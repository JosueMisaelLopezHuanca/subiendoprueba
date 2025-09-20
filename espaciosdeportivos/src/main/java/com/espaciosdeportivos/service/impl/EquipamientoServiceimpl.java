package com.espaciosdeportivos.service.impl;

import com.espaciosdeportivos.dto.EquipamientoDTO;
import com.espaciosdeportivos.model.Equipamiento;
import com.espaciosdeportivos.repository.EquipamientoRepository;
import com.espaciosdeportivos.service.IEquipamientoService;
import com.espaciosdeportivos.validation.EquipamientoValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EquipamientoServiceImpl implements IEquipamientoService {
    private final EquipamientoRepository equipamientoRepository;
    private final EquipamientoValidator equipamientoValidator;

    @Autowired
    public EquipamientoServiceImpl(
            EquipamientoRepository equipamientoRepository,
            EquipamientoValidator equipamientoValidator
    ) {
        this.equipamientoRepository = equipamientoRepository;
        this.equipamientoValidator = equipamientoValidator;
    }

    @Override
    @Transactional(readOnly = true)
    public List<EquipamientoDTO> obtenerTodosLosEquipamientos() {
        return equipamientoRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public EquipamientoDTO obtenerEquipamientoPorId(Long id) {
        Equipamiento equipamiento = equipamientoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Equipamiento no encontrado con ID: " + id));
        return convertToDTO(equipamiento);
    }

    @Override
    @Transactional
    public EquipamientoDTO crearEquipamiento(@Valid EquipamientoDTO dto) {
        equipamientoValidator.validarEquipamiento(dto);
        Equipamiento equipamiento = convertToEntity(dto);
        Equipamiento guardado = equipamientoRepository.save(equipamiento);
        return convertToDTO(guardado);
    }

    @Override
    @Transactional
    public EquipamientoDTO actualizarEquipamiento(Long id, @Valid EquipamientoDTO dto) {
        Equipamiento existente = equipamientoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Equipamiento no encontrado con ID: " + id));
        equipamientoValidator.validarEquipamiento(dto);

        existente.setNombreequipamiento(dto.getNombre());
        existente.setTipoequipamiento(dto.getTipo());
        existente.setDescripcion(dto.getDescripcion());
        existente.setEstado(dto.getEstado());
        existente.setUrlimagen(dto.getUrlImagen());

        Equipamiento actualizado = equipamientoRepository.save(existente);
        return convertToDTO(actualizado);
    }

    @Override
    @Transactional
    public EquipamientoDTO eliminarEquipamiento(Long id) {
        Equipamiento existente = equipamientoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Equipamiento no encontrado con ID: " + id));
        equipamientoRepository.delete(existente);
        return convertToDTO(existente);
    }

    // Métodos privados de conversión
    private EquipamientoDTO convertToDTO(Equipamiento equipamiento) {
        return EquipamientoDTO.builder()
                .idEquipamiento(equipamiento.getIdequipamiento())
                .nombre(equipamiento.getNombreequipamiento())
                .tipo(equipamiento.getTipoequipamiento())
                .descripcion(equipamiento.getDescripcion())
                .estado(equipamiento.getEstado())
                .urlImagen(equipamiento.getUrlimagen())
                .build();
    }

    private Equipamiento convertToEntity(EquipamientoDTO dto) {
        return Equipamiento.builder()
                .idequipamiento(dto.getIdEquipamiento())
                .nombreequipamiento(dto.getNombre())
                .tipoequipamiento(dto.getTipo())
                .descripcion(dto.getDescripcion())
                .estado(dto.getEstado())
                .urlimagen(dto.getUrlImagen())
                .build();
    }
}