package com.espaciosdeportivos.service.impl;

import com.espaciosdeportivos.dto.ReservaDTO;
import com.espaciosdeportivos.model.Cliente;
import com.espaciosdeportivos.model.Reserva;
import com.espaciosdeportivos.repository.ReservaRepository;
import com.espaciosdeportivos.repository.ClienteRepository;
import com.espaciosdeportivos.service.IReservaService;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ReservaServiceImpl implements IReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    private ReservaDTO mapToDTO(Reserva reserva) {
        return ReservaDTO.builder()
                .idReserva(reserva.getIdReserva())
                .fechaCreacion(reserva.getFechaCreacion())
                .fechaReserva(reserva.getFechaReserva())
                .horaInicio(reserva.getHoraInicio())
                .horaFin(reserva.getHoraFin())
                .estadoReserva(reserva.getEstadoReserva())
                .montoTotal(reserva.getMontoTotal())
                .observaciones(reserva.getObservaciones())
                .clienteId(reserva.getCliente().getIdPersona())
                .build();
    }

    private Reserva mapToEntity(ReservaDTO dto) {
        Cliente cliente = clienteRepository.findById(dto.getClienteId())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con ID: " + dto.getClienteId()));

        return Reserva.builder()
                .idReserva(dto.getIdReserva()) // ← clave para PUT
                .fechaCreacion(dto.getFechaCreacion())
                .fechaReserva(dto.getFechaReserva())
                .horaInicio(dto.getHoraInicio())
                .horaFin(dto.getHoraFin())
                .estadoReserva(dto.getEstadoReserva())
                .montoTotal(dto.getMontoTotal())
                .observaciones(dto.getObservaciones())
                .cliente(cliente)
                .build();
    }

    @Override
    public List<ReservaDTO> listarTodas() {
        return reservaRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ReservaDTO obtenerPorId(Long id) {
        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reserva no encontrada con ID: " + id));
        return mapToDTO(reserva);
    }

    @Override
    public ReservaDTO crear(ReservaDTO dto) {
        validarFechaReserva(dto.getFechaReserva());

        if (!reservaRepository.findByFechaReservaAndHoraInicioBeforeAndHoraFinAfter(
                dto.getFechaReserva(), dto.getHoraFin(), dto.getHoraInicio()
        ).isEmpty()) {
            throw new RuntimeException("Existe solapamiento de horarios para esa fecha.");
        }

        Reserva reserva = mapToEntity(dto);
        return mapToDTO(reservaRepository.save(reserva));
    }

    @Override
    public ReservaDTO actualizar(Long id, ReservaDTO dto) {
        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reserva no encontrada con ID: " + id));

        validarFechaReserva(dto.getFechaReserva());

        reserva.setFechaCreacion(dto.getFechaCreacion());
        reserva.setFechaReserva(dto.getFechaReserva());
        reserva.setHoraInicio(dto.getHoraInicio());
        reserva.setHoraFin(dto.getHoraFin());
        reserva.setEstadoReserva(dto.getEstadoReserva());
        reserva.setMontoTotal(dto.getMontoTotal());
        reserva.setObservaciones(dto.getObservaciones());

        Cliente cliente = clienteRepository.findById(dto.getClienteId())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con ID: " + dto.getClienteId()));
        reserva.setCliente(cliente);

        return mapToDTO(reservaRepository.save(reserva));
    }

    @Override
    public void eliminar(Long id) {
        if (!reservaRepository.existsById(id)) throw new RuntimeException("Reserva no encontrada");
        reservaRepository.deleteById(id);
    }

    @Override
    public List<ReservaDTO> buscarPorCliente(Long idCliente) {
        return reservaRepository.findByCliente_IdPersona(idCliente)
                .stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public List<ReservaDTO> buscarPorEstado(String estado) {
        return reservaRepository.findByEstadoReserva(estado)
                .stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public List<ReservaDTO> buscarPorRangoFechas(LocalDate inicio, LocalDate fin) {
        return reservaRepository.findByFechaReservaBetween(inicio, fin)
                .stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public void validarFechaReserva(LocalDate fechaReserva) {
        if (fechaReserva.isBefore(LocalDate.now())) {
            throw new RuntimeException("No se puede reservar una fecha pasada");
        }
    }
}
