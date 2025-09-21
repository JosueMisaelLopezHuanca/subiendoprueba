package com.espaciosdeportivos.service;

import java.util.List;

import com.espaciosdeportivos.dto.disponeDTO;


public interface IdisponeService {
    disponeDTO asociarCanchaAEspacio(disponeDTO iddisponeDTO);
    disponeDTO asociarCanchaDeEspacio(Long idCancha, Long idEquipamiento);
    List<disponeDTO> obtenerequipamientosPorIdCancha(Long idCancha);
    void desasociardisponeDeCancha(Long idCancha, Long idEquipamiento);
    
}
