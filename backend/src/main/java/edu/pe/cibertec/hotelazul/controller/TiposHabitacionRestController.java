package edu.pe.cibertec.hotelazul.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.pe.cibertec.hotelazul.entity.TipoHabitacion;
import edu.pe.cibertec.hotelazul.service.TipoHabitacionService;

@RestController
@RequestMapping("/api/tipos-habitacion")
public class TiposHabitacionRestController {

    private final TipoHabitacionService tipoHabitacionService;

    public TiposHabitacionRestController(TipoHabitacionService tipoHabitacionService) {
        this.tipoHabitacionService = tipoHabitacionService;
    }

    @GetMapping
    public List<TipoHabitacion> listar() {
        return tipoHabitacionService.listar();
    }

}
