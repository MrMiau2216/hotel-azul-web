package edu.pe.cibertec.hotelazul.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import edu.pe.cibertec.hotelazul.entity.Huesped;
import edu.pe.cibertec.hotelazul.service.HuespedService;

@RestController
@RequestMapping("/api/huespedes")
public class HuespedesRestController {

    private final HuespedService huespedService;

    public HuespedesRestController(HuespedService huespedService) {
        this.huespedService = huespedService;
    }

    @GetMapping
    public List<Huesped> listar() {
        return huespedService.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        Huesped huesped = huespedService.buscarPorId(id);
        if (huesped == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El huésped con ID " + id + " no existe.");
        return ResponseEntity.ok(huesped);
    }

    @PostMapping
    public ResponseEntity<Huesped> guardar(@RequestBody Huesped huesped) {
        Huesped nuevo = huespedService.guardar(huesped);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody Huesped huesped) {
        Huesped actualizado = huespedService.actualizar(id, huesped);
        if (actualizado == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El huésped con ID " + id + " no existe.");
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        huespedService.eliminar(id);
        return ResponseEntity.ok("Huésped eliminado correctamente.");
    }

}
