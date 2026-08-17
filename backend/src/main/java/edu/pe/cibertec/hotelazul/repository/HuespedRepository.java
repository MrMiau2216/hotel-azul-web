package edu.pe.cibertec.hotelazul.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.pe.cibertec.hotelazul.entity.Huesped;

public interface HuespedRepository extends JpaRepository<Huesped, Long> {

    Huesped findByDni(String dni);

}
