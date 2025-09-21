package com.espaciosdeportivos.service;

import jakarta.validation.Valid;
import com.espaciosdeportivos.dto.AreaDeportivaDTO;
import com.espaciosdeportivos.model.AreaDeportiva;

import java.util.List;

public interface IAreaDeportivaService {
    List<AreaDeportivaDTO> obtenerTodasLasAreasDeportivas();   // activos
    AreaDeportivaDTO obtenerAreaDeportivaPorId(Long idAreaDeportiva); // activo
    AreaDeportivaDTO crearAreaDeportiva(@Valid AreaDeportivaDTO areaDeportivaDTO);
    AreaDeportivaDTO actualizarAreaDeportiva(Long idAreaDeportiva, @Valid AreaDeportivaDTO areaDeportivaDTO);
    AreaDeportivaDTO eliminarAreaDeportiva(Long idAreaDeportiva); // baja lógica (estado=false)
    AreaDeportiva obtenerAreaDeportivaConBloqueo(Long idAreaDeportiva); // para uso interno con bloqueo
    void eliminarAreaDeportivaFisicamente(Long idAreaDeportiva); // uso interno
}
