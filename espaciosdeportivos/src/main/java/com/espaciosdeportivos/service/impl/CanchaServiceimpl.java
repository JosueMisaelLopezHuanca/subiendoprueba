package com.espaciosdeportivos.service.impl;

import com.espaciosdeportivos.dto.CanchaDTO;
import com.espaciosdeportivos.model.Cancha;
import com.espaciosdeportivos.model.AreaDeportiva;
import com.espaciosdeportivos.repository.CanchaRepository;
import com.espaciosdeportivos.repository.AreaDeportivaRepository;
import com.espaciosdeportivos.service.ICanchaService;
import com.espaciosdeportivos.validation.CanchaValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CanchaServiceImpl implements ICanchaService {
    private final CanchaRepository canchaRepository;
    private final AreaDeportivaRepository areaDeportivaRepository;
    private final CanchaValidator canchaValidator;

    @Autowired
    public CanchaServiceImpl(
            CanchaRepository canchaRepository,
            AreaDeportivaRepository areaDeportivaRepository,
            CanchaValidator canchaValidator
    ) {
        this.canchaRepository = canchaRepository;
        this.areaDeportivaRepository = areaDeportivaRepository;
        this.canchaValidator = canchaValidator;
    }

    @Override
    @Transactional(readOnly = true)
    public List<CanchaDTO> obtenerTodasLasCanchas() {
        return canchaRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public CanchaDTO obtenerCanchaPorId(Long id) {
        Cancha cancha = canchaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cancha no encontrada con ID: " + id));
        return convertToDTO(cancha);
    }

    @Override
    @Transactional
    public CanchaDTO crearCancha(@Valid CanchaDTO dto) {
        canchaValidator.validarCancha(dto);

        AreaDeportiva area = areaDeportivaRepository.findById(dto.getIdAreadeportiva())
                .orElseThrow(() -> new RuntimeException("Área deportiva no encontrada con ID: " + dto.getIdAreadeportiva()));

        Cancha cancha = convertToEntity(dto);
        cancha.setAreaDeportiva(area);

        Cancha guardada = canchaRepository.save(cancha);
        return convertToDTO(guardada);
    }

    @Override
    @Transactional
    public CanchaDTO actualizarCancha(Long id, @Valid CanchaDTO dto) {
        Cancha existente = canchaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cancha no encontrada con ID: " + id));
        canchaValidator.validarCancha(dto);

        AreaDeportiva area = areaDeportivaRepository.findById(dto.getIdAreadeportiva())
                .orElseThrow(() -> new RuntimeException("Área deportiva no encontrada con ID: " + dto.getIdAreadeportiva()));

        existente.setNombre(dto.getNombre());
        existente.setCostoHora(dto.getCostoHora());
        existente.setCapacidad(dto.getCapacidad());
        existente.setEstado(dto.getEstado());
        existente.setMantenimiento(dto.getMantenimiento());
        existente.setHoraInicio(dto.getHoraInicio());
        existente.setHoraFin(dto.getHoraFin());
        existente.setTipoSuperficie(dto.getTipoSuperficie());
        existente.setTamaño(dto.getTamaño());
        existente.setIluminacion(dto.getIluminacion());
        existente.setCubierta(dto.getCubierta());
        existente.setUrlImagen(dto.getUrlImagen());
        existente.setAreaDeportiva(area);

        Cancha actualizada = canchaRepository.save(existente);
        return convertToDTO(actualizada);
    }

    @Override
    @Transactional
    public CanchaDTO eliminarCancha(Long id) {
        Cancha existente = canchaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cancha no encontrada con ID: " + id));
        canchaRepository.delete(existente);
        return convertToDTO(existente);
    }

    // Métodos privados de conversión
    private CanchaDTO convertToDTO(Cancha cancha) {
        return CanchaDTO.builder()
                .idCancha(cancha.getIdCancha())
                .nombre(cancha.getNombre())
                .costoHora(cancha.getCostoHora())
                .capacidad(cancha.getCapacidad())
                .estado(cancha.getEstado())
                .mantenimiento(cancha.getMantenimiento())
                .horaInicio(cancha.getHoraInicio())
                .horaFin(cancha.getHoraFin())
                .tipoSuperficie(cancha.getTipoSuperficie())
                .tamaño(cancha.getTamaño())
                .iluminacion(cancha.getIluminacion())
                .cubierta(cancha.getCubierta())
                .urlImagen(cancha.getUrlImagen())
                .idAreadeportiva(cancha.getAreaDeportiva() != null ? cancha.getAreaDeportiva().getIdAreaDeportiva() : null)
                .build();
    }

    private Cancha convertToEntity(CanchaDTO dto) {
        return Cancha.builder()
                .idCancha(dto.getIdCancha())
                .nombre(dto.getNombre())
                .costoHora(dto.getCostoHora())
                .capacidad(dto.getCapacidad())
                .estado(dto.getEstado())
                .mantenimiento(dto.getMantenimiento())
                .horaInicio(dto.getHoraInicio())
                .horaFin(dto.getHoraFin())
                .tipoSuperficie(dto.getTipoSuperficie())
                .tamaño(dto.getTamaño())
                .iluminacion(dto.getIluminacion())
                .cubierta(dto.getCubierta())
                .urlImagen(dto.getUrlImagen())
                .build();
    }
}