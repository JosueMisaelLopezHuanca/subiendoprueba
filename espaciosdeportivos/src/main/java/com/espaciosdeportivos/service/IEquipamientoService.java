package com.espaciosdeportivos.service;

import java.util.List;
import jakarta.validation.Valid;
import com.espaciosdeportivos.dto.EquipamientoDTO;
import com.espaciosdeportivos.model.Equipamiento;



public class IEquipamientoService {
    List<EquipamientoDTO> obtenerTodosLosEquipamientos();
    EquipamientoDTO obtenerEquipamientoPorId(Long idEquipamiento);
    EquipamientoDTO crearEquipamiento(@Valid EquipamientoDTO equipamientoDTO);
    EquipamientoDTO actualizarEquipamiento(Long idEquipamiento, @Valid EquipamientoDTO equipamientoDTO);
    EquipamientoDTO eliminarEquipamiento(Long idEquipamiento);

}
