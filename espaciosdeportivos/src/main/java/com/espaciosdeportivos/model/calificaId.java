package com.espaciosdeportivos.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Embeddable
public class calificaId implements Serializable {

    @Column(name = "id_cancha", nullable = false)
    private Long idCancha;

    @Column(name = "id_comentario", nullable = false)
    private Long idComentario;
}
