package com.espaciosdeportivos.service.impl;

import com.espaciosdeportivos.dto.EquipamientoDTO;
import com.espaciosdeportivos.model.Equipamiento;
import com.espaciosdeportivos.repository.EquipamientoRepository;
import com.espaciosdeportivos.service.IEquipamientoService;
import com.espaciosdeportivos.validation.EquipamientoValidator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.validation.Valid;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class EquipamientoServiceimpl implements IEquipamientoService {

    private final EquipamientoRepository equipamientoRepository;
    private final EquipamientoValidator equipamientoValidator;

    @Override
    @Transactional(readOnly = true)
    public List<EquipamientoDTO> obtenerTodosLosEquipamientos() {
        return equipamientoRepository.findByEstadoboolTrue()
                .stream()
                .map(this::toDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public EquipamientoDTO obtenerEquipamientoPorId(Long id) {
        Equipamiento eq = equipamientoRepository.findByIdequipamientoAndEstadoboolTrue(id)
                .orElseThrow(() -> new RuntimeException("Equipamiento no encontrado con ID: " + id));
        return toDto(eq);
    }

    @Override
    public EquipamientoDTO crearEquipamiento(@Valid EquipamientoDTO dto) {
        equipamientoValidator.validarEquipamiento(dto);

        // Unicidad por nombre (opcional, descomenta si quieres reforzar)
        // if (equipamientoRepository.existsByNombreequipamientoIgnoreCase(dto.getNombre())) {
        //     throw new EquipamientoValidator.BusinessException("Ya existe un equipamiento con ese nombre.");
        // }

        Equipamiento entidad = toEntity(dto);
        entidad.setIdequipamiento(null);
        entidad.setEstadobool(Boolean.TRUE);

        return toDto(equipamientoRepository.save(entidad));
    }

    @Override
    public EquipamientoDTO actualizarEquipamiento(Long id, @Valid EquipamientoDTO dto) {
        Equipamiento existente = equipamientoRepository.findByIdequipamientoAndEstadoboolTrue(id)
                .orElseThrow(() -> new RuntimeException("Equipamiento no encontrado con ID: " + id));

        equipamientoValidator.validarEquipamiento(dto);

        // Unicidad por nombre ignorando el mismo id (opcional)
        // equipamientoRepository.findByNombreequipamientoIgnoreCase(dto.getNombre())
        //     .filter(e -> !e.getIdequipamiento().equals(id))
        //     .ifPresent(e -> { throw new EquipamientoValidator.BusinessException("Ya existe un equipamiento con ese nombre."); });

        existente.setNombreequipamiento(dto.getNombre());
        existente.setTipoequipamiento(dto.getTipo());
        existente.setDescripcion(dto.getDescripcion());
        existente.setEstado(dto.getEstado());
        existente.setUrlimagen(dto.getUrlImagen());
        existente.setEstadobool(dto.getEstadobool());

        return toDto(equipamientoRepository.save(existente));
    }

    @Override
    public EquipamientoDTO eliminarEquipamiento(Long id) {
        Equipamiento existente = equipamientoRepository.findByIdequipamientoAndEstadoboolTrue(id)
                .orElseThrow(() -> new RuntimeException("Equipamiento no encontrado con ID: " + id));
        existente.setEstadobool(Boolean.FALSE); // baja lógica
        return toDto(equipamientoRepository.save(existente));
    }

    // ---------- mapping ----------
    private EquipamientoDTO toDto(Equipamiento e) {
        return EquipamientoDTO.builder()
                .idEquipamiento(e.getIdequipamiento())
                .nombre(e.getNombreequipamiento())
                .tipo(e.getTipoequipamiento())
                .descripcion(e.getDescripcion())
                .estado(e.getEstado())
                .urlImagen(e.getUrlimagen())
                .estadobool(e.getEstadobool())
                .build();
    }

    private Equipamiento toEntity(EquipamientoDTO d) {
        return Equipamiento.builder()
                .idequipamiento(d.getIdEquipamiento())
                .nombreequipamiento(d.getNombre())
                .tipoequipamiento(d.getTipo())
                .descripcion(d.getDescripcion())
                .estado(d.getEstado())
                .urlimagen(d.getUrlImagen())
                .estadobool(d.getEstadobool() != null ? d.getEstadobool() : Boolean.TRUE)
                .build();
    }
}
