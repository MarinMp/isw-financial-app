package co.edu.unbosque.gestion_financiera;

import co.edu.unbosque.gestion_financiera.exceptions.FechaVencimientoInvalidaException;
import co.edu.unbosque.gestion_financiera.exceptions.NumeroTarjetaDuplicadoException;
import co.edu.unbosque.gestion_financiera.exceptions.NumeroTarjetaInvalidoException;
import co.edu.unbosque.gestion_financiera.exceptions.TarjetaInactivaException;
import co.edu.unbosque.gestion_financiera.model.dtos.TarjetaCreditoDTO;
import co.edu.unbosque.gestion_financiera.model.entities.Cliente;
import co.edu.unbosque.gestion_financiera.model.entities.TarjetaCredito;
import co.edu.unbosque.gestion_financiera.repositories.ClienteRepository;
import co.edu.unbosque.gestion_financiera.repositories.TarjetaCreditoRepository;
import co.edu.unbosque.gestion_financiera.services.TarjetaCreditoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TarjetaCreditoServiceTest {

    @Mock
    private TarjetaCreditoRepository tarjetaRepository;

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private TarjetaCreditoService tarjetaService;

    @Test
    public void testRegistrarTarjetaVisa() {
        TarjetaCreditoDTO dto = new TarjetaCreditoDTO();
        dto.setNumeroTarjeta("4111111111111111");
        dto.setFechaVencimiento("08/2027");
        dto.setCupoTotal(5000000.0);
        dto.setCupoDisponible(3000000.0);
        dto.setIdCliente(1);

        when(tarjetaRepository.findByNumeroTarjeta(any())).thenReturn(Optional.empty());
        when(clienteRepository.findById(any())).thenReturn(Optional.of(new Cliente()));
        when(tarjetaRepository.save(any())).thenReturn(new TarjetaCredito());

        TarjetaCredito resultado = tarjetaService.registrarTarjeta(dto);
        assertNotNull(resultado);
    }

    @Test
    public void testRegistrarTarjetaNumeroInvalido() {
        TarjetaCreditoDTO dto = new TarjetaCreditoDTO();
        dto.setNumeroTarjeta("1234567890");
        dto.setFechaVencimiento("08/2027");
        dto.setCupoTotal(5000000.0);
        dto.setCupoDisponible(3000000.0);
        dto.setIdCliente(1);

        assertThrows(NumeroTarjetaInvalidoException.class, () -> {
            tarjetaService.registrarTarjeta(dto);
        });
    }

    @Test
    public void testRegistrarTarjetaFechaInvalida() {
        TarjetaCreditoDTO dto = new TarjetaCreditoDTO();
        dto.setNumeroTarjeta("4111111111111111");
        dto.setFechaVencimiento("2027/08");
        dto.setCupoTotal(5000000.0);
        dto.setCupoDisponible(3000000.0);
        dto.setIdCliente(1);

        assertThrows(FechaVencimientoInvalidaException.class, () -> {
            tarjetaService.registrarTarjeta(dto);
        });
    }

    @Test
    public void testRegistrarTarjetaDuplicada() {
        TarjetaCreditoDTO dto = new TarjetaCreditoDTO();
        dto.setNumeroTarjeta("4111111111111111");
        dto.setFechaVencimiento("08/2027");
        dto.setCupoTotal(5000000.0);
        dto.setCupoDisponible(3000000.0);
        dto.setIdCliente(1);

        when(tarjetaRepository.findByNumeroTarjeta(any()))
                .thenReturn(Optional.of(new TarjetaCredito()));

        assertThrows(NumeroTarjetaDuplicadoException.class, () -> {
            tarjetaService.registrarTarjeta(dto);
        });
    }

    @Test
    public void testEliminarTarjetaExitosa() {
        TarjetaCredito tarjeta = new TarjetaCredito();
        tarjeta.setEstado("ACTIVO");

        when(tarjetaRepository.findById(any())).thenReturn(Optional.of(tarjeta));
        when(tarjetaRepository.save(any())).thenReturn(tarjeta);

        TarjetaCredito resultado = tarjetaService.eliminarTarjeta(1);
        assertEquals("INACTIVO", resultado.getEstado());
    }

    @Test
    public void testEliminarTarjetaYaInactiva() {
        TarjetaCredito tarjeta = new TarjetaCredito();
        tarjeta.setEstado("INACTIVO");

        when(tarjetaRepository.findById(any())).thenReturn(Optional.of(tarjeta));

        assertThrows(TarjetaInactivaException.class, () -> {
            tarjetaService.eliminarTarjeta(1);
        });
    }
}