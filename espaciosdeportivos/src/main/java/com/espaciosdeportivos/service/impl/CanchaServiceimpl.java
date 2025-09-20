package com.espaciosdeportivos.service.impl;

import com.espaciosdeportivos.dto.CanchaDTO;
import com.espaciosdeportivos.model.Cancha;
import com.espaciosdeportivos.model.AreaDeportiva;
import com.espaciosdeportivos.repository.CanchaRepository;
import com.espaciosdeportivos.repository.AreaDeportivaRepository;
import com.espaciosdeportivos.service.ICanchaService;
import com.espaciosdeportivos.validation.CanchaValidator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.validation.Valid;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CanchaServiceimpl implements ICanchaService {

    private final CanchaRepository canchaRepository;
    private final AreaDeportivaRepository areaDeportivaRepository;
    private final CanchaValidator canchaValidator;

    @Override
    @Transactional(readOnly = true)
    public List<CanchaDTO> obtenerTodasLasCanchas() {
        return canchaRepository.findByEstadoboolTrue()
                .stream()
                .map(this::toDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public CanchaDTO obtenerCanchaPorId(Long id) {
        Cancha cancha = canchaRepository.findByIdCanchaAndEstadoboolTrue(id)
                .orElseThrow(() -> new RuntimeException("Cancha no encontrada con ID: " + id));
        return toDto(cancha);
    }

    @Override
    public CanchaDTO crearCancha(@Valid CanchaDTO dto) {
        canchaValidator.validarCancha(dto);

        AreaDeportiva area = areaDeportivaRepository.findById(dto.getIdAreadeportiva())
                .orElseThrow(() -> new RuntimeException("Área deportiva no encontrada con ID: " + dto.getIdAreadeportiva()));

        Cancha entidad = toEntity(dto);
        entidad.setIdCancha(null);
        entidad.setEstadobool(Boolean.TRUE);
        entidad.setAreaDeportiva(area);

        return toDto(canchaRepository.save(entidad));
    }

    @Override
    public CanchaDTO actualizarCancha(Long id, @Valid CanchaDTO dto) {
        Cancha existente = canchaRepository.findByIdCanchaAndEstadoboolTrue(id)
                .orElseThrow(() -> new RuntimeException("Cancha no encontrada con ID: " + id));

        canchaValidator.validarCancha(dto);

        AreaDeportiva area = areaDeportivaRepository.findById(dto.getIdAreadeportiva())
                .orElseThrow(() -> new RuntimeException("Área deportiva no encontrada con ID: " + dto.getIdAreadeportiva()));

        existente.setNombre(dto.getNombre());
        existente.setCostoHora(dto.getCostoHora());
        existente.setCapacidad(dto.getCapacidad());
        existente.setEstado(dto.getEstado());
        existente.setEstadobool(dto.getEstadobool());
        existente.setMantenimiento(dto.getMantenimiento());
        existente.setHoraInicio(dto.getHoraInicio());
        existente.setHoraFin(dto.getHoraFin());
        existente.setTipoSuperficie(dto.getTipoSuperficie());
        existente.setTamano(dto.getTamano());
        existente.setIluminacion(dto.getIluminacion());
        existente.setCubierta(dto.getCubierta());
        existente.setUrlImagen(dto.getUrlImagen());
        existente.setAreaDeportiva(area);

        return toDto(canchaRepository.save(existente));
    }

    @Override
    public CanchaDTO eliminarCancha(Long id) {
        Cancha existente = canchaRepository.findByIdCanchaAndEstadoboolTrue(id)
                .orElseThrow(() -> new RuntimeException("Cancha no encontrada con ID: " + id));
        existente.setEstadobool(Boolean.FALSE); // baja lógica
        return toDto(canchaRepository.save(existente));
    }

    // --------- mapping ----------
    private CanchaDTO toDto(Cancha c) {
        return CanchaDTO.builder()
                .idCancha(c.getIdCancha())
                .nombre(c.getNombre())
                .costoHora(c.getCostoHora())
                .capacidad(c.getCapacidad())
                .estado(c.getEstado())
                .estadobool(c.getEstadobool())
                .mantenimiento(c.getMantenimiento())
                .horaInicio(c.getHoraInicio())
                .horaFin(c.getHoraFin())
                .tipoSuperficie(c.getTipoSuperficie())
                .tamano(c.getTamano())
                .iluminacion(c.getIluminacion())
                .cubierta(c.getCubierta())
                .urlImagen(c.getUrlImagen())
                .idAreadeportiva(c.getAreaDeportiva() != null ? c.getAreaDeportiva().getIdAreaDeportiva() : null)
                .build();
    }

    private Cancha toEntity(CanchaDTO d) {
        return Cancha.builder()
                .idCancha(d.getIdCancha())
                .nombre(d.getNombre())
                .costoHora(d.getCostoHora())
                .capacidad(d.getCapacidad())
                .estado(d.getEstado())
                .estadobool(d.getEstadobool() != null ? d.getEstadobool() : Boolean.TRUE)
                .mantenimiento(d.getMantenimiento())
                .horaInicio(d.getHoraInicio())
                .horaFin(d.getHoraFin())
                .tipoSuperficie(d.getTipoSuperficie())
                .tamano(d.getTamano())
                .iluminacion(d.getIluminacion())
                .cubierta(d.getCubierta())
                .urlImagen(d.getUrlImagen())
                .build();
    }
}
