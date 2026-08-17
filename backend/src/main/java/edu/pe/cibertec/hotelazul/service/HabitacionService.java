package edu.pe.cibertec.hotelazul.service;

import java.util.List;

import edu.pe.cibertec.hotelazul.entity.Habitacion;

public interface HabitacionService {

    List<Habitacion> listar();

    Habitacion buscarPorId(Long id);

    Habitacion guardar(Habitacion habitacion);

    Habitacion actualizar(Long id, Habitacion habitacion);

    void eliminar(Long id);

}
