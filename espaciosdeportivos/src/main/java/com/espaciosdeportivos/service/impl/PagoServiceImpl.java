package com.espaciosdeportivos.service.impl;

import com.espaciosdeportivos.dto.PagoDTO;
import com.espaciosdeportivos.model.Pago;
import com.espaciosdeportivos.model.Reserva;
import com.espaciosdeportivos.repository.PagoRepository;
import com.espaciosdeportivos.repository.ReservaRepository;
import com.espaciosdeportivos.service.IPagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PagoServiceImpl implements IPagoService {

    @Autowired
    private PagoRepository pagoRepository;

    @Autowired
    private ReservaRepository reservaRepository;

    private PagoDTO mapToDTO(Pago pago) {
        return PagoDTO.builder()
                .idPago(pago.getIdPago())
                .monto(pago.getMonto())
                .fecha(pago.getFecha())
                .tipoPago(pago.getTipoPago())
                .metodoPago(pago.getMetodoPago())
                .estado(pago.getEstado())
                .idReserva(pago.getReserva().getIdReserva())
                .build();
    }

    private Pago mapToEntity(PagoDTO dto) {
        Reserva reserva = reservaRepository.findById(dto.getIdReserva())
                .orElseThrow(() -> new EntityNotFoundException("Reserva no encontrada con id: " + dto.getIdReserva()));

        return Pago.builder()
                .monto(dto.getMonto())
                .fecha(dto.getFecha())
                .tipoPago(dto.getTipoPago())
                .metodoPago(dto.getMetodoPago())
                .estado(dto.getEstado())
                .reserva(reserva)
                .build();
    }

    @Override
    public PagoDTO crear(PagoDTO dto) {
        Pago pago = mapToEntity(dto);
        return mapToDTO(pagoRepository.save(pago));
    }

    @Override
    public PagoDTO actualizar(Long id, PagoDTO dto) {
        Pago pago = pagoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pago no encontrado con id: " + id));

        Reserva reserva = reservaRepository.findById(dto.getIdReserva())
                .orElseThrow(() -> new EntityNotFoundException("Reserva no encontrada con id: " + dto.getIdReserva()));

        pago.setMonto(dto.getMonto());
        pago.setFecha(dto.getFecha());
        pago.setTipoPago(dto.getTipoPago());
        pago.setMetodoPago(dto.getMetodoPago());
        pago.setEstado(dto.getEstado());
        pago.setReserva(reserva);

        return mapToDTO(pagoRepository.save(pago));
    }

    @Override
    public PagoDTO obtenerPorId(Long id) {
        Pago pago = pagoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pago no encontrado con id: " + id));
        return mapToDTO(pago);
    }

    @Override
    public void eliminar(Long id) {
        if (!pagoRepository.existsById(id)) {
            throw new EntityNotFoundException("Pago no encontrado con id: " + id);
        }
        pagoRepository.deleteById(id);
    }

    @Override
    public List<PagoDTO> listarTodos() {
        return pagoRepository.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public List<PagoDTO> buscarPorEstado(String estado) {
        return pagoRepository.findByEstado(estado).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public List<PagoDTO> buscarPorMetodo(String metodoPago) {
        return pagoRepository.findByMetodoPago(metodoPago).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public List<PagoDTO> buscarPorTipo(String tipoPago) {
        return pagoRepository.findByTipoPago(tipoPago).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public List<PagoDTO> buscarPorFecha(LocalDate fecha) {
        return pagoRepository.findByFecha(fecha).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public List<PagoDTO> buscarPorRangoFechas(LocalDate inicio, LocalDate fin) {
        return pagoRepository.findByFechaBetween(inicio, fin).stream().map(this::mapToDTO).collect(Collectors.toList());
    }
}
