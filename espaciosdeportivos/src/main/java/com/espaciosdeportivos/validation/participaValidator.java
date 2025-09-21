package com.espaciosdeportivos.validation;


import com.espaciosdeportivos.dto.participaDTO;
import org.springframework.stereotype.Component;

@Component
public class participaValidator {

    public void validarParticipacion(participaDTO dto) {
        if (dto.getIdInvitado() == null || dto.getIdInvitado() <= 0) {
            throw new BusinessException("Debe proporcionar un ID de invitado válido.");
        }

        if (dto.getIdReserva() == null || dto.getIdReserva() <= 0) {
            throw new BusinessException("Debe proporcionar un ID de reserva válido.");
        }
        //J
        // Aquí podrías verificar si ya existe la participación en la DB
    }

    public static class BusinessException extends RuntimeException {
        public BusinessException(String message) {
            super(message);
        }
    }
}
