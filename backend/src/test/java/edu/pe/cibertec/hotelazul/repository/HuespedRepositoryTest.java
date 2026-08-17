package edu.pe.cibertec.hotelazul.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;

import edu.pe.cibertec.hotelazul.entity.Huesped;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class HuespedRepositoryTest {

    @Autowired
    private HuespedRepository huespedRepository;

    private Huesped crearHuesped() {
        Huesped huesped = new Huesped();
        huesped.setDni("T" + (System.nanoTime() % 10000000L));
        huesped.setNombres("Huésped");
        huesped.setApellidos("De Prueba");
        huesped.setTelefono("987654321");
        huesped.setCorreo("huesped.prueba@example.com");
        return huesped;
    }

    @Test
    void insertar() {
        Huesped huesped = huespedRepository.save(crearHuesped());

        assertNotNull(huesped.getId());
        assertEquals("De Prueba", huesped.getApellidos());
    }

    @Test
    void listar() {
        huespedRepository.save(crearHuesped());
        huespedRepository.save(crearHuesped());

        List<Huesped> huespedes = huespedRepository.findAll();

        assertTrue(huespedes.size() >= 2);
    }

    @Test
    void actualizar() {
        Huesped huesped = huespedRepository.save(crearHuesped());

        huesped.setTelefono("999888777");
        huespedRepository.save(huesped);

        Huesped actualizado = huespedRepository.findById(huesped.getId()).orElseThrow();
        assertEquals("999888777", actualizado.getTelefono());
    }

    @Test
    void eliminar() {
        Huesped huesped = huespedRepository.save(crearHuesped());

        huespedRepository.deleteById(huesped.getId());

        Optional<Huesped> resultado = huespedRepository.findById(huesped.getId());
        assertTrue(resultado.isEmpty());
    }

}
