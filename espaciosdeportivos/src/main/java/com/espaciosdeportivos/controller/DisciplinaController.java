package com.espaciosdeportivos.controller;

import com.espaciosdeportivos.dto.DisciplinaDTO;
import com.espaciosdeportivos.service.IDisciplinaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/disciplinas")
public class DisciplinaController {

    @Autowired
    private IDisciplinaService disciplinaService;
    //J - TESTEADO
    // -----------------------------
    // Listar todas las disciplinas
    // -----------------------------
    @GetMapping
    public ResponseEntity<List<DisciplinaDTO>> listarTodas() {
        List<DisciplinaDTO> lista = disciplinaService.listarTodas();
        return ResponseEntity.ok(lista);
    }
    //J - TESTEADO
    // -----------------------------
    // Obtener disciplina por ID
    // -----------------------------
    @GetMapping("/{id}")
    public ResponseEntity<DisciplinaDTO> obtenerPorId(@PathVariable Long id) {
        DisciplinaDTO dto = disciplinaService.obtenerPorId(id);
        return ResponseEntity.ok(dto);
    }
    //J - TESTEADO
    // -----------------------------
    // Crear nueva disciplina
    // -----------------------------
    @PostMapping
    public ResponseEntity<DisciplinaDTO> crear(@Valid @RequestBody DisciplinaDTO dto) {
        DisciplinaDTO nueva = disciplinaService.crear(dto);
        return ResponseEntity.ok(nueva);
    }
    //J - TESTEADO
    // -----------------------------
    // Actualizar disciplina
    // -----------------------------
    @PutMapping("/{id}")
    public ResponseEntity<DisciplinaDTO> actualizar(@PathVariable Long id,
                                                    @Valid @RequestBody DisciplinaDTO dto) {
        DisciplinaDTO actualizada = disciplinaService.actualizar(id, dto);
        return ResponseEntity.ok(actualizada);
    }
    //J - TESTEADO
    // -----------------------------
    // Eliminar disciplina
    // -----------------------------
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        disciplinaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
    //J - TESTEADO
    // -----------------------------
    // Buscar disciplinas por nombre
    // -----------------------------
    // http://localhost:8032/api/disciplinas/buscar?nombre=Volleyball
    @GetMapping("/buscar")
    public ResponseEntity<List<DisciplinaDTO>> buscarPorNombre(@RequestParam String nombre) {
        List<DisciplinaDTO> resultados = disciplinaService.buscarPorNombre(nombre);
        return ResponseEntity.ok(resultados);
    }
}
