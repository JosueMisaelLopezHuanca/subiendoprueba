package com.espaciosdeportivos.model;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "incluye")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Incluye {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /*
    @ManyToOne
    @JoinColumn(name = "id_cancha", nullable = false)
    private Cancha cancha;
    */

    @ManyToOne
    @JoinColumn(name = "id_reserva", nullable = false)
    private Reserva reserva;

    @ManyToOne
    @JoinColumn(name = "id_disciplina", nullable = false)
    private Disciplina disciplina;
}
