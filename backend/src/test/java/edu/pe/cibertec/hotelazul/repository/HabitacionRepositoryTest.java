package edu.pe.cibertec.hotelazul.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;

import edu.pe.cibertec.hotelazul.entity.Habitacion;
import edu.pe.cibertec.hotelazul.entity.TipoHabitacion;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class HabitacionRepositoryTest {

    @Autowired
    private HabitacionRepository habitacionRepository;

    @Autowired
    private TipoHabitacionRepository tipoHabitacionRepository;

    private TipoHabitacion crearTipo() {
        TipoHabitacion tipo = new TipoHabitacion();
        tipo.setNombre("Tipo" + (System.nanoTime() % 100000L));
        return tipoHabitacionRepository.save(tipo);
    }

    private Habitacion crearHabitacion(TipoHabitacion tipo) {
        Habitacion habitacion = new Habitacion();
        habitacion.setNumero("T" + (System.nanoTime() % 100000L));
        habitacion.setPiso(1);
        habitacion.setPrecio(new BigDecimal("100.00"));
        habitacion.setDescripcion("Habitación de prueba");
        habitacion.setEstado("DISPONIBLE");
        habitacion.setTipo(tipo);
        return habitacion;
    }

    @Test
    void insertar() {
        TipoHabitacion tipo = crearTipo();
        Habitacion habitacion = habitacionRepository.save(crearHabitacion(tipo));

        assertNotNull(habitacion.getId());
        assertEquals("DISPONIBLE", habitacion.getEstado());
    }

    @Test
    void listar() {
        TipoHabitacion tipo = crearTipo();
        habitacionRepository.save(crearHabitacion(tipo));
        habitacionRepository.save(crearHabitacion(tipo));

        List<Habitacion> habitaciones = habitacionRepository.findAll();

        assertTrue(habitaciones.size() >= 2);
    }

    @Test
    void actualizar() {
        TipoHabitacion tipo = crearTipo();
        Habitacion habitacion = habitacionRepository.save(crearHabitacion(tipo));

        habitacion.setEstado("MANTENIMIENTO");
        habitacionRepository.save(habitacion);

        Habitacion actualizada = habitacionRepository.findById(habitacion.getId()).orElseThrow();
        assertEquals("MANTENIMIENTO", actualizada.getEstado());
    }

    @Test
    void eliminar() {
        TipoHabitacion tipo = crearTipo();
        Habitacion habitacion = habitacionRepository.save(crearHabitacion(tipo));

        habitacionRepository.deleteById(habitacion.getId());

        Optional<Habitacion> resultado = habitacionRepository.findById(habitacion.getId());
        assertTrue(resultado.isEmpty());
    }

}
