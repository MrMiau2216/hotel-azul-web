package edu.pe.cibertec.hotelazul.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.pe.cibertec.hotelazul.entity.Habitacion;

public interface HabitacionRepository extends JpaRepository<Habitacion, Long> {

    List<Habitacion> findByEstado(String estado);

}
