package com.espaciosdeportivos.service;

import com.espaciosdeportivos.dto.PagoDTO;

import java.time.LocalDate;
import java.util.List;

public interface IPagoService {

    PagoDTO crear(PagoDTO dto);

    PagoDTO actualizar(Long id, PagoDTO dto);

    PagoDTO obtenerPorId(Long id);

    void eliminar(Long id);

    List<PagoDTO> listarTodos();

    List<PagoDTO> buscarPorEstado(String estado);

    List<PagoDTO> buscarPorMetodo(String metodoPago);

    List<PagoDTO> buscarPorTipo(String tipoPago);

    List<PagoDTO> buscarPorFecha(LocalDate fecha);

    List<PagoDTO> buscarPorRangoFechas(LocalDate inicio, LocalDate fin);
}
