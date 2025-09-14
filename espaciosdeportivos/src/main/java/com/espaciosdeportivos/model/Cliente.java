package com.espaciosdeportivos.model;

import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "cliente")
@PrimaryKeyJoinColumn(name = "id_cliente")
public class Cliente extends Persona {

    @Column(name = "estado_cliente", nullable = false, length = 50)
    private String estadoCliente;

    public Cliente() {}

    public String getEstadoCliente() {
        return estadoCliente;
    }

    public void setEstadoCliente(String estadoCliente) {
        this.estadoCliente = estadoCliente;
    }
    //k
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = false)
    private List<Cancelacion> cancelacion;
}