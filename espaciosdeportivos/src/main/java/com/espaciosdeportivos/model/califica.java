package com.espaciosdeportivos.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "califica")
public class califica {

    @EmbeddedId
    private calificaId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idCancha") //  campo en calificaId
    @JoinColumn(name = "id_cancha", referencedColumnName = "id_cancha")
    private Cancha cancha;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idComentario") 
    @JoinColumn(name = "id_comentario", referencedColumnName = "id_comentario")
    private Comentario comentario;
}
