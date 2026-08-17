package edu.pe.cibertec.hotelazul.service;

import java.util.List;

import edu.pe.cibertec.hotelazul.entity.Reserva;

public interface ReservaService {

    List<Reserva> listar();

    Reserva buscarPorId(Long id);

    Reserva guardar(Reserva reserva);

    Reserva actualizar(Long id, Reserva reserva);

    void eliminar(Long id);

}
