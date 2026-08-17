package edu.pe.cibertec.hotelazul.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import edu.pe.cibertec.hotelazul.entity.Habitacion;
import edu.pe.cibertec.hotelazul.service.HabitacionService;

@RestController
@RequestMapping("/api/habitaciones")
public class HabitacionesRestController {

    private final HabitacionService habitacionService;

    public HabitacionesRestController(HabitacionService habitacionService) {
        this.habitacionService = habitacionService;
    }

    @GetMapping
    public List<Habitacion> listar() {
        return habitacionService.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        Habitacion habitacion = habitacionService.buscarPorId(id);
        if (habitacion == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La habitación con ID " + id + " no existe.");
        return ResponseEntity.ok(habitacion);
    }

    @PostMapping
    public ResponseEntity<?> guardar(@RequestBody Habitacion habitacion) {
        try {
            Habitacion nueva = habitacionService.guardar(habitacion);
            return ResponseEntity.status(HttpStatus.CREATED).body(nueva);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody Habitacion habitacion) {
        try {
            Habitacion actualizada = habitacionService.actualizar(id, habitacion);
            if (actualizada == null)
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La habitación con ID " + id + " no existe.");
            return ResponseEntity.ok(actualizada);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        habitacionService.eliminar(id);
        return ResponseEntity.ok("Habitación eliminada correctamente.");
    }

}
