package edu.pe.cibertec.hotelazul.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "huesped")
@Getter
@Setter
@NoArgsConstructor
public class Huesped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 8, nullable = false, unique = true)
    private String dni;

    @Column(length = 60, nullable = false)
    private String nombres;

    @Column(length = 60, nullable = false)
    private String apellidos;

    @Column(length = 9)
    private String telefono;

    @Column(length = 80)
    private String correo;

}
