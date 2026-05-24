package co.edu.unbosque.gestion_financiera;

import co.edu.unbosque.gestion_financiera.exceptions.ClienteYaExisteException;
import co.edu.unbosque.gestion_financiera.exceptions.IdentificacionInvalidaException;
import co.edu.unbosque.gestion_financiera.model.dtos.ClienteDTO;
import co.edu.unbosque.gestion_financiera.model.entities.Cliente;
import co.edu.unbosque.gestion_financiera.repositories.ClienteRepository;
import co.edu.unbosque.gestion_financiera.services.ClienteService;
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
public class ClienteServiceTest {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteService clienteService;

    @Test
    public void testRegistrarClienteExitoso() {
        ClienteDTO dto = new ClienteDTO();
        dto.setNumeroIdentificacion("1234567890");
        dto.setCorreoElectronico("test@gmail.com");
        dto.setNombreCompleto("Test Cliente");

        when(clienteRepository.findByNumeroIdentificacion(any())).thenReturn(Optional.empty());
        when(clienteRepository.findByCorreoElectronico(any())).thenReturn(Optional.empty());
        when(clienteRepository.save(any())).thenReturn(new Cliente());

        Cliente resultado = clienteService.registrarCliente(dto);
        assertNotNull(resultado);
    }

    @Test
    public void testRegistrarClienteIdentificacionInvalida() {
        ClienteDTO dto = new ClienteDTO();
        dto.setNumeroIdentificacion("12345");
        dto.setCorreoElectronico("test@gmail.com");
        dto.setNombreCompleto("Test Cliente");

        assertThrows(IdentificacionInvalidaException.class, () -> {
            clienteService.registrarCliente(dto);
        });
    }

    @Test
    public void testRegistrarClienteDuplicado() {
        ClienteDTO dto = new ClienteDTO();
        dto.setNumeroIdentificacion("1234567890");
        dto.setCorreoElectronico("test@gmail.com");
        dto.setNombreCompleto("Test Cliente");

        when(clienteRepository.findByNumeroIdentificacion(any()))
                .thenReturn(Optional.of(new Cliente()));

        assertThrows(ClienteYaExisteException.class, () -> {
            clienteService.registrarCliente(dto);
        });
    }
}