package com.espaciosdeportivos.service.impl;

import com.espaciosdeportivos.dto.sepracticaDTO;
import com.espaciosdeportivos.model.*;
import com.espaciosdeportivos.repository.*;
import com.espaciosdeportivos.service.IsepracticaService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class sepracticaServiceImpl implements IsepracticaService {

    private final sepracticaRepository sepracticaRepository;
    private final CanchaRepository canchaRepository;
    private final DisciplinaRepository disciplinaRepository;

    @Override
    public sepracticaDTO create(sepracticaDTO dto) {
        Cancha cancha = canchaRepository.findById(dto.getIdCancha())
                .orElseThrow(() -> new EntityNotFoundException("Cancha no encontrada con id: " + dto.getIdCancha()));
        Disciplina disciplina = disciplinaRepository.findById(dto.getIdDisciplina())
                .orElseThrow(() -> new EntityNotFoundException("Disciplina no encontrada con id: " + dto.getIdDisciplina()));

        sepractica entity = sepractica.builder()
                .id(new sepracticaId(dto.getIdCancha(), dto.getIdDisciplina()))
                .cancha(cancha)
                .disciplina(disciplina)
                .build();

        sepractica saved = sepracticaRepository.save(entity);
        return mapToDTO(saved);
    }

    @Override
    public List<sepracticaDTO> findAll() {
        return sepracticaRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<sepracticaDTO> findByCancha(Long idCancha) {
        return sepracticaRepository.findById_IdCancha(idCancha)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<sepracticaDTO> findByDisciplina(Long idDisciplina) {
        return sepracticaRepository.findById_IdDisciplina(idDisciplina)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(sepracticaId id) {
        if (!sepracticaRepository.existsById(id)) {
            throw new EntityNotFoundException("Relación no encontrada: " + id);
        }
        sepracticaRepository.deleteById(id);
    }

    private sepracticaDTO mapToDTO(sepractica entity) {
        return sepracticaDTO.builder()
                .idCancha(entity.getCancha().getIdCancha())
                .idDisciplina(entity.getDisciplina().getIdDisciplina())
                .build();
    }
}
