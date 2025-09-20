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
@Table(name = "participa")
public class participa {

    @EmbeddedId
    private participaId id;
    //J
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idInvitado") // se enlaza con la PK compuesta
    @JoinColumn(name = "id_invitado", referencedColumnName = "id_invitado")
    private Invitado invitado;
    //J
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idReserva") // se enlaza con la PK compuesta
    @JoinColumn(name = "id_reserva", referencedColumnName = "id_reserva")
    private Reserva reserva;
}
