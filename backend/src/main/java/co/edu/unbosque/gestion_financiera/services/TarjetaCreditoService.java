package co.edu.unbosque.gestion_financiera.services;

import co.edu.unbosque.gestion_financiera.exceptions.ClienteNoExisteException;
import co.edu.unbosque.gestion_financiera.exceptions.FechaVencimientoInvalidaException;
import co.edu.unbosque.gestion_financiera.exceptions.NumeroTarjetaDuplicadoException;
import co.edu.unbosque.gestion_financiera.exceptions.NumeroTarjetaInvalidoException;
import co.edu.unbosque.gestion_financiera.model.dtos.TarjetaCreditoDTO;
import co.edu.unbosque.gestion_financiera.model.entities.Cliente;
import co.edu.unbosque.gestion_financiera.model.entities.TarjetaCredito;
import co.edu.unbosque.gestion_financiera.repositories.ClienteRepository;
import co.edu.unbosque.gestion_financiera.repositories.TarjetaCreditoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TarjetaCreditoService {

    @Autowired
    private TarjetaCreditoRepository tarjetaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    public List<TarjetaCredito> listarTarjetas() {
        return tarjetaRepository.findAll();
    }

    public TarjetaCredito registrarTarjeta(TarjetaCreditoDTO dto) {

        // Validar numero de tarjeta
        String numero = dto.getNumeroTarjeta();
        String franquicia = detectarFranquicia(numero);

        // Validar fecha de vencimiento
        if (!dto.getFechaVencimiento().matches("^(0[1-9]|1[0-2])/\\d{4}$")) {
            throw new FechaVencimientoInvalidaException(
                    "La fecha de vencimiento debe tener el formato MM/YYYY");
        }

        // Validar duplicado
        if (tarjetaRepository.findByNumeroTarjeta(numero).isPresent()) {
            throw new NumeroTarjetaDuplicadoException(
                    "Ya existe una tarjeta con ese numero");
        }

        // Buscar cliente
        Cliente cliente = clienteRepository.findById(dto.getIdCliente())
                .orElseThrow(() -> new ClienteNoExisteException(
                        "No existe un cliente con ese id"));

        // Calcular cupo utilizado
        Double cupoUtilizado = dto.getCupoTotal() - dto.getCupoDisponible();

        // Crear tarjeta
        TarjetaCredito tarjeta = new TarjetaCredito();
        tarjeta.setNumeroTarjeta(numero);
        tarjeta.setFechaVencimiento(dto.getFechaVencimiento());
        tarjeta.setFranquicia(franquicia);
        tarjeta.setEstado("ACTIVO");
        tarjeta.setCupoTotal(dto.getCupoTotal());
        tarjeta.setCupoDisponible(dto.getCupoDisponible());
        tarjeta.setCupoUtilizado(cupoUtilizado);
        tarjeta.setCliente(cliente);

        return tarjetaRepository.save(tarjeta);
    }

    private String detectarFranquicia(String numero) {
        if (numero == null) {
            throw new NumeroTarjetaInvalidoException(
                    "El numero de tarjeta no puede ser nulo");
        }
        if (numero.length() == 16) {
            if (numero.charAt(0) == '4') {
                return "VISA";
            }
            int prefijo = Integer.parseInt(numero.substring(0, 2));
            if (prefijo >= 51 && prefijo <= 55) {
                return "MASTERCARD";
            }
        }
        if (numero.length() == 15) {
            String prefijo = numero.substring(0, 2);
            if (prefijo.equals("34") || prefijo.equals("37")) {
                return "AMEX";
            }
        }
        throw new NumeroTarjetaInvalidoException(
                "El numero de tarjeta no corresponde a ninguna franquicia valida");
    }
}