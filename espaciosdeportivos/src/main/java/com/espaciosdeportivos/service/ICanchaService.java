package com.espaciosdeportivos.service;

import java.util.List;
import jakarta.validation.Valid;
import com.espaciosdeportivos.dto.CanchaDTO;

public interface ICanchaService {
    List<CanchaDTO> obtenerTodasLasCanchas();       // solo activas
    CanchaDTO obtenerCanchaPorId(Long idCancha);    // activa
    CanchaDTO crearCancha(@Valid CanchaDTO canchaDTO);
    CanchaDTO actualizarCancha(Long idCancha, @Valid CanchaDTO canchaDTO);
    CanchaDTO eliminarCancha(Long idCancha);        // baja lógica
}
