package com.espaciosdeportivos.service.impl;

import com.espaciosdeportivos.dto.QrDTO;
import com.espaciosdeportivos.model.Qr;
// import com.espaciosdeportivos.model.Reserva;
// import com.espaciosdeportivos.model.Invitado;
// import com.espaciosdeportivos.model.UsuarioControl;
import com.espaciosdeportivos.repository.QrRepository;
// import com.espaciosdeportivos.repository.ReservaRepository;
// import com.espaciosdeportivos.repository.InvitadoRepository;
// import com.espaciosdeportivos.repository.UsuarioControlRepository;
import com.espaciosdeportivos.service.IQrService;
import com.espaciosdeportivos.validation.QrValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QrServiceImpl implements IQrService {

    private final QrRepository qrRepository;
    private final QrValidator qrValidator;

    // Repositorios comentados temporalmente
    // private final ReservaRepository reservaRepository;
    // private final InvitadoRepository invitadoRepository;
    // private final UsuarioControlRepository usuarioControlRepository;

    @Autowired
    public QrServiceImpl(
            QrRepository qrRepository,
            QrValidator qrValidator
            // ,ReservaRepository reservaRepository,
            // InvitadoRepository invitadoRepository,
            // UsuarioControlRepository usuarioControlRepository
    ) {
        this.qrRepository = qrRepository;
        this.qrValidator = qrValidator;
        // this.reservaRepository = reservaRepository;
        // this.invitadoRepository = invitadoRepository;
        // this.usuarioControlRepository = usuarioControlRepository;
    }
    //Pregunta si quiero obtner todos los qr por la parte administrativa
    @Override
    public List<QrDTO> obtenerTodosLosQrs() {
        return qrRepository.findByEstadoTrue()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public QrDTO obtenerQrPorId(Long id) {
        Qr qr = qrRepository.findByIdQrAndEstadoTrue(id)
                .orElseThrow(() -> new RuntimeException("QR no encontrado con ID: " + id));
        return convertToDTO(qr);
    }

    @Override
    @Transactional
    public QrDTO crearQr(QrDTO qrDTO) {
        qrValidator.validarQr(qrDTO);

        Qr qr = convertToEntity(qrDTO);
        qr.setEstado(true); // por defecto activo

        Qr qrGuardado = qrRepository.save(qr);
        return convertToDTO(qrGuardado);
    }

    @Override
    @Transactional
    public QrDTO actualizarQr(Long id, QrDTO qrDTO) {
        Qr qrExistente = qrRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("QR no encontrado con ID: " + id));

        qrValidator.validarQr(qrDTO);

        qrExistente.setCodigoQr(qrDTO.getCodigoQr());
        qrExistente.setFechaGeneracion(qrDTO.getFechaGeneracion());
        qrExistente.setFechaExpiracion(qrDTO.getFechaExpiracion());
        qrExistente.setEstado(qrDTO.getEstado());

        Qr qrActualizado = qrRepository.save(qrExistente);
        return convertToDTO(qrActualizado);
    }

    @Override
    @Transactional
    public QrDTO eliminarQr(Long id) {
        Qr qrExistente = qrRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("QR no encontrado con ID: " + id));

        qrExistente.setEstado(false); // baja lógica
        Qr qrEliminado = qrRepository.save(qrExistente);

        return convertToDTO(qrEliminado);
    }

    @Override
    @Transactional
    public Qr obtenerQrConBloqueo(Long id) {
        Qr qr = qrRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("QR no encontrado con ID: " + id));
        try {
            Thread.sleep(15000); // simula espera
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return qr;
    }

    @Override
    @Transactional
    public void eliminarQrFisicamente(Long id) {
        Qr qrExistente = qrRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("QR no encontrado con ID: " + id));
        qrRepository.delete(qrExistente);
    }

    private QrDTO convertToDTO(Qr qr) {
        return QrDTO.builder()
                .idQr(qr.getIdQr())
                .codigoQr(qr.getCodigoQr())
                .fechaGeneracion(qr.getFechaGeneracion())
                .fechaExpiracion(qr.getFechaExpiracion())
                .estado(qr.getEstado())
                // .idReserva(qr.getReserva() != null ? qr.getReserva().getIdReserva() : null)
                // .idInvitado(qr.getInvitado() != null ? qr.getInvitado().getIdPersona() : null)
                // .idUsuarioControl(qr.getUsuarioControl() != null ? qr.getUsuarioControl().getIdPersona() : null)
                .build();
    }

    private Qr convertToEntity(QrDTO dto) {
        // Reserva reserva = reservaRepository.findById(dto.getIdReserva())
        //         .orElseThrow(() -> new RuntimeException("Reserva no encontrada con ID: " + dto.getIdReserva()));

        // Invitado invitado = invitadoRepository.findById(dto.getIdInvitado())
        //         .orElseThrow(() -> new RuntimeException("Invitado no encontrado con ID: " + dto.getIdInvitado()));

        // UsuarioControl usuarioControl = usuarioControlRepository.findById(dto.getIdUsuarioControl())
        //         .orElseThrow(() -> new RuntimeException("UsuarioControl no encontrado con ID: " + dto.getIdUsuarioControl()));

        return Qr.builder()
                .idQr(dto.getIdQr())
                .codigoQr(dto.getCodigoQr())
                .fechaGeneracion(dto.getFechaGeneracion())
                .fechaExpiracion(dto.getFechaExpiracion())
                .estado(dto.getEstado())
                // .reserva(reserva)
                // .invitado(invitado)
                // .usuarioControl(usuarioControl)
                .build();
    }
}
