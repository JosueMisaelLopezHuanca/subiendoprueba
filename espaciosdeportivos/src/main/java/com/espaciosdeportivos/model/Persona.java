package com.espaciosdeportivos.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "persona")
@Inheritance(strategy = InheritanceType.JOINED)
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_persona")
    private Long idPersona;

    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;

    private String nombre;

    @Column(name = "a_paterno")
    private String aPaterno;

    @Column(name = "a_materno")
    private String aMaterno;

    private String telefono;
    private String email;
    private String ci;

    

    //k
    @OneToMany(mappedBy = "persona", cascade = CascadeType.ALL, orphanRemoval = false)
    private List<Comentario> comentario;

}