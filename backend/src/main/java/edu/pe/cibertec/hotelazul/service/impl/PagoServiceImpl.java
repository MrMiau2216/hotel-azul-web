package edu.pe.cibertec.hotelazul.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.pe.cibertec.hotelazul.entity.Pago;
import edu.pe.cibertec.hotelazul.entity.Reserva;
import edu.pe.cibertec.hotelazul.repository.PagoRepository;
import edu.pe.cibertec.hotelazul.service.PagoService;
import edu.pe.cibertec.hotelazul.service.ReservaService;

@Service
public class PagoServiceImpl implements PagoService {

    @Autowired
    private PagoRepository repository;

    @Autowired
    private ReservaService reservaService;

    @Override
    public List<Pago> listar() {
        return repository.findAll();
    }

    @Override
    public Pago buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Pago guardar(Pago pago) {
        Reserva reserva = reservaService.buscarPorId(pago.getReserva().getId());
        if (reserva == null)
            throw new RuntimeException("La reserva no existe.");
        pago.setReserva(reserva);
        return repository.save(pago);
    }

    @Override
    public Pago actualizar(Long id, Pago pagoActualizado) {
        Pago pago = repository.findById(id).orElse(null);
        if (pago != null) {
            Reserva reserva = reservaService.buscarPorId(pagoActualizado.getReserva().getId());
            if (reserva == null)
                throw new RuntimeException("La reserva no existe.");
            pago.setReserva(reserva);
            pago.setMonto(pagoActualizado.getMonto());
            pago.setMetodo(pagoActualizado.getMetodo());
            repository.save(pago);
        }
        return pago;
    }

    @Override
    public void eliminar(Long id) {
        repository.deleteById(id);
    }

}
