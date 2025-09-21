package com.espaciosdeportivos.validation;

import com.espaciosdeportivos.dto.ReservaDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Component
public class ReservaValidator {
    //J
    private static final List<String> ESTADOS_VALIDOS = List.of("PENDIENTE", "CONFIRMADA", "CANCELADA");

    public void validarReserva(ReservaDTO dto) {
        validarFechas(dto.getFechaCreacion(), dto.getFechaReserva());
        validarHoras(dto.getHoraInicio(), dto.getHoraFin());
        validarEstado(dto.getEstadoReserva());
        validarMonto(dto.getMontoTotal());
        validarCliente(dto.getClienteId());
    }

    public void validarFechas(LocalDate fechaCreacion, LocalDate fechaReserva) {
        if (fechaCreacion == null || fechaReserva == null) {
            throw new BusinessException("Las fechas no pueden ser nulas.");
        }
        if (fechaCreacion.isAfter(LocalDate.now())) {
            throw new BusinessException("La fecha de creación no puede ser futura.");
        }
        if (fechaReserva.isBefore(LocalDate.now())) {
            throw new BusinessException("La fecha de la reserva no puede ser en el pasado.");
        }
        if (fechaReserva.isBefore(fechaCreacion)) {
            throw new BusinessException("La fecha de la reserva no puede ser anterior a la fecha de creación.");
        }
    }

    public void validarHoras(LocalTime inicio, LocalTime fin) {
        if (inicio == null || fin == null) {
            throw new BusinessException("Las horas de inicio y fin son obligatorias.");
        }
        if (!inicio.isBefore(fin)) {
            throw new BusinessException("La hora de inicio debe ser anterior a la hora de fin.");
        }
    }

    public void validarEstado(String estado) {
        if (estado == null || estado.isBlank()) {
            throw new BusinessException("El estado de la reserva es obligatorio.");
        }
        if (!ESTADOS_VALIDOS.contains(estado.toUpperCase())) {
            throw new BusinessException("Estado inválido. Solo se permiten: " + ESTADOS_VALIDOS);
        }
    }

    public void validarMonto(Double monto) {
        if (monto == null || monto <= 0) {
            throw new BusinessException("El monto total debe ser mayor a 0.");
        }
    }

    public void validarCliente(Long clienteId) {
        if (clienteId == null || clienteId <= 0) {
            throw new BusinessException("Debe especificar un cliente válido.");
        }
    }

    public static class BusinessException extends RuntimeException {
        public BusinessException(String message) {
            super(message);
        }
    }
}
