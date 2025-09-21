package com.espaciosdeportivos.validation;

import com.espaciosdeportivos.dto.PagoDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class PagoValidator {
    //J
    private static final List<String> TIPOS_PAGO_VALIDOS = List.of("parcial", "total");
    private static final List<String> METODOS_PAGO_VALIDOS = List.of("efectivo", "tarjeta", "transferencia");
    private static final List<String> ESTADOS_VALIDOS = List.of("pendiente", "confirmado", "anulado");

    public void validarPago(PagoDTO dto) {
        validarMonto(dto.getMonto());
        validarFecha(dto.getFecha());
        validarTipoPago(dto.getTipoPago());
        validarMetodoPago(dto.getMetodoPago());
        validarEstado(dto.getEstado());
        validarIdReserva(dto.getIdReserva());
    }

    public void validarMonto(Double monto) {
        if (monto == null || monto <= 0) {
            throw new BusinessException("El monto debe ser mayor a 0.");
        }
    }

    public void validarFecha(LocalDate fecha) {
        if (fecha == null || fecha.isAfter(LocalDate.now())) {
            throw new BusinessException("La fecha del pago no puede ser nula ni futura.");
        }
    }

    public void validarTipoPago(String tipoPago) {
        if (tipoPago == null || !TIPOS_PAGO_VALIDOS.contains(tipoPago.toLowerCase())) {
            throw new BusinessException("Tipo de pago inválido. Opciones permitidas: " + TIPOS_PAGO_VALIDOS);
        }
    }

    public void validarMetodoPago(String metodoPago) {
        if (metodoPago == null || !METODOS_PAGO_VALIDOS.contains(metodoPago.toLowerCase())) {
            throw new BusinessException("Método de pago inválido. Opciones permitidas: " + METODOS_PAGO_VALIDOS);
        }
    }

    public void validarEstado(String estado) {
        if (estado == null || !ESTADOS_VALIDOS.contains(estado.toLowerCase())) {
            throw new BusinessException("Estado de pago inválido. Opciones permitidas: " + ESTADOS_VALIDOS);
        }
    }

    public void validarIdReserva(Long idReserva) {
        if (idReserva == null || idReserva <= 0) {
            throw new BusinessException("El ID de reserva debe ser válido.");
        }
    }

    public static class BusinessException extends RuntimeException {
        public BusinessException(String message) {
            super(message);
        }
    }
}
