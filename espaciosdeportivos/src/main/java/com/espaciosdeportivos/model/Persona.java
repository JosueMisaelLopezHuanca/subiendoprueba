package com.espaciosdeportivos.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;
import lombok.*;

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

    // ===== Getters y Setters =====
    public Long getIdPersona() { return idPersona; }
    public void setIdPersona(Long idPersona) { this.idPersona = idPersona; }

    public LocalDate getFechaNacimiento() { return fechaNacimiento; }
    public void setFechaNacimiento(LocalDate fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getaPaterno() { return aPaterno; }
    public void setaPaterno(String aPaterno) { this.aPaterno = aPaterno; }

    public String getaMaterno() { return aMaterno; }
    public void setaMaterno(String aMaterno) { this.aMaterno = aMaterno; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getCi() { return ci; }
    public void setCi(String ci) { this.ci = ci; }

    //k
    @OneToMany(mappedBy = "persona", cascade = CascadeType.ALL, orphanRemoval = false)
    private List<Comentario> comentario;

    //prueba
}