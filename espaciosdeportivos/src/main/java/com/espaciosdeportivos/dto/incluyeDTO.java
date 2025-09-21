package com.espaciosdeportivos.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;
//J
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class incluyeDTO implements Serializable {

    @NotNull(message = "El ID de la cancha es obligatorio")
    private Long idCancha;

    @NotNull(message = "El ID de la reserva es obligatorio")
    private Long idReserva;

    @NotNull(message = "El ID de la disciplina es obligatorio")
    private Long idDisciplina;
}
