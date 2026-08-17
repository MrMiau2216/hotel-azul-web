package edu.pe.cibertec.hotelazul.service;

import java.util.List;

import edu.pe.cibertec.hotelazul.entity.Pago;

public interface PagoService {

    List<Pago> listar();

    Pago buscarPorId(Long id);

    Pago guardar(Pago pago);

    Pago actualizar(Long id, Pago pago);

    void eliminar(Long id);

}
