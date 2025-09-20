package com.espaciosdeportivos.service.impl;

import com.espaciosdeportivos.dto.disponeDTO;
import com.espaciosdeportivos.model.Cancha;
import com.espaciosdeportivos.model.Equipamiento;
import com.espaciosdeportivos.model.dispone;
import com.espaciosdeportivos.model.disponeId;
import com.espaciosdeportivos.repository.CanchaRepository;
import com.espaciosdeportivos.repository.EquipamientoRepository;
import com.espaciosdeportivos.repository.disponeRepository;
import com.espaciosdeportivos.service.IdisponeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class disponeServiceimpl implements IdisponeService  {
private final disponeRepository disponeRepository;
    private final CanchaRepository canchaRepository;
    private final EquipamientoRepository equipamientoRepository;

    @Autowired
    public DisponeServiceImpl(
            disponeRepository disponeRepository,
            CanchaRepository canchaRepository,
            EquipamientoRepository equipamientoRepository
    ) {
        this.disponeRepository = disponeRepository;
        this.canchaRepository = canchaRepository;
        this.equipamientoRepository = equipamientoRepository;
    }

    @Override
    @Transactional
    public disponeDTO asociarEquipamientoACancha(disponeDTO dto) {
        Cancha cancha = canchaRepository.findById(dto.getIdCancha())
                .orElseThrow(() -> new RuntimeException("Cancha no encontrada con ID: " + dto.getIdCancha()));

        Equipamiento equipamiento = equipamientoRepository.findById(dto.getIdEquipamiento())
                .orElseThrow(() -> new RuntimeException("Equipamiento no encontrado con ID: " + dto.getIdEquipamiento()));

        disponeId id = new disponeId(dto.getIdCancha(), dto.getIdEquipamiento());
        Optional<dispone> existente = disponeRepository.findById(id);

        dispone disp;
        if (existente.isPresent()) {
            disp = existente.get();
            disp.setCantidad(dto.getCantidad());
        } else {
            disp = dispone.builder()
                    .id(id)
                    .cancha(cancha)
                    .equipamiento(equipamiento)
                    .cantidad(dto.getCantidad())
                    .build();
        }

        dispone guardado = disponeRepository.save(disp);
        return convertToDTO(guardado);
    }

    @Override
    @Transactional(readOnly = true)
    public disponeDTO obtenerEquipamientoDeCancha(Long idCancha, Long idEquipamiento) {
        disponeId id = new disponeId(idCancha, idEquipamiento);
        dispone association = disponeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Asociación no encontrada para Cancha ID: " + idCancha + " y Equipamiento ID: " + idEquipamiento));
        return convertToDTO(association);
    }

    @Override
    @Transactional(readOnly = true)
    public List<disponeDTO> obtenerEquipamientosPorIdCancha(Long idCancha) {
        canchaRepository.findById(idCancha)
                .orElseThrow(() -> new RuntimeException("Cancha no encontrada con ID: " + idCancha));
        return disponeRepository.findById_IdCancha(idCancha).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void desasociarEquipamientoDeCancha(Long idCancha, Long idEquipamiento) {
        disponeId id = new disponeId(idCancha, idEquipamiento);
        if (!disponeRepository.existsById(id)) {
            throw new RuntimeException("Asociación no encontrada para desasociar: Cancha ID: " + idCancha + " y Equipamiento ID: " + idEquipamiento);
        }
        disponeRepository.deleteById(id);
    }

    // Métodos auxiliares de conversión
    private disponeDTO convertToDTO(dispone disp) {
        return disponeDTO.builder()
                .idCancha(disp.getId().getIdCancha())
                .idEquipamiento(disp.getId().getIdEquipamiento())
                .cantidad(disp.getCantidad())
                .build();
    }
}
