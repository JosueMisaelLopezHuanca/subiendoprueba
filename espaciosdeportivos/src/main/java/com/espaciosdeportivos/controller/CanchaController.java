package com.espaciosdeportivos.controller;

import com.espaciosdeportivos.dto.CanchaDTO;
import com.espaciosdeportivos.service.ICanchaService;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/canchas")
@Validated
public class CanchaController {

    private final ICanchaService canchaService;
    private static final Logger logger = LoggerFactory.getLogger(CanchaController.class);

    @Autowired
    public CanchaController(ICanchaService canchaService) {
        this.canchaService = canchaService;
    }

    // -------- CRUD básico --------

    @GetMapping
    public ResponseEntity<List<CanchaDTO>> obtenerTodasLasCanchas() {
        logger.info("[CANCHA] Inicio obtenerTodasLasCanchas");
        List<CanchaDTO> lista = canchaService.obtenerTodasLasCanchas();
        logger.info("[CANCHA] Fin obtenerTodasLasCanchas ({} registros)", lista.size());
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CanchaDTO> obtenerCanchaPorId(@PathVariable Long id) {
        logger.info("[CANCHA] Inicio obtenerCanchaPorId: {}", id);
        CanchaDTO dto = canchaService.obtenerCanchaPorId(id);
        logger.info("[CANCHA] Fin obtenerCanchaPorId");
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<CanchaDTO> crearCancha(@Valid @RequestBody CanchaDTO canchaDTO) {
        logger.info("[CANCHA] Inicio crearCancha");
        CanchaDTO creado = canchaService.crearCancha(canchaDTO);
        logger.info("[CANCHA] Fin crearCancha: id={}", creado.getIdCancha());
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<CanchaDTO> actualizarCancha(@PathVariable Long id, @Valid @RequestBody CanchaDTO canchaDTO) {
        logger.info("[CANCHA] Inicio actualizarCancha: {}", id);
        CanchaDTO actualizado = canchaService.actualizarCancha(id, canchaDTO);
        logger.info("[CANCHA] Fin actualizarCancha: {}", id);
        return ResponseEntity.ok(actualizado);
    }

    // Baja lógica (estadobool = false)
    @PutMapping("/{id}/eliminar")
    @Transactional
    public ResponseEntity<CanchaDTO> eliminarCancha(@PathVariable Long id) {
        logger.info("[CANCHA] Inicio eliminarCancha (baja lógica): {}", id);
        CanchaDTO eliminado = canchaService.eliminarCancha(id);
        logger.info("[CANCHA] Fin eliminarCancha (baja lógica): {}", id);
        return ResponseEntity.ok(eliminado);
    }

}
