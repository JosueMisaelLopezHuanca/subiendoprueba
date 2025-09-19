package com.espaciosdeportivos.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
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

    @NotNull
    @Column(name = "fecha_nacimiento", nullable = false)
    private LocalDate fechaNacimiento;
    
    @NotNull
    @Column(nullable = false)
    private String nombre;

    @NotNull
    @Column(name = "a_paterno")
    private String aPaterno;

    @Column(name = "a_materno", nullable = false)
    private String aMaterno;

    @NotNull
    @Column(nullable = false)
    private String telefono;

    @Email
    @NotNull
    @Column(nullable = false)
    private String email;
    
    private String ci;

    

    //k
    @OneToMany(mappedBy = "persona", cascade = CascadeType.ALL, orphanRemoval = false)
    private List<Comentario> comentario;

}