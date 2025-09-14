package com.espaciosdeportivos.model;

import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "invitado")
@PrimaryKeyJoinColumn(name = "id_invitado")
public class Invitado extends Persona {

    @Column(name = "verificado", nullable = false)
    private Boolean verificado;

    public Invitado() {}

    public Boolean getVerificado() {
        return verificado;
    }

  
     //k
    @OneToMany(mappedBy = "invitado", cascade = CascadeType.ALL, orphanRemoval = true) 
    private List<Qr> qr;
}