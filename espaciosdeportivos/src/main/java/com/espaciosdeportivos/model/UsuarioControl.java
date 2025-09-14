package com.espaciosdeportivos.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.*;
import lombok.experimental.SuperBuilder;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "usuario_control")
@PrimaryKeyJoinColumn(name = "id_us_control")
public class UsuarioControl extends Persona {

    @Column(name = "estado_operativo", nullable = false, length = 50)
    private String estadoOperativo;

    @Column(name = "hora_inicio_turno", nullable = false)
    private LocalDateTime horaInicioTurno;

    @Column(name = "hora_fin_turno", nullable = false)
    private LocalDateTime horaFinTurno;

    @Column(name = "direccion", nullable = false, length = 200)
    private String direccion;

    
    //K
    @OneToMany(mappedBy = "usuarioControl", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Qr> qr;


}