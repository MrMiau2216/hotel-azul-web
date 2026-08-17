package edu.pe.cibertec.hotelazul.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import edu.pe.cibertec.hotelazul.entity.Pago;
import edu.pe.cibertec.hotelazul.service.PagoService;

@RestController
@RequestMapping("/api/pagos")
public class PagosRestController {

    private final PagoService pagoService;

    public PagosRestController(PagoService pagoService) {
        this.pagoService = pagoService;
    }

    @GetMapping
    public List<Pago> listar() {
        return pagoService.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        Pago pago = pagoService.buscarPorId(id);
        if (pago == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El pago con ID " + id + " no existe.");
        return ResponseEntity.ok(pago);
    }

    @PostMapping
    public ResponseEntity<?> guardar(@RequestBody Pago pago) {
        try {
            Pago nuevo = pagoService.guardar(pago);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody Pago pago) {
        try {
            Pago actualizado = pagoService.actualizar(id, pago);
            if (actualizado == null)
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El pago con ID " + id + " no existe.");
            return ResponseEntity.ok(actualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        pagoService.eliminar(id);
        return ResponseEntity.ok("Pago eliminado correctamente.");
    }

}
