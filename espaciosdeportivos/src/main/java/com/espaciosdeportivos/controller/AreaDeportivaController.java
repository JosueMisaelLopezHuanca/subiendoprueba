package com.espaciosdeportivos.controller;

import com.espaciosdeportivos.dto.AreaDeportivaDTO;
import com.espaciosdeportivos.service.IAreaDeportivaService;

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
@RequestMapping("/api/areas-deportivas")
@Validated
public class AreaDeportivaController {

    private final IAreaDeportivaService service;
    private static final Logger logger = LoggerFactory.getLogger(AreaDeportivaController.class);

    @Autowired
    public AreaDeportivaController(IAreaDeportivaService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<AreaDeportivaDTO>> obtenerTodas() {
        logger.info("[AREA] Inicio obtenerTodas");
        List<AreaDeportivaDTO> lista = service.obtenerTodasLasAreasDeportivas();
        logger.info("[AREA] Fin obtenerTodas");
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AreaDeportivaDTO> obtenerPorId(@PathVariable Long id) {
        logger.info("[AREA] Inicio obtenerPorId: {}", id);
        AreaDeportivaDTO dto = service.obtenerAreaDeportivaPorId(id);
        logger.info("[AREA] Fin obtenerPorId");
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<AreaDeportivaDTO> crear(@Valid @RequestBody AreaDeportivaDTO dto) {
        AreaDeportivaDTO creado = service.crearAreaDeportiva(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<AreaDeportivaDTO> actualizar(@PathVariable Long id, @Valid @RequestBody AreaDeportivaDTO dto) {
        AreaDeportivaDTO actualizado = service.actualizarAreaDeportiva(id, dto);
        return ResponseEntity.ok(actualizado);
    }

    // Baja lógica
    @PutMapping("/{id}/eliminar")
    @Transactional
    public ResponseEntity<AreaDeportivaDTO> eliminar(@PathVariable Long id) {
        AreaDeportivaDTO eliminado = service.eliminarAreaDeportiva(id);
        return ResponseEntity.ok(eliminado);
    }
}
