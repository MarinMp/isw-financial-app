package co.edu.unbosque.gestion_financiera.controller;

import co.edu.unbosque.gestion_financiera.exceptions.*;
import co.edu.unbosque.gestion_financiera.model.dtos.TarjetaCreditoDTO;
import co.edu.unbosque.gestion_financiera.model.entities.TarjetaCredito;
import co.edu.unbosque.gestion_financiera.services.TarjetaCreditoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tarjetas")
@CrossOrigin(origins = "*")
public class TarjetaCreditoController {

    @Autowired
    private TarjetaCreditoService tarjetaService;

    @GetMapping
    public ResponseEntity<List<TarjetaCredito>> listarTarjetas() {
        return ResponseEntity.ok(tarjetaService.listarTarjetas());
    }

    @PostMapping
    public ResponseEntity<?> registrarTarjeta(@RequestBody TarjetaCreditoDTO dto) {
        try {
            TarjetaCredito tarjeta = tarjetaService.registrarTarjeta(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(tarjeta);
        } catch (NumeroTarjetaInvalidoException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (FechaVencimientoInvalidaException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (NumeroTarjetaDuplicadoException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (ClienteNoExisteException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{idTarjeta}")
    public ResponseEntity<?> eliminarTarjeta(@PathVariable Integer idTarjeta) {
        try {
            TarjetaCredito tarjeta = tarjetaService.eliminarTarjeta(idTarjeta);
            return ResponseEntity.ok(tarjeta);
        } catch (ClienteNoExisteException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (TarjetaInactivaException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}