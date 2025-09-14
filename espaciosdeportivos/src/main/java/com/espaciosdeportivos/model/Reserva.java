package com.espaciosdeportivos.model;


import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "reserva")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idReserva;

    private LocalDate fechaCreacion;
    private LocalDate fechaReserva;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private String estadoReserva;
    private Double montoTotal;
    private String observaciones;

    /*@ManyToOne
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente cliente;*/
}
