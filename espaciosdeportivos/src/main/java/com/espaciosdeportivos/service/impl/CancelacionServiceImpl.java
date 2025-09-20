package com.espaciosdeportivos.service.impl;

import com.espaciosdeportivos.dto.CancelacionDTO;
import com.espaciosdeportivos.model.Cancelacion;
// import com.espaciosdeportivos.model.Cliente;
// import com.espaciosdeportivos.model.Reserva;
import com.espaciosdeportivos.repository.CancelacionRepository;
// import com.espaciosdeportivos.repository.ClienteRepository;
// import com.espaciosdeportivos.repository.ReservaRepository;
import com.espaciosdeportivos.service.ICancelacionService;
import com.espaciosdeportivos.validation.CancelacionValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CancelacionServiceImpl implements ICancelacionService {

    private final CancelacionRepository cancelacionRepository;
    private final CancelacionValidator cancelacionValidator;

    // Repositorios comentados temporalmente
    // private final ClienteRepository clienteRepository;
    // private final ReservaRepository reservaRepository;

    @Autowired
    public CancelacionServiceImpl(
            CancelacionRepository cancelacionRepository,
            CancelacionValidator cancelacionValidator
            // ,ClienteRepository clienteRepository,
            // ReservaRepository reservaRepository
    ) {
        this.cancelacionRepository = cancelacionRepository;
        this.cancelacionValidator = cancelacionValidator;
        // this.clienteRepository = clienteRepository;
        // this.reservaRepository = reservaRepository;
    }

    @Override
    public List<CancelacionDTO> obtenerTodasLasCancelaciones() {
        return cancelacionRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public CancelacionDTO obtenerCancelacionPorId(Long id) {
        Cancelacion cancelacion = cancelacionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cancelación no encontrada con ID: " + id));
        return convertToDTO(cancelacion);
    }

    @Override
    @Transactional
    public CancelacionDTO crearCancelacion(CancelacionDTO cancelacionDTO) {
        cancelacionValidator.validarCancelacion(cancelacionDTO);

        Cancelacion cancelacion = convertToEntity(cancelacionDTO);
        cancelacion.setEstado(true); // por defecto activa

        Cancelacion guardada = cancelacionRepository.save(cancelacion);
        return convertToDTO(guardada);
    }

    @Override
    @Transactional
    public CancelacionDTO actualizarCancelacion(Long id, CancelacionDTO cancelacionDTO) {
        Cancelacion existente = cancelacionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cancelación no encontrada con ID: " + id));

        cancelacionValidator.validarCancelacion(cancelacionDTO);

        existente.setMotivo(cancelacionDTO.getMotivo());
        existente.setFechaCancelacion(cancelacionDTO.getFechaCancelacion());
        existente.setHoraCancelacion(cancelacionDTO.getHoraCancelacion());
        existente.setEstado(cancelacionDTO.getEstado());

        Cancelacion actualizada = cancelacionRepository.save(existente);
        return convertToDTO(actualizada);
    }

    @Override
    @Transactional
    public CancelacionDTO eliminarCancelacion(Long id) {
        Cancelacion existente = cancelacionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cancelación no encontrada con ID: " + id));

        existente.setEstado(false); // baja lógica
        Cancelacion eliminada = cancelacionRepository.save(existente);

        return convertToDTO(eliminada);
    }

    @Override
    @Transactional
    public Cancelacion obtenerCancelacionConBloqueo(Long id) {
        Cancelacion cancelacion = cancelacionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cancelación no encontrada con ID: " + id));
        try {
            Thread.sleep(15000); // simula espera
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return cancelacion;
    }

    @Override
    @Transactional
    public void eliminarCancelacionFisicamente(Long id) {
        Cancelacion existente = cancelacionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cancelación no encontrada con ID: " + id));
        cancelacionRepository.delete(existente);
    }

    private CancelacionDTO convertToDTO(Cancelacion cancelacion) {
        return CancelacionDTO.builder()
                .idCancelacion(cancelacion.getIdCancelacion())
                .motivo(cancelacion.getMotivo())
                .fechaCancelacion(cancelacion.getFechaCancelacion())
                .horaCancelacion(cancelacion.getHoraCancelacion())
                .estado(cancelacion.getEstado())
                // .idCliente(cancelacion.getCliente() != null ? cancelacion.getCliente().getIdPersona() : null)
                // .idReserva(cancelacion.getReserva() != null ? cancelacion.getReserva().getIdReserva() : null)
                .build();
    }

    private Cancelacion convertToEntity(CancelacionDTO dto) {
        // Cliente cliente = clienteRepository.findById(dto.getIdCliente())
        //         .orElseThrow(() -> new RuntimeException("Cliente no encontrado con ID: " + dto.getIdCliente()));

        // Reserva reserva = reservaRepository.findById(dto.getIdReserva())
        //         .orElseThrow(() -> new RuntimeException("Reserva no encontrada con ID: " + dto.getIdReserva()));

        return Cancelacion.builder()
                .idCancelacion(dto.getIdCancelacion())
                .motivo(dto.getMotivo())
                .fechaCancelacion(dto.getFechaCancelacion())
                .horaCancelacion(dto.getHoraCancelacion())
                .estado(dto.getEstado())
                // .cliente(cliente)
                // .reserva(reserva)
                .build();
    }
}
