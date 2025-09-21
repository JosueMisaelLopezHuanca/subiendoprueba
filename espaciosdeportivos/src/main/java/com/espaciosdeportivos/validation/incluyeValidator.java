package com.espaciosdeportivos.validation;

import com.espaciosdeportivos.dto.incluyeDTO;
import org.springframework.stereotype.Component;

@Component
public class incluyeValidator {
    //J
    public void validarRelacion(incluyeDTO dto) {
        if (dto.getIdCancha() == null || dto.getIdCancha() <= 0) {
            throw new BusinessException("Debe proporcionar un ID de cancha válido.");
        }

        if (dto.getIdReserva() == null || dto.getIdReserva() <= 0) {
            throw new BusinessException("Debe proporcionar un ID de reserva válido.");
        }

        if (dto.getIdDisciplina() == null || dto.getIdDisciplina() <= 0) {
            throw new BusinessException("Debe proporcionar un ID de disciplina válido.");
        }

        // Aquí puedes agregar lógica adicional para validar existencia en DB si usas un repositorio
    }

    public static class BusinessException extends RuntimeException {
        public BusinessException(String message) {
            super(message);
        }
    }
}
