package edu.pe.cibertec.hotelazul.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.pe.cibertec.hotelazul.entity.Habitacion;
import edu.pe.cibertec.hotelazul.entity.TipoHabitacion;
import edu.pe.cibertec.hotelazul.repository.HabitacionRepository;
import edu.pe.cibertec.hotelazul.service.HabitacionService;
import edu.pe.cibertec.hotelazul.service.TipoHabitacionService;

@Service
public class HabitacionServiceImpl implements HabitacionService {

    @Autowired
    private HabitacionRepository repository;

    @Autowired
    private TipoHabitacionService tipoHabitacionService;

    @Override
    public List<Habitacion> listar() {
        return repository.findAll();
    }

    @Override
    public Habitacion buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Habitacion guardar(Habitacion habitacion) {
        TipoHabitacion tipo = tipoHabitacionService.buscarPorId(habitacion.getTipo().getId());
        if (tipo == null)
            throw new RuntimeException("El tipo de habitación no existe.");
        habitacion.setTipo(tipo);
        return repository.save(habitacion);
    }

    @Override
    public Habitacion actualizar(Long id, Habitacion habitacionActualizada) {
        Habitacion habitacion = repository.findById(id).orElse(null);
        if (habitacion != null) {
            TipoHabitacion tipo = tipoHabitacionService.buscarPorId(habitacionActualizada.getTipo().getId());
            if (tipo == null)
                throw new RuntimeException("El tipo de habitación no existe.");
            habitacion.setNumero(habitacionActualizada.getNumero());
            habitacion.setPiso(habitacionActualizada.getPiso());
            habitacion.setPrecio(habitacionActualizada.getPrecio());
            habitacion.setUrlImagen(habitacionActualizada.getUrlImagen());
            habitacion.setDescripcion(habitacionActualizada.getDescripcion());
            habitacion.setEstado(habitacionActualizada.getEstado());
            habitacion.setTipo(tipo);
            repository.save(habitacion);
        }
        return habitacion;
    }

    @Override
    public void eliminar(Long id) {
        repository.deleteById(id);
    }

}
