package com.espaciosdeportivos.service;

import java.util.List;
import jakarta.validation.Valid;
import com.espaciosdeportivos.dto.CanchaDTO;
import com.espaciosdeportivos.model.Cancha;

public class ICanchaService {
    List<CanchaDTO> obtenerTodasLasCanchas();
    CanchaDTO obtenerCanchaPorId(Long idCancha);
    CanchaDTO crearCancha(@Valid CanchaDTO canchaDTO);
    CanchaDTO actualizarCancha(Long idCancha, @Valid CanchaDTO canchaDTO);
    CanchaDTO eliminarCancha(Long idCancha);
}
