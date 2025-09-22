package com.espaciosdeportivos.validation;


import com.espaciosdeportivos.dto.AreaDeportivaDTO;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Component
public class AreaDeportivaValidator {

    public void validarNombre(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            throw new BusinessException("El nombre del área es obligatorio.");
        }
        if (nombre.length() > 100) {
            throw new BusinessException("El nombre del área no puede exceder 100 caracteres.");
        }
    }

    public void validarDescripcion(String descripcion) {
        if (descripcion != null && descripcion.length() > 400) {
            throw new BusinessException("La descripción no puede exceder 400 caracteres.");
        }
    }

    public void validarHoras(LocalTime inicio, LocalTime fin) {
        if (inicio == null || fin == null) {
            throw new BusinessException("Las horas de inicio y fin son obligatorias.");
        }
        if (!fin.isAfter(inicio)) {
            throw new BusinessException("La hora de fin debe ser posterior a la hora de inicio.");
        }
    }

    public void validarEstadoArea(String estadoArea) {
        if (estadoArea == null || estadoArea.isBlank()) {
            throw new BusinessException("El estado del área (texto) es obligatorio.");
        }
        if (estadoArea.length() > 100) {
            throw new BusinessException("El estado del área no puede exceder 100 caracteres.");
        }
        // Opcional: validar contra un conjunto permitido (ABIERTA/CERRADA/MANTENIMIENTO)
    }

    public void validarCoordenadas(Double lat, Double lng) {
        if (lat == null || lng == null) {
            throw new BusinessException("Las coordenadas (latitud/longitud) son obligatorias.");
        }
        if (lat < -90 || lat > 90) {
            throw new BusinessException("La latitud debe estar entre -90 y 90.");
        }
        if (lng < -180 || lng > 180) {
            throw new BusinessException("La longitud debe estar entre -180 y 180.");
        }
    }

    public void validarIds(Long idZona, Long idAdmin) {
        if (idZona == null || idZona <= 0) {
            throw new BusinessException("El ID de la zona debe ser positivo.");
        }
        if (idAdmin == null || idAdmin <= 0) {
            throw new BusinessException("El ID del administrador debe ser positivo.");
        }
    }

    public void validarEstado(Boolean estado) {
        if (estado == null) {
            throw new BusinessException("El estado (soft delete) es obligatorio.");
        }
    }

    public void validarArea(AreaDeportivaDTO dto) {
        validarNombre(dto.getNombreArea());
        validarDescripcion(dto.getDescripcionArea());
        validarHoras(dto.getHoraInicioArea(), dto.getHoraFinArea());
        validarEstadoArea(dto.getEstadoArea());
        validarCoordenadas(dto.getLatitud(), dto.getLongitud());
        validarIds(dto.getIdZona(), dto.getIdAdministrador());
        validarEstado(dto.getEstado());
    }

    public static class BusinessException extends RuntimeException {
        public BusinessException(String message) { super(message); }
    }
}
