package com.espaciosdeportivos.service.impl;

import com.espaciosdeportivos.dto.ZonaDTO;
import com.espaciosdeportivos.model.Macrodistrito;
import com.espaciosdeportivos.model.Zona;
import com.espaciosdeportivos.repository.MacrodistritoRepository;
import com.espaciosdeportivos.repository.ZonaRepository;
import com.espaciosdeportivos.service.IZonaService;
import com.espaciosdeportivos.validation.ZonaValidator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.validation.Valid;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional

public class ZonaServiceimpl implements IZonaService {
    private final ZonaRepository zonaRepository;
    private final MacrodistritoRepository macrodistritoRepository;
    private final ZonaValidator zonaValidator;

    @Override
    @Transactional(readOnly = true)
    public List<ZonaDTO> obtenerTodasLasZonas() {
        return zonaRepository.findByEstadoTrue()
                .stream()
                .map(this::convertToDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public ZonaDTO obtenerZonaPorId(Long idZona) {
        Zona zona = zonaRepository.findByIdZonaAndEstadoTrue(idZona)
                .orElseThrow(() -> new RuntimeException("Zona no encontrada con ID: " + idZona));
        return convertToDTO(zona);
    }

    @Override
    public ZonaDTO crearZona(@Valid ZonaDTO dto) {
        zonaValidator.validarZona(dto);

        Macrodistrito macro = macrodistritoRepository.findById(dto.getIdMacrodistrito())
                .orElseThrow(() -> new RuntimeException("Macrodistrito no encontrado con ID: " + dto.getIdMacrodistrito()));

        Zona entidad = toEntity(dto, macro);
        entidad.setIdZona(null);
        entidad.setEstado(Boolean.TRUE);

        return convertToDTO(zonaRepository.save(entidad));
    }

    @Override
    public ZonaDTO actualizarZona(Long idZona, @Valid ZonaDTO dto) {
        Zona existente = zonaRepository.findByIdZonaAndEstadoTrue(idZona)
                .orElseThrow(() -> new RuntimeException("Zona no encontrada con ID: " + idZona));

        zonaValidator.validarZona(dto);

        // Permitir cambiar de macrodistrito si el dto lo trae
        Macrodistrito macro = macrodistritoRepository.findById(dto.getIdMacrodistrito())
                .orElseThrow(() -> new RuntimeException("Macrodistrito no encontrado con ID: " + dto.getIdMacrodistrito()));

        existente.setNombre(dto.getNombre());
        existente.setDescripcion(dto.getDescripcion());
        existente.setEstado(dto.getEstado());
        existente.setMacrodistrito(macro);

        return convertToDTO(zonaRepository.save(existente));
    }

    @Override
    public ZonaDTO eliminarZona(Long idZona) {
        Zona existente = zonaRepository.findByIdZonaAndEstadoTrue(idZona)
                .orElseThrow(() -> new RuntimeException("Zona no encontrada con ID: " + idZona));
        existente.setEstado(Boolean.FALSE);
        return convertToDTO(zonaRepository.save(existente));
    }

    // ---------- mapping ----------
    private ZonaDTO convertToDTO(Zona z) {
        return ZonaDTO.builder()
                .idZona(z.getIdZona())
                .nombre(z.getNombre())
                .descripcion(z.getDescripcion())
                .estado(z.getEstado())
                .idMacrodistrito(z.getMacrodistrito() != null ? z.getMacrodistrito().getIdMacrodistrito() : null)
                .build();
    }

    private Zona toEntity(ZonaDTO d, Macrodistrito macro) {
        return Zona.builder()
                .idZona(d.getIdZona())
                .nombre(d.getNombre())
                .descripcion(d.getDescripcion())
                .estado(d.getEstado() != null ? d.getEstado() : Boolean.TRUE)
                .macrodistrito(macro)
                .build();
    }
}