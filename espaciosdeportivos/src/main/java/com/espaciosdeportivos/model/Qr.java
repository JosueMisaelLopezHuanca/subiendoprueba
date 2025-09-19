package com.espaciosdeportivos.model;

import lombok.*;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "qr")
public class Qr {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_qr")
    private Long id_qr;

    @Column(name = "codigo_qr", nullable = false, unique = true, length = 200)
    private String codigo_qr;

    @Column(name = "fecha_generacion", nullable = false)
    private LocalDateTime fecha_generacion;

    @Column(name = "fecha_expiracion", nullable = false)
    private LocalDateTime fecha_expiracion;

    @Column(name = "estado", nullable = false)
    private boolean estado;


    @Column(name = "descripcion", length = 255)
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "id_us_control", referencedColumnName = "id_persona")
    private UsuarioControl usuarioControl;

    // Relación con Reserva -> se agrega cuando definas esa entidad
    //J
    @ManyToOne
    @JoinColumn(name = "id_reserva", referencedColumnName = "id_reserva")
    private Reserva reserva;

    @ManyToOne
    @JoinColumn(name = "id_invitado", referencedColumnName = "id_persona")
    private Invitado invitado;
}
