package com.espaciosdeportivos.service;

import java.util.List;

import com.espaciosdeportivos.dto.MacrodistritoDTO;

import jakarta.validation.Valid;

public class IMacrodistritoService {
    List<MacrodistritoDTO> obtenerTodosLosMacrodistritos();
    MacrodistritoDTO obtenerMacrodistritoPorId(Long idMacrodistrito);
    MacrodistritoDTO crearMacrodistrito(@Valid MacrodistritoDTO macrodistritoDTO);
    MacrodistritoDTO actualizarMacrodistrito(Long idMacrodistrito, @Valid MacrodistritoDTO macrodistritoDTO);
    MacrodistritoDTO eliminarMacrodistrito(Long idMacrodistrito);   
}
