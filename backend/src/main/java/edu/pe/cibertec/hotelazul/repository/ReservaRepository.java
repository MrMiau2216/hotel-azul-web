package edu.pe.cibertec.hotelazul.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.pe.cibertec.hotelazul.entity.Reserva;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {
}
