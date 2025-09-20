package com.espaciosdeportivos.service.impl;


import com.espaciosdeportivos.dto.MacrodistritoDTO;
import com.espaciosdeportivos.dto.QrDTO;
import com.espaciosdeportivos.model.Macrodistrito;
import com.espaciosdeportivos.repository.MacrodistritoRepository;

import com.espaciosdeportivos.service.IMacrodistritoService;
import com.espaciosdeportivos.validation.MacrodistritoValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MacrodistritoServiceimpl implements IMacrodistritoService {
    private final MacrodistritoRepository macrodistritoRepository;
    private final MacrodistritoValidator macrodistritoValidator;

    @Autowired
    public MacrodistritoServiceimpl(
            MacrodistritoRepository macrodistritoRepository,
            MacrodistritoValidator macrodistritoValidator
    ) {
        this.macrodistritoRepository = macrodistritoRepository;
        this.macrodistritoValidator = macrodistritoValidator;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Macrodistrito> obtenerTodosLosMacrodistritos() {
        return macrodistritoRepository.findByEstadoTrue()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public MacrodistritoDTO obtenerMacrodistritoPorId(Long idMacrodistrito) {
        Macrodistrito macrodistrito = macrodistritoRepository.findById(idMacrodistrito)
                .orElseThrow(() -> new RuntimeException("Macrodistrito no encontrado con ID: " + idMacrodistrito));
        return convertToDTO(macrodistrito);
    }
    @Override
    @Transactional
    public MacrodistritoDTO crearMacrodistrito(@Valid MacrodistritoDTO macrodistritoDTO) {
        macrodistritoValidator.validarMacrodistrito(macrodistritoDTO);

        Macrodistrito macrodistrito = convertToEntity(macrodistritoDTO);

        Macrodistrito macrodistritoGuardado = macrodistritoRepository.save(macrodistrito);
        return convertToDTO(macrodistritoGuardado);
    }

    @Override
    @Transactional
    public MacrodistritoDTO actualizarMacrodistrito(Long idMacrodistrito, MacrodistritoDTO macrodistritoDTO) {
        Macrodistrito macrodistritoExistente = macrodistritoRepository.findById(idMacrodistrito)
                .orElseThrow(() -> new RuntimeException("Macrodistrito no encontrado con ID: " + idMacrodistrito));

        macrodistritoValidator.validarMacrodistrito(macrodistritoDTO);

        macrodistritoExistente.setNombre(macrodistritoDTO.getNombre());
        macrodistritoExistente.setDescripcion(macrodistritoDTO.getDescripcion());

        Macrodistrito macrodistritoActualizado = macrodistritoRepository.save(macrodistritoExistente);
        return convertToDTO(macrodistritoActualizado);
    }

    @Override
    @Transactional
    public MacrodistritoDTO eliminarMacrodistrito(Long idMacrodistrito) {
        Macrodistrito macrodistritoExistente = macrodistritoRepository.findById(idMacrodistrito)
                .orElseThrow(() -> new RuntimeException("Macrodistrito no encontrado con ID: " + idMacrodistrito));

        Macrodistrito macrodistritoEliminado = macrodistritoRepository.save(macrodistritoExistente);

        return convertToDTO(macrodistritoEliminado);
    }

    private MacrodistritoDTO convertToDTO(Macrodistrito macrodistrito) {
        return MacrodistritoDTO.builder()
                .idMacrodistrito(macrodistrito.getIdMacrodistrito())
                .nombre(macrodistrito.getNombre())
                .descripcion(macrodistrito.getDescripcion())
                .build();
    }

    private Macrodistrito convertToEntity(MacrodistritoDTO dto) {
        return Macrodistrito.builder()
                .idMacrodistrito(dto.getIdMacrodistrito())
                .nombre(dto.getNombre())
                .descripcion(dto.getDescripcion())
                .build();
    }
}
