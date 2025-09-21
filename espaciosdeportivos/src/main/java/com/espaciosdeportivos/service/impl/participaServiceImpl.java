package com.espaciosdeportivos.service.impl;

import com.espaciosdeportivos.dto.participaDTO;
import com.espaciosdeportivos.model.Invitado;
import com.espaciosdeportivos.model.Reserva;
import com.espaciosdeportivos.model.participa;
import com.espaciosdeportivos.model.participaId;
import com.espaciosdeportivos.repository.InvitadoRepository;
import com.espaciosdeportivos.repository.ReservaRepository;
import com.espaciosdeportivos.repository.participaRepository;
import com.espaciosdeportivos.service.IparticipaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class participaServiceImpl implements IparticipaService {

    private final participaRepository participaRepo;
    private final InvitadoRepository invitadoRepo;
    private final ReservaRepository reservaRepo;

    @Override
    public participaDTO save(participaDTO dto) {
        Invitado invitado = invitadoRepo.findById(dto.getIdInvitado())
                .orElseThrow(() -> new RuntimeException("Invitado no encontrado"));
        Reserva reserva = reservaRepo.findById(dto.getIdReserva())
                .orElseThrow(() -> new RuntimeException("Reserva no encontrada"));

        participa entity = participa.builder()
                .id(new participaId(dto.getIdInvitado(), dto.getIdReserva()))
                .invitado(invitado)
                .reserva(reserva)
                .build();

        participa saved = participaRepo.save(entity);
        return mapToDTO(saved);
    }

    @Override
    public void delete(Long idInvitado, Long idReserva) {
        participaId id = new participaId(idInvitado, idReserva);
        participaRepo.deleteById(id);
    }

    @Override
    public List<participaDTO> getByReserva(Long idReserva) {
        return participaRepo.findById_IdReserva(idReserva)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<participaDTO> getByInvitado(Long idInvitado) {
        return participaRepo.findById_IdInvitado(idInvitado)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<participaDTO> getAll() {
        return participaRepo.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }
    //J
    //CAmbie en .getIdInvitado() por .getIdPersona()
    private participaDTO mapToDTO(participa entity) {
        return participaDTO.builder()
                .idInvitado(entity.getInvitado().getIdPersona())
                .idReserva(entity.getReserva().getIdReserva())
                .build();
    }
}
