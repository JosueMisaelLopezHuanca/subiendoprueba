package com.espaciosdeportivos.validation;

import com.espaciosdeportivos.dto.sepracticaDTO;
import org.springframework.stereotype.Component;

@Component
public class sepracticaValidator {

    public void validarRelacion(sepracticaDTO dto) {
        if (dto.getIdCancha() == null || dto.getIdCancha() <= 0) {
            throw new BusinessException("Debe proporcionar un ID de cancha válido.");
        }

        if (dto.getIdDisciplina() == null || dto.getIdDisciplina() <= 0) {
            throw new BusinessException("Debe proporcionar un ID de disciplina válido.");
        }
        //Por si las moscas
        //J
        // Ejemplo adicional: podrías verificar si ya existe la relación
        // mediante una consulta en el repositorio
    }

    public static class BusinessException extends RuntimeException {
        public BusinessException(String message) {
            super(message);
        }
    }
}
