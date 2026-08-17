package edu.pe.cibertec.hotelazul.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import edu.pe.cibertec.hotelazul.entity.Reserva;
import edu.pe.cibertec.hotelazul.service.ReservaService;

@RestController
@RequestMapping("/api/reservas")
public class ReservasRestController {

    private final ReservaService reservaService;

    public ReservasRestController(ReservaService reservaService) {
        this.reservaService = reservaService;
    }

    @GetMapping
    public List<Reserva> listar() {
        return reservaService.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        Reserva reserva = reservaService.buscarPorId(id);
        if (reserva == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La reserva con ID " + id + " no existe.");
        return ResponseEntity.ok(reserva);
    }

    @PostMapping
    public ResponseEntity<?> guardar(@RequestBody Reserva reserva) {
        try {
            Reserva nueva = reservaService.guardar(reserva);
            return ResponseEntity.status(HttpStatus.CREATED).body(nueva);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody Reserva reserva) {
        try {
            Reserva actualizada = reservaService.actualizar(id, reserva);
            if (actualizada == null)
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La reserva con ID " + id + " no existe.");
            return ResponseEntity.ok(actualizada);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        reservaService.eliminar(id);
        return ResponseEntity.ok("Reserva eliminada correctamente.");
    }

}
