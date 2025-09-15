package com.espaciosdeportivos.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "sepractica")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class sepractica {

    @EmbeddedId
    private sepracticaId id;

    @ManyToOne
    @MapsId("idCancha") // vincula con la clave compuesta
    @JoinColumn(name = "id_cancha", nullable = false)
    private Cancha cancha;

    @ManyToOne
    @MapsId("idDisciplina") // vincula con la clave compuesta
    @JoinColumn(name = "id_disciplina", nullable = false)
    private Disciplina disciplina;
}
