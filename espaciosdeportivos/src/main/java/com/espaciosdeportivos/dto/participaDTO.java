package com.espaciosdeportivos.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;
//J
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class participaDTO implements Serializable {

    @NotNull(message = "El ID del invitado es obligatorio")
    private Long idInvitado;

    @NotNull(message = "El ID de la reserva es obligatorio")
    private Long idReserva;
}
