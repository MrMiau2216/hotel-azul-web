package edu.pe.cibertec.hotelazul.service;

import java.util.List;

import edu.pe.cibertec.hotelazul.entity.TipoHabitacion;

public interface TipoHabitacionService {

    List<TipoHabitacion> listar();

    TipoHabitacion buscarPorId(Long id);

}
