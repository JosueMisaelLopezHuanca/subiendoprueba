package com.espaciosdeportivos.controller;


import com.espaciosdeportivos.dto.ZonaDTO;
import com.espaciosdeportivos.service.IZonaService;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RestController
@RequestMapping("/api/zonas")
@Validated
public class ZonaController {

    private final IZonaService zonaService;
    private static final Logger logger = LoggerFactory.getLogger(ZonaController.class);

    @Autowired
    public ZonaController(IZonaService zonaService) {
        this.zonaService = zonaService;
    }

    @GetMapping
    public ResponseEntity<List<ZonaDTO>> obtenerTodasLasZonas() {
        logger.info("[ZONA] Inicio obtenerTodasLasZonas");
        List<ZonaDTO> zonas = zonaService.obtenerTodasLasZonas();
        logger.info("[ZONA] Fin obtenerTodasLasZonas");
        return ResponseEntity.ok(zonas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ZonaDTO> obtenerZonaPorId(@PathVariable Long id) {
        logger.info("[ZONA] Inicio obtenerZonaPorId: {}", id);
        ZonaDTO zona = zonaService.obtenerZonaPorId(id);
        logger.info("[ZONA] Fin obtenerZonaPorId");
        return ResponseEntity.ok(zona);
    }

    @PostMapping
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ZonaDTO> crearZona(@Valid @RequestBody ZonaDTO zonaDTO) {
        ZonaDTO creada = zonaService.crearZona(zonaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(creada);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<ZonaDTO> actualizarZona(@PathVariable Long id, @Valid @RequestBody ZonaDTO zonaDTO) {
        ZonaDTO actualizada = zonaService.actualizarZona(id, zonaDTO);
        return ResponseEntity.ok(actualizada);
    }

    // Baja lógica (estado=false)
    @PutMapping("/{id}/eliminar")
    @Transactional
    public ResponseEntity<ZonaDTO> eliminarZona(@PathVariable Long id) {
        ZonaDTO eliminada = zonaService.eliminarZona(id);
        return ResponseEntity.ok(eliminada);
    }
}
