package edu.pe.cibertec.hotelazul.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.pe.cibertec.hotelazul.entity.TipoHabitacion;
import edu.pe.cibertec.hotelazul.repository.TipoHabitacionRepository;
import edu.pe.cibertec.hotelazul.service.TipoHabitacionService;

@Service
public class TipoHabitacionServiceImpl implements TipoHabitacionService {

    @Autowired
    private TipoHabitacionRepository repository;

    @Override
    public List<TipoHabitacion> listar() {
        return repository.findAll();
    }

    @Override
    public TipoHabitacion buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

}
