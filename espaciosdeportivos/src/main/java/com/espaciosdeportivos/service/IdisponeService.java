package com.espaciosdeportivos.service;

import java.util.List;
import jakarta.validation.Valid;
import com.espaciosdeportivos.dto.disponeDTO;
import com.espaciosdeportivos.model.dispone;

public interface IdisponeService {
    disponeDTO asociarCanchaAEspacio(disponeDTO iddisponeDTO);
    disponeDTO asociarCanchaDeEspacio(Long idCancha, Long idEquipamiento);
    List<disponeDTO> obtenerequipamientosPorIdCancha(Long idCancha);
    void desasociardisponeDeCancha(Long idCancha, Long idEquipamiento);
    
}
