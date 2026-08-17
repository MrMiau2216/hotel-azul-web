package edu.pe.cibertec.hotelazul.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.pe.cibertec.hotelazul.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Usuario findByUsername(String username);

}
