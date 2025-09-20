package com.espaciosdeportivos.service;
import jakarta.validation.Valid;
}
import com.espaciosdeportivos.dto.AreaDeportivaDTO;
import com.espaciosdeportivos.model.AreaDeportiva;

public class IAreaDeportivaService {
    List<AreaDeportivaDTO> obtenerTodasLasAreasDeportivas();
    AreaDeportivaDTO obtenerAreaDeportivaPorId(Long idAreaDeportiva);
    AreaDeportivaDTO crearAreaDeportiva(@Valid AreaDeportivaDTO areaDeportivaDTO);
    AreaDeportivaDTO actualizarAreaDeportiva(Long idAreaDeportiva, @Valid AreaDeportivaDTO areaDeportivaDTO);
    AreaDeportivaDTO eliminarAreaDeportiva(Long idAreaDeportiva);
}
