package edu.pe.cibertec.hotelazul;

import java.math.BigDecimal;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import edu.pe.cibertec.hotelazul.entity.Habitacion;
import edu.pe.cibertec.hotelazul.entity.TipoHabitacion;
import edu.pe.cibertec.hotelazul.entity.Usuario;
import edu.pe.cibertec.hotelazul.repository.HabitacionRepository;
import edu.pe.cibertec.hotelazul.repository.TipoHabitacionRepository;
import edu.pe.cibertec.hotelazul.repository.UsuarioRepository;

@Component
public class CargaDatosIniciales implements CommandLineRunner {

    private final UsuarioRepository usuarioRepository;
    private final TipoHabitacionRepository tipoHabitacionRepository;
    private final HabitacionRepository habitacionRepository;
    private final PasswordEncoder passwordEncoder;

    public CargaDatosIniciales(UsuarioRepository usuarioRepository, TipoHabitacionRepository tipoHabitacionRepository,
            HabitacionRepository habitacionRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.tipoHabitacionRepository = tipoHabitacionRepository;
        this.habitacionRepository = habitacionRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        // USUARIOS ====================================================
        if (usuarioRepository.count() == 0) {
            Usuario admin = new Usuario();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("Admin123"));
            admin.setRol("ADMIN");
            usuarioRepository.save(admin);

            Usuario recepcionista = new Usuario();
            recepcionista.setUsername("recepcion");
            recepcionista.setPassword(passwordEncoder.encode("Recepcion123"));
            recepcionista.setRol("RECEPCIONISTA");
            usuarioRepository.save(recepcionista);
        }

        // TIPOS DE HABITACIÓN ====================================================
        if (tipoHabitacionRepository.count() == 0) {
            TipoHabitacion simple = new TipoHabitacion();
            simple.setNombre("Simple");
            tipoHabitacionRepository.save(simple);

            TipoHabitacion doble = new TipoHabitacion();
            doble.setNombre("Doble");
            tipoHabitacionRepository.save(doble);

            TipoHabitacion matrimonial = new TipoHabitacion();
            matrimonial.setNombre("Matrimonial");
            tipoHabitacionRepository.save(matrimonial);

            TipoHabitacion suite = new TipoHabitacion();
            suite.setNombre("Suite");
            tipoHabitacionRepository.save(suite);

            // HABITACIONES DE EJEMPLO ====================================================
            if (habitacionRepository.count() == 0) {
                crearHabitacion("101", 1, new BigDecimal("120.00"),
                        "https://images.unsplash.com/photo-1611892440504-42a792e24d32?w=600",
                        "Habitación simple con vista a la ciudad.", simple);
                crearHabitacion("102", 1, new BigDecimal("120.00"),
                        "https://images.unsplash.com/photo-1590490360182-c33d57733427?w=600",
                        "Habitación simple con escritorio de trabajo.", simple);
                crearHabitacion("201", 2, new BigDecimal("180.00"),
                        "https://images.unsplash.com/photo-1512918728675-ed5a9ecdebfd?w=600",
                        "Habitación doble con dos camas individuales.", doble);
                crearHabitacion("202", 2, new BigDecimal("180.00"),
                        "https://images.unsplash.com/photo-1566665797739-1674de7a421a?w=600",
                        "Habitación doble con balcón.", doble);
                crearHabitacion("301", 3, new BigDecimal("220.00"),
                        "https://images.unsplash.com/photo-1560185893-a55cbc8c57e8?w=600",
                        "Habitación matrimonial con cama queen.", matrimonial);
                crearHabitacion("302", 3, new BigDecimal("220.00"),
                        "https://images.unsplash.com/photo-1595576508898-0ad5c879a061?w=600",
                        "Habitación matrimonial con vista al jardín.", matrimonial);
                crearHabitacion("401", 4, new BigDecimal("350.00"),
                        "https://images.unsplash.com/photo-1611892440504-42a792e24d32?w=600",
                        "Suite con sala de estar independiente.", suite);
                crearHabitacion("402", 4, new BigDecimal("400.00"),
                        "https://images.unsplash.com/photo-1582719508461-905c673771fd?w=600",
                        "Suite de lujo con jacuzzi privado.", suite);
            }
        }
    }

    private void crearHabitacion(String numero, Integer piso, BigDecimal precio, String urlImagen,
            String descripcion, TipoHabitacion tipo) {
        Habitacion habitacion = new Habitacion();
        habitacion.setNumero(numero);
        habitacion.setPiso(piso);
        habitacion.setPrecio(precio);
        habitacion.setUrlImagen(urlImagen);
        habitacion.setDescripcion(descripcion);
        habitacion.setEstado("DISPONIBLE");
        habitacion.setTipo(tipo);
        habitacionRepository.save(habitacion);
    }

}
