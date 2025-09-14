package com.espaciosdeportivos.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "administrador")
@PrimaryKeyJoinColumn(name = "id_administrador")
public class Administrador extends Persona {

    @Column(name = "cargo", nullable = false, length = 100)
    private String cargo;

    @Column(name = "direccion", nullable = false, length = 200)
    private String direccion;

    @OneToMany(mappedBy = "administrador", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AreaDeportiva> areaDeportiva;
}