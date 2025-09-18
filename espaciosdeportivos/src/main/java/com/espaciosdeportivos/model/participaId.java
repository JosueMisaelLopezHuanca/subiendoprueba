package com.espaciosdeportivos.model;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class participaId implements Serializable {

    private Long idInvitado;
    private Long idReserva;
}
