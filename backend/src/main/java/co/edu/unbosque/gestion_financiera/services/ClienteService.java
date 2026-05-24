package co.edu.unbosque.gestion_financiera.services;

import co.edu.unbosque.gestion_financiera.exceptions.ClienteYaExisteException;
import co.edu.unbosque.gestion_financiera.exceptions.CorreoYaExisteException;
import co.edu.unbosque.gestion_financiera.exceptions.IdentificacionInvalidaException;
import co.edu.unbosque.gestion_financiera.model.dtos.ClienteDTO;
import co.edu.unbosque.gestion_financiera.model.entities.Cliente;
import co.edu.unbosque.gestion_financiera.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }

    public Cliente registrarCliente(ClienteDTO dto) {
        if (!dto.getNumeroIdentificacion().matches("\\d{10}")) {
            throw new IdentificacionInvalidaException(
                    "El numero de identificacion debe tener exactamente 10 digitos numericos");
        }
        if (clienteRepository.findByNumeroIdentificacion(
                dto.getNumeroIdentificacion()).isPresent()) {
            throw new ClienteYaExisteException(
                    "Ya existe un cliente con ese numero de identificacion");
        }
        if (clienteRepository.findByCorreoElectronico(
                dto.getCorreoElectronico()).isPresent()) {
            throw new CorreoYaExisteException(
                    "Ya existe un cliente con ese correo electronico");
        }
        Cliente cliente = new Cliente();
        cliente.setNumeroIdentificacion(dto.getNumeroIdentificacion());
        cliente.setCorreoElectronico(dto.getCorreoElectronico());
        cliente.setNombreCompleto(dto.getNombreCompleto());
        return clienteRepository.save(cliente);
    }
}