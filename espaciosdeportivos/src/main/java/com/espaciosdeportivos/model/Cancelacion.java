package com.espaciosdeportivos.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "cancelacion")
public class Cancelacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cancelacion")
    private Long id_cancelacion;

    @Column(name = "fecha_cancelacion", nullable = false)
    private LocalDate fecha_cancelacion;

    @Column(name = "hora_cancelacion", nullable = false)
    private LocalTime hora_cancelacion;

    @Column(name = "razon_cancelacion", nullable = false, length = 500)
    private String razon_cancelacion;

    // Relación con Reserva
    /*@ManyToOne
    @JoinColumn(name = "id_reserva", nullable = false)
    private Reserva reserva; */

    // Relacin con Cliente
    @ManyToOne
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente cliente;

   
}
