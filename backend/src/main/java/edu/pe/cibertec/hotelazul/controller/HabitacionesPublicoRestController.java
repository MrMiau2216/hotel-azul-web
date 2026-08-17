package edu.pe.cibertec.hotelazul.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.pe.cibertec.hotelazul.entity.Habitacion;
import edu.pe.cibertec.hotelazul.service.HabitacionService;

@RestController
@RequestMapping("/api/publico/habitaciones")
public class HabitacionesPublicoRestController {

    private final HabitacionService habitacionService;

    public HabitacionesPublicoRestController(HabitacionService habitacionService) {
        this.habitacionService = habitacionService;
    }

    @GetMapping
    public List<Habitacion> listar() {
        return habitacionService.listar();
    } // http://localhost:8080/api/publico/habitaciones

}
