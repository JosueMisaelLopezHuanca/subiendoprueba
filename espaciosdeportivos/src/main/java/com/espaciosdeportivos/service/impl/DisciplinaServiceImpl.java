package com.espaciosdeportivos.service.impl;

import com.espaciosdeportivos.dto.DisciplinaDTO;
import com.espaciosdeportivos.model.Disciplina;
import com.espaciosdeportivos.repository.DisciplinaRepository;
import com.espaciosdeportivos.service.IDisciplinaService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class DisciplinaServiceImpl implements IDisciplinaService {

    @Autowired
    private DisciplinaRepository disciplinaRepository;

    // Mapear Entity -> DTO
    private DisciplinaDTO mapToDTO(Disciplina disciplina) {
        return DisciplinaDTO.builder()
                .idDisciplina(disciplina.getIdDisciplina())
                .nombre(disciplina.getNombre())
                .descripcion(disciplina.getDescripcion())
                .build();
    }

    // Mapear DTO -> Entity
    private Disciplina mapToEntity(DisciplinaDTO dto) {
        return Disciplina.builder()
                .nombre(dto.getNombre())
                .descripcion(dto.getDescripcion())
                .build();
    }

    @Override
    public List<DisciplinaDTO> listarTodas() {
        return disciplinaRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public DisciplinaDTO obtenerPorId(Long id) {
        Disciplina disciplina = disciplinaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Disciplina no encontrada con ID: " + id));
        return mapToDTO(disciplina);
    }

    @Override
    public DisciplinaDTO crear(DisciplinaDTO dto) {
        // Validación: evitar duplicados por nombre
        if (disciplinaRepository.existsByNombre(dto.getNombre())) {
            throw new RuntimeException("Ya existe una disciplina con ese nombre.");
        }
        Disciplina disciplina = mapToEntity(dto);
        Disciplina nueva = disciplinaRepository.save(disciplina);
        return mapToDTO(nueva);
    }

    @Override
    public DisciplinaDTO actualizar(Long id, DisciplinaDTO dto) {
        Disciplina disciplina = disciplinaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Disciplina no encontrada con ID: " + id));

        disciplina.setNombre(dto.getNombre());
        disciplina.setDescripcion(dto.getDescripcion());

        Disciplina actualizada = disciplinaRepository.save(disciplina);
        return mapToDTO(actualizada);
    }

    @Override
    public void eliminar(Long id) {
        if (!disciplinaRepository.existsById(id)) {
            throw new RuntimeException("Disciplina no encontrada con ID: " + id);
        }
        disciplinaRepository.deleteById(id);
    }

    @Override
    public List<DisciplinaDTO> buscarPorNombre(String nombre) {
        return disciplinaRepository.findByNombreContainingIgnoreCase(nombre)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }
}
