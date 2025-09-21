package com.espaciosdeportivos.service;

import com.espaciosdeportivos.dto.sepracticaDTO;
import com.espaciosdeportivos.model.sepracticaId;

import java.util.List;

public interface IsepracticaService {

    sepracticaDTO create(sepracticaDTO dto);

    List<sepracticaDTO> findAll();

    List<sepracticaDTO> findByCancha(Long idCancha);

    List<sepracticaDTO> findByDisciplina(Long idDisciplina);

    void delete(sepracticaId id);
}
