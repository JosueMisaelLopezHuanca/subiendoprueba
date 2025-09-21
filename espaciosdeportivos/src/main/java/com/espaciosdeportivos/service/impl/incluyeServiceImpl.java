package com.espaciosdeportivos.service.impl;

import com.espaciosdeportivos.dto.incluyeDTO;
import com.espaciosdeportivos.model.*;
import com.espaciosdeportivos.repository.*;
import com.espaciosdeportivos.service.IincluyeService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class incluyeServiceImpl implements IincluyeService {

    private final incluyeRepository incluyeRepository;
    private final CanchaRepository canchaRepository;
    private final ReservaRepository reservaRepository;
    private final DisciplinaRepository disciplinaRepository;

    @Override
    public incluyeDTO create(incluyeDTO dto) {
        Cancha cancha = canchaRepository.findById(dto.getIdCancha())
                .orElseThrow(() -> new EntityNotFoundException("Cancha no encontrada con id: " + dto.getIdCancha()));
        Reserva reserva = reservaRepository.findById(dto.getIdReserva())
                .orElseThrow(() -> new EntityNotFoundException("Reserva no encontrada con id: " + dto.getIdReserva()));
        Disciplina disciplina = disciplinaRepository.findById(dto.getIdDisciplina())
                .orElseThrow(() -> new EntityNotFoundException("Disciplina no encontrada con id: " + dto.getIdDisciplina()));

        incluye entity = incluye.builder()
                .id(new incluyeId(dto.getIdCancha(), dto.getIdReserva(), dto.getIdDisciplina()))
                .cancha(cancha)
                .reserva(reserva)
                .disciplina(disciplina)
                .build();

        incluye saved = incluyeRepository.save(entity);
        return mapToDTO(saved);
    }

    @Override
    public List<incluyeDTO> findAll() {
        return incluyeRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<incluyeDTO> findByReserva(Long idReserva) {
        return incluyeRepository.findById_IdReserva(idReserva)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<incluyeDTO> findByCancha(Long idCancha) {
        return incluyeRepository.findById_IdCancha(idCancha)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<incluyeDTO> findByDisciplina(Long idDisciplina) {
        return incluyeRepository.findById_IdDisciplina(idDisciplina)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(incluyeId id) {
        if (!incluyeRepository.existsById(id)) {
            throw new EntityNotFoundException("Relación no encontrada: " + id);
        }
        incluyeRepository.deleteById(id);
    }

    private incluyeDTO mapToDTO(incluye entity) {
        return incluyeDTO.builder()
                .idCancha(entity.getCancha().getIdCancha())
                .idReserva(entity.getReserva().getIdReserva())
                .idDisciplina(entity.getDisciplina().getIdDisciplina())
                .build();
    }
}
