package com.espaciosdeportivos.service;

import com.espaciosdeportivos.dto.ReservaDTO;
import java.time.LocalDate;
import java.util.List;

public interface IReservaService {

    List<ReservaDTO> listarTodas();
    ReservaDTO obtenerPorId(Long id);
    ReservaDTO crear(ReservaDTO reservaDTO);
    ReservaDTO actualizar(Long id, ReservaDTO reservaDTO);
    void eliminar(Long id);
    List<ReservaDTO> buscarPorCliente(Long idCliente);
    List<ReservaDTO> buscarPorEstado(String estado);
    List<ReservaDTO> buscarPorRangoFechas(LocalDate inicio, LocalDate fin);

    void validarFechaReserva(LocalDate fechaReserva);
}
