package com.espaciosdeportivos.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PagoDTO implements Serializable {
    //J
    private Long idPago;

    @NotNull(message = "El monto no puede ser nulo")
    @DecimalMin(value = "0.01", message = "El monto debe ser mayor a 0")
    private Double monto;

    @NotNull(message = "La fecha del pago es obligatoria")
    @PastOrPresent(message = "La fecha del pago no puede ser futura")
    private LocalDate fecha;

    @NotBlank(message = "El tipo de pago no puede estar vacío")
    @Size(max = 50, message = "El tipo de pago no puede exceder los 50 caracteres")
    private String tipoPago;

    @NotBlank(message = "El método de pago no puede estar vacío")
    @Size(max = 50, message = "El método de pago no puede exceder los 50 caracteres")
    private String metodoPago;

    @NotBlank(message = "El estado del pago no puede estar vacío")
    @Size(max = 30, message = "El estado del pago no puede exceder los 30 caracteres")
    private String estado;

    @NotNull(message = "El ID de la reserva es obligatorio")
    private Long idReserva;
}
