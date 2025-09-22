package com.espaciosdeportivos.service;

import java.util.List;
import jakarta.validation.Valid;
import com.espaciosdeportivos.dto.ZonaDTO;
import com.espaciosdeportivos.model.Zona;

public interface IZonaService {
    List<ZonaDTO> obtenerTodasLasZonas();
    ZonaDTO obtenerZonaPorId(Long idZona);
    ZonaDTO crearZona(@Valid ZonaDTO zonaDTO);
    ZonaDTO actualizarZona(Long idZona, @Valid ZonaDTO zonaDTO);
    ZonaDTO eliminarZona(Long idZona); 
    Zona obtenerZonaConBloqueo(Long idZona);
    void eliminarZonaFisicamente(Long idZona);
}
