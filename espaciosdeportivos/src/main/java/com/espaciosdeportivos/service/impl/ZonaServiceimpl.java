package com.espaciosdeportivos.service.impl;

import com.espaciosdeportivos.dto.ZonaDTO;
import com.espaciosdeportivos.model.Zona;
import com.espaciosdeportivos.repository.ZonaRepository;
import com.espaciosdeportivos.service.IZonaService;
import com.espaciosdeportivos.validation.ZonaValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ZonaServiceImpl implements IZonaService {
    private final ZonaRepository zonaRepository;
    private final ZonaValidator zonaValidator;

    @Autowired
    public ZonaServiceImpl(ZonaRepository zonaRepository, ZonaValidator zonaValidator) {
        this.zonaRepository = zonaRepository;
        this.zonaValidator = zonaValidator;
    }

    @Override
    @Transactional(readOnly = true)
    public List<String> obtenerTodasLasZonas() {
        return zonaRepository.findAll()
                .stream()
                .map(Zona::getNombre)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public ZonaDTO obtenerZonaPorId(Long idZona) {
        Zona zona = zonaRepository.findById(idZona)
                .orElseThrow(() -> new RuntimeException("Zona no encontrada con ID: " + idZona));
        return convertToDTO(zona);
    }

    @Override
    @Transactional
    public ZonaDTO crearZona(@Valid ZonaDTO zonaDTO) {
        zonaValidator.validarZona(zonaDTO);
        Zona zona = convertToEntity(zonaDTO);
        Zona guardada = zonaRepository.save(zona);
        return convertToDTO(guardada);
    }

    @Override
    @Transactional
    public ZonaDTO actualizarZona(Long idZona, @Valid ZonaDTO zonaDTO) {
        Zona existente = zonaRepository.findById(idZona)
                .orElseThrow(() -> new RuntimeException("Zona no encontrada con ID: " + idZona));
        zonaValidator.validarZona(zonaDTO);
        existente.setNombre(zonaDTO.getNombre());
        existente.setDescripcion(zonaDTO.getDescripcion());
        Zona actualizada = zonaRepository.save(existente);
        return convertToDTO(actualizada);
    }

    @Override
    @Transactional
    public ZonaDTO eliminarZona(Long idZona) {
        Zona existente = zonaRepository.findById(idZona)
                .orElseThrow(() -> new RuntimeException("Zona no encontrada con ID: " + idZona));
        zonaRepository.delete(existente);
        return convertToDTO(existente);
    }

    // Métodos privados de conversión
    private ZonaDTO convertToDTO(Zona zona) {
        return ZonaDTO.builder()
                .idZona(zona.getIdZona())
                .nombre(zona.getNombre())
                .descripcion(zona.getDescripcion())
                .build();
    }

    private Zona convertToEntity(ZonaDTO dto) {
        return Zona.builder()
                .idZona(dto.getIdZona())
                .nombre(dto.getNombre())
                .descripcion(dto.getDescripcion())
                .build();
    }
}