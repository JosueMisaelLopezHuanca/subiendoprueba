package com.espaciosdeportivos.service;

import com.espaciosdeportivos.dto.participaDTO;

import java.util.List;

public interface IparticipaService {

    participaDTO save(participaDTO dto);

    void delete(Long idInvitado, Long idReserva);

    List<participaDTO> getByReserva(Long idReserva);

    List<participaDTO> getByInvitado(Long idInvitado);

    List<participaDTO> getAll();
}
