package edu.pe.cibertec.hotelazul.entity;

import java.math.BigDecimal;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "habitacion")
@Getter
@Setter
@NoArgsConstructor
public class Habitacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 10, nullable = false, unique = true)
    private String numero;

    @Column(nullable = false)
    private Integer piso;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal precio;

    @Column(length = 255)
    private String urlImagen;

    @Column(length = 255)
    private String descripcion;

    // DISPONIBLE, OCUPADA o MANTENIMIENTO
    @Column(length = 20, nullable = false)
    private String estado = "DISPONIBLE";

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tipo_habitacion_id", nullable = false)
    private TipoHabitacion tipo;

}
