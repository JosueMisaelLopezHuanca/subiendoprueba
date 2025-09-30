package com.espaciosdeportivos.repository;

import com.espaciosdeportivos.model.Qr;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QrRepository extends JpaRepository<Qr, Long> {

    // Verifica si existe un código QR 
    Boolean existsByCodigoQrIgnoreCase(String codigoQr);

    // Busca todos los QRs activos (estado = true)
    List<Qr> findByEstadoTrue();

    // Busca un QR por su código, solo si está activo
    Optional<Qr> findByCodigoQrIgnoreCaseAndEstadoTrue(String codigoQr);

    // Busca un QR por su ID
    Optional<Qr> findByIdQrAndEstadoTrue(Long idQr);

    // Busca QRs por ID de reserva
    List<Qr> findByReserva_IdReserva(Long idReserva);

    // Busca QRs por ID de invitado
    List<Qr> findByInvitado_IdPersona(Long idInvitado);

    // Busca QRs por ID de usuario de control
    List<Qr> findByUsuarioControl_IdPersona(Long idUsuarioControl);

    //k pa front
    List<Qr> findByReservaIdReserva(Long idReserva);

}
