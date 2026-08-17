package edu.pe.cibertec.hotelazul.service;

import java.util.List;

import edu.pe.cibertec.hotelazul.entity.Huesped;

public interface HuespedService {

    List<Huesped> listar();

    Huesped buscarPorId(Long id);

    Huesped guardar(Huesped huesped);

    Huesped actualizar(Long id, Huesped huesped);

    void eliminar(Long id);

}
