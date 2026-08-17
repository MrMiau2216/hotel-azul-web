package edu.pe.cibertec.hotelazul.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.pe.cibertec.hotelazul.entity.Huesped;
import edu.pe.cibertec.hotelazul.repository.HuespedRepository;
import edu.pe.cibertec.hotelazul.service.HuespedService;

@Service
public class HuespedServiceImpl implements HuespedService {

    @Autowired
    private HuespedRepository repository;

    @Override
    public List<Huesped> listar() {
        return repository.findAll();
    }

    @Override
    public Huesped buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Huesped guardar(Huesped huesped) {
        return repository.save(huesped);
    }

    @Override
    public Huesped actualizar(Long id, Huesped huespedActualizado) {
        Huesped huesped = repository.findById(id).orElse(null);
        if (huesped != null) {
            huesped.setDni(huespedActualizado.getDni());
            huesped.setNombres(huespedActualizado.getNombres());
            huesped.setApellidos(huespedActualizado.getApellidos());
            huesped.setTelefono(huespedActualizado.getTelefono());
            huesped.setCorreo(huespedActualizado.getCorreo());
            repository.save(huesped);
        }
        return huesped;
    }

    @Override
    public void eliminar(Long id) {
        repository.deleteById(id);
    }

}
