package com.espaciosdeportivos.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class sepracticaDTO implements Serializable {
    //J
    @NotNull(message = "El ID de la cancha es obligatorio")
    private Long idCancha;

    @NotNull(message = "El ID de la disciplina es obligatorio")
    private Long idDisciplina;
}
