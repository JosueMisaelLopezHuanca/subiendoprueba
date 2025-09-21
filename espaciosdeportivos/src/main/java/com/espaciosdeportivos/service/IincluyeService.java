package com.espaciosdeportivos.service;

import com.espaciosdeportivos.dto.incluyeDTO;
import com.espaciosdeportivos.model.incluyeId;

import java.util.List;

public interface IincluyeService {

    incluyeDTO create(incluyeDTO dto);

    List<incluyeDTO> findAll();

    List<incluyeDTO> findByReserva(Long idReserva);

    List<incluyeDTO> findByCancha(Long idCancha);

    List<incluyeDTO> findByDisciplina(Long idDisciplina);

    void delete(incluyeId id);
}
