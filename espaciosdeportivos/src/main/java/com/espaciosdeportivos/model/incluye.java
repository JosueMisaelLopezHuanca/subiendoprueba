package com.espaciosdeportivos.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "incluye")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class incluye {

    @EmbeddedId
    private incluyeId id;

    @ManyToOne
    @MapsId("idCancha")
    @JoinColumn(name = "id_cancha", nullable = false)
    private Cancha cancha;

    @ManyToOne
    @MapsId("idReserva")
    @JoinColumn(name = "id_reserva", nullable = false)
    private Reserva reserva;

    @ManyToOne
    @MapsId("idDisciplina")
    @JoinColumn(name = "id_disciplina", nullable = false)
    private Disciplina disciplina;
}
