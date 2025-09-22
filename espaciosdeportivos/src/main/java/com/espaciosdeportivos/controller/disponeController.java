package com.espaciosdeportivos.controller;

import com.espaciosdeportivos.dto.disponeDTO;
import com.espaciosdeportivos.model.dispone;
import com.espaciosdeportivos.service.IdisponeService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/cancha-equipamiento")
@Validated
public class disponeController {

    private final IdisponeService disponeService;
    private static final Logger logger = LoggerFactory.getLogger(disponeController.class);

    @Autowired
    public disponeController(IdisponeService disponeService) {
        this.disponeService = disponeService;
    }

    // Asociar/Actualizar equipamiento a una cancha
    @PostMapping
    public ResponseEntity<disponeDTO> asociarEquipamientoACancha(
            @Valid @RequestBody disponeDTO dto) {
        logger.info("[DISPONE] Solicitud para asociar/actualizar equipamiento ID {} a cancha ID {} con cantidad {}.",
                dto.getIdEquipamiento(), dto.getIdCancha(), dto.getCantidad());
        disponeDTO result = disponeService.asociarEquipamientoACancha(dto);
        logger.info("[DISPONE] Asociación creada/actualizada exitosamente.");
        return ResponseEntity.ok(result);
    }

    // Obtener la asociación específica (cancha + equipamiento)
    @GetMapping("/{idCancha}/{idEquipamiento}")
    public ResponseEntity<disponeDTO> obtenerEquipamientoDeCancha(
            @PathVariable Long idCancha,
            @PathVariable Long idEquipamiento) {
        logger.info("[DISPONE] Solicitud para obtener asociación de cancha ID {} y equipamiento ID {}.",
                idCancha, idEquipamiento);
        disponeDTO association = disponeService.obtenerEquipamientoDeCancha(idCancha, idEquipamiento);
        logger.info("[DISPONE] Asociación encontrada exitosamente.");
        return ResponseEntity.ok(association);
    }

    // Desasociar equipamiento de una cancha
    @DeleteMapping("/{idCancha}/{idEquipamiento}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> desasociarEquipamientoDeCancha(
            @PathVariable Long idCancha,
            @PathVariable Long idEquipamiento) {
        logger.info("[DISPONE] Solicitud para desasociar equipamiento ID {} de cancha ID {}.",
                idEquipamiento, idCancha);
        disponeService.desasociarEquipamientoDeCancha(idCancha, idEquipamiento);
        logger.info("[DISPONE] Asociación eliminada exitosamente.");
        return ResponseEntity.noContent().build();
    }

    // Listar todos los equipamientos de una cancha (DTO simple)
    @GetMapping("/{idCancha}/equipamientos")
    public ResponseEntity<List<disponeDTO>> obtenerEquipamientosPorCancha(@PathVariable Long idCancha) {
        List<disponeDTO> lista = disponeService.obtenerEquipamientosPorCancha(idCancha);
        return ResponseEntity.ok(lista);
    }

    // Listar con detalle (similar a espacio-equipamiento)
    @GetMapping("/por-cancha/{idCancha}")
    public ResponseEntity<List<Map<String, Object>>> listarPorIdCancha(@PathVariable Long idCancha) {
        List<dispone> lista = disponeService.listarPorIdCancha(idCancha);

        List<Map<String, Object>> response = new ArrayList<>();
        for (dispone d : lista) {
            Map<String, Object> item = new LinkedHashMap<>();

            // Detalle Cancha
            item.put("idCancha", d.getCancha().getIdCancha());
            item.put("nombre", d.getCancha().getNombre());
            item.put("capacidad", d.getCancha().getCapacidad());
            item.put("estado", d.getCancha().getEstado());
            item.put("costoHora", d.getCancha().getCostoHora());

            // Detalle Equipamiento (OJO: usa los atributos correctos de tu clase Equipamiento)
            Map<String, Object> equipamientoMap = new LinkedHashMap<>();
            equipamientoMap.put("idEquipamiento", d.getEquipamiento().getIdEquipamiento());
            equipamientoMap.put("nombreEquipamiento", d.getEquipamiento().getNombreEquipamiento());
            equipamientoMap.put("tipoEquipamiento", d.getEquipamiento().getTipoEquipamiento());
            equipamientoMap.put("descripcion", d.getEquipamiento().getDescripcion());
            equipamientoMap.put("estado", d.getEquipamiento().getEstado());
            item.put("equipamiento", equipamientoMap);

            // Cantidad
            item.put("cantidad", d.getCantidad());

            response.add(item);
        }

        return ResponseEntity.ok(response);
    }
}
