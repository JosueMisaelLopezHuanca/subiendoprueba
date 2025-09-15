package com.espaciosdeportivos.model;

import java.util.List;
import lombok.*;
import lombok.experimental.SuperBuilder;
import jakarta.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "invitado")
@PrimaryKeyJoinColumn(name = "id_invitado")
public class Invitado extends Persona {

    @Column(name = "verificado", nullable = false)
    private Boolean verificado;

     //k
    @OneToMany(mappedBy = "invitado", cascade = CascadeType.ALL, orphanRemoval = true) 
    private List<Qr> qr;
}