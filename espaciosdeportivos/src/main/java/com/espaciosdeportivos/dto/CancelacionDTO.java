package com.espaciosdeportivos.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CancelacionDTO implements Serializable {

    private Long idCancelacion;

    @NotNull(message = "La fecha de cancelación es obligatoria")
    private LocalDate fechaCancelacion;

    @NotNull(message = "La hora de cancelación es obligatoria")
    private LocalTime horaCancelacion;

    @NotBlank(message = "La razón de cancelación es obligatoria")
    @Size(max = 500, message = "La razón no puede exceder los 500 caracteres")
    private String razonCancelacion;

    @NotNull(message = "El ID de la reserva es obligatorio")
    private Long idReserva;

    @NotNull(message = "El ID del cliente es obligatorio")
    private Long idCliente;
}
