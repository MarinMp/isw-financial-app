package co.edu.unbosque.gestion_financiera.controller;

import co.edu.unbosque.gestion_financiera.exceptions.ClienteYaExisteException;
import co.edu.unbosque.gestion_financiera.exceptions.CorreoYaExisteException;
import co.edu.unbosque.gestion_financiera.exceptions.IdentificacionInvalidaException;
import co.edu.unbosque.gestion_financiera.model.dtos.ClienteDTO;
import co.edu.unbosque.gestion_financiera.model.entities.Cliente;
import co.edu.unbosque.gestion_financiera.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
@CrossOrigin(origins = "*")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public ResponseEntity<List<Cliente>> listarClientes() {
        return ResponseEntity.ok(clienteService.listarClientes());
    }

    @PostMapping
    public ResponseEntity<?> registrarCliente(@RequestBody ClienteDTO dto) {
        try {
            Cliente cliente = clienteService.registrarCliente(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(cliente);
        } catch (IdentificacionInvalidaException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (ClienteYaExisteException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (CorreoYaExisteException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }
}