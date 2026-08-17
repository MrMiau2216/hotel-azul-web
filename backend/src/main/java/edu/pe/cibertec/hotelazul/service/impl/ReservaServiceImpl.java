package edu.pe.cibertec.hotelazul.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.pe.cibertec.hotelazul.entity.Habitacion;
import edu.pe.cibertec.hotelazul.entity.Huesped;
import edu.pe.cibertec.hotelazul.entity.Reserva;
import edu.pe.cibertec.hotelazul.repository.ReservaRepository;
import edu.pe.cibertec.hotelazul.service.HabitacionService;
import edu.pe.cibertec.hotelazul.service.HuespedService;
import edu.pe.cibertec.hotelazul.service.ReservaService;

@Service
public class ReservaServiceImpl implements ReservaService {

    @Autowired
    private ReservaRepository repository;

    @Autowired
    private HabitacionService habitacionService;

    @Autowired
    private HuespedService huespedService;

    @Override
    public List<Reserva> listar() {
        return repository.findAll();
    }

    @Override
    public Reserva buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Reserva guardar(Reserva reserva) {
        Habitacion habitacion = habitacionService.buscarPorId(reserva.getHabitacion().getId());
        if (habitacion == null)
            throw new RuntimeException("La habitación no existe.");
        Huesped huesped = huespedService.buscarPorId(reserva.getHuesped().getId());
        if (huesped == null)
            throw new RuntimeException("El huésped no existe.");
        reserva.setHabitacion(habitacion);
        reserva.setHuesped(huesped);
        return repository.save(reserva);
    }

    @Override
    public Reserva actualizar(Long id, Reserva reservaActualizada) {
        Reserva reserva = repository.findById(id).orElse(null);
        if (reserva != null) {
            Habitacion habitacion = habitacionService.buscarPorId(reservaActualizada.getHabitacion().getId());
            if (habitacion == null)
                throw new RuntimeException("La habitación no existe.");
            Huesped huesped = huespedService.buscarPorId(reservaActualizada.getHuesped().getId());
            if (huesped == null)
                throw new RuntimeException("El huésped no existe.");
            reserva.setHabitacion(habitacion);
            reserva.setHuesped(huesped);
            reserva.setFechaEntrada(reservaActualizada.getFechaEntrada());
            reserva.setFechaSalida(reservaActualizada.getFechaSalida());
            reserva.setEstado(reservaActualizada.getEstado());
            reserva.setTotal(reservaActualizada.getTotal());
            repository.save(reserva);
        }
        return reserva;
    }

    @Override
    public void eliminar(Long id) {
        repository.deleteById(id);
    }

}
