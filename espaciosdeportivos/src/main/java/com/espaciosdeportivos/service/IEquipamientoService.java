package com.espaciosdeportivos.service;

import java.util.List;
import jakarta.validation.Valid;
import com.espaciosdeportivos.dto.EquipamientoDTO;

public interface IEquipamientoService {
    List<EquipamientoDTO> obtenerTodosLosEquipamientos();                      // activos
    EquipamientoDTO obtenerEquipamientoPorId(Long idEquipamiento);            // activo
    EquipamientoDTO crearEquipamiento(@Valid EquipamientoDTO equipamientoDTO);
    EquipamientoDTO actualizarEquipamiento(Long idEquipamiento, @Valid EquipamientoDTO equipamientoDTO);
    EquipamientoDTO eliminarEquipamiento(Long idEquipamiento);                // baja lógica
}
