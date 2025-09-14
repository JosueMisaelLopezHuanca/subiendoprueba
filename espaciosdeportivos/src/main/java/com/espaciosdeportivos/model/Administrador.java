package com.espaciosdeportivos.model;

import jakarta.persistence.*;

@Entity
@Table(name = "administrador")
@PrimaryKeyJoinColumn(name = "id_administrador")
public class Administrador extends Persona {

    @Column(name = "cargo", nullable = false, length = 100)
    private String cargo;

    @Column(name = "direccion", nullable = false, length = 200)
    private String direccion;

    public Administrador() {}

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}