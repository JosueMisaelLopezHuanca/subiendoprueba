package com.espaciosdeportivos.service.impl;

import com.espaciosdeportivos.dto.MacrodistritoDTO;
import com.espaciosdeportivos.model.Macrodistrito;
import com.espaciosdeportivos.repository.MacrodistritoRepository;
import com.espaciosdeportivos.service.IMacrodistritoService;
import com.espaciosdeportivos.validation.MacrodistritoValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    public List<MacrodistritoDTO> obtenerTodosLosMacrodistritos() {
        return macrodistritoRepository.findByEstadoTrue()
                .stream()
                .map(this::convertToDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public MacrodistritoDTO obtenerMacrodistritoPorId(Long idMacrodistrito) {
        Macrodistrito macrodistrito = macrodistritoRepository.findByIdMacrodistritoAndEstadoTrue(idMacrodistrito)
                .orElseThrow(() -> new RuntimeException("Macrodistrito no encontrado con ID: " + idMacrodistrito));
        return convertToDTO(macrodistrito);
    }

    @Override
    @Transactional
    public MacrodistritoDTO crearMacrodistrito(MacrodistritoDTO dto) {
        macrodistritoValidator.validarMacrodistrito(dto);
        // opcional: reforzar unicidad
        // if (macrodistritoRepository.existsByNombreIgnoreCase(dto.getNombre())) {
        //     throw new MacrodistritoValidator.BusinessException("Ya existe un macrodistrito con ese nombre.");
        // }

        Macrodistrito entidad = convertToEntity(dto);
        entidad.setIdMacrodistrito(null);
        entidad.setEstado(Boolean.TRUE);

        return convertToDTO(macrodistritoRepository.save(entidad));
    }

    @Override
    @Transactional
    public MacrodistritoDTO actualizarMacrodistrito(Long id, MacrodistritoDTO dto) {
        Macrodistrito existente = macrodistritoRepository.findByIdMacrodistritoAndEstadoTrue(id)
                .orElseThrow(() -> new RuntimeException("Macrodistrito no encontrado con ID: " + id));

        macrodistritoValidator.validarMacrodistrito(dto);
        // opcional: unicidad ignorando el mismo id
        // macrodistritoRepository.findByNombreIgnoreCase(dto.getNombre())
        //         .filter(m -> !m.getIdMacrodistrito().equals(id))
        //         .ifPresent(m -> { throw new MacrodistritoValidator.BusinessException("Ya existe un macrodistrito con ese nombre."); });

        existente.setNombre(dto.getNombre());
        existente.setDescripcion(dto.getDescripcion());
        existente.setEstado(dto.getEstado());

        return convertToDTO(macrodistritoRepository.save(existente));
    }

    @Override
    @Transactional
    public MacrodistritoDTO eliminarMacrodistrito(Long id) {
        Macrodistrito existente = macrodistritoRepository.findByIdMacrodistritoAndEstadoTrue(id)
                .orElseThrow(() -> new RuntimeException("Macrodistrito no encontrado con ID: " + id));
        existente.setEstado(Boolean.FALSE);
        return convertToDTO(macrodistritoRepository.save(existente));
    }

    // --------- mapping ----------
    private MacrodistritoDTO convertToDTO(Macrodistrito m) {
        return MacrodistritoDTO.builder()
                .idMacrodistrito(m.getIdMacrodistrito())
                .nombre(m.getNombre())
                .descripcion(m.getDescripcion())
                .estado(m.getEstado())
                .build();
    }

    private Macrodistrito convertToEntity(MacrodistritoDTO d) {
        return Macrodistrito.builder()
                .idMacrodistrito(d.getIdMacrodistrito())
                .nombre(d.getNombre())
                .descripcion(d.getDescripcion())
                .estado(d.getEstado() != null ? d.getEstado() : Boolean.TRUE)
                .build();
    }
}
