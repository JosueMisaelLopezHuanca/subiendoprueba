package com.espaciosdeportivos.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "reserva")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reserva")
    private Long idReserva;

    @Column(name = "fecha_creacion", nullable = false)
    private LocalDate fechaCreacion;

    @Column(name = "fecha_reserva", nullable = false)
    private LocalDate fechaReserva;

    @Column(name = "hora_inicio", nullable = false)
    private LocalTime horaInicio;

    @Column(name = "hora_fin", nullable = false)
    private LocalTime horaFin;

    @Column(name = "estado_reserva", nullable = false, length = 50)
    private String estadoReserva;

    @Column(name = "monto_total", nullable = false)
    private Double montoTotal;

    @Column(name = "observaciones", length = 500)
    private String observaciones;

    //J
    // Relación con Cliente
    @ManyToOne
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente cliente;
    //J
    // Relación con Pago (1 reserva puede tener muchos pagos)
    @OneToMany(mappedBy = "reserva", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Pago> pagos;
    //J
    // Relación con QR
    @OneToMany(mappedBy = "reserva", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Qr> codigosQr;
    //J
    // Relación con Cancha (a través de la tabla intermedia "incluye")
    @OneToMany(mappedBy = "reserva", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<incluyee> canchasIncluidas;
    //J
    /*// Relación con Invitado (a través de la tabla intermedia "participa")
    @OneToMany(mappedBy = "reserva", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Participa> invitados;*/
    //J
    // Relación con Cancelación (una reserva puede ser cancelada)
    @OneToOne(mappedBy = "reserva", cascade = CascadeType.ALL, orphanRemoval = true)
    private Cancelacion cancelacion;
}
