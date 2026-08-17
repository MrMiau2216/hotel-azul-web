package edu.pe.cibertec.hotelazul.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.pe.cibertec.hotelazul.entity.TipoHabitacion;

public interface TipoHabitacionRepository extends JpaRepository<TipoHabitacion, Long> {

    TipoHabitacion findByNombre(String nombre);

}
