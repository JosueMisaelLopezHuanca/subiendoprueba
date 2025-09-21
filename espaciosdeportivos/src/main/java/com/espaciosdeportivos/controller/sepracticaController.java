package com.espaciosdeportivos.controller;

import com.espaciosdeportivos.dto.sepracticaDTO;
import com.espaciosdeportivos.model.sepracticaId;
import com.espaciosdeportivos.service.IsepracticaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sepractica")
@RequiredArgsConstructor
public class sepracticaController {

    private final IsepracticaService sepracticaService;
    //J
    // TESTEADO
    // Crear relación cancha-disciplina
    @PostMapping
    public ResponseEntity<sepracticaDTO> create(@Valid @RequestBody sepracticaDTO dto) {
        return ResponseEntity.ok(sepracticaService.create(dto));
    }
    //J
    // TESTEADO
    // Listar todas las relaciones
    @GetMapping
    public ResponseEntity<List<sepracticaDTO>> findAll() {
        return ResponseEntity.ok(sepracticaService.findAll());
    }
    //J
    // TESTEADO
    // Buscar todas las disciplinas de una cancha
    @GetMapping("/cancha/{idCancha}")
    public ResponseEntity<List<sepracticaDTO>> findByCancha(@PathVariable Long idCancha) {
        return ResponseEntity.ok(sepracticaService.findByCancha(idCancha));
    }
    //J
    // TESTEADO
    // Buscar todas las canchas de una disciplina
    @GetMapping("/disciplina/{idDisciplina}")
    public ResponseEntity<List<sepracticaDTO>> findByDisciplina(@PathVariable Long idDisciplina) {
        return ResponseEntity.ok(sepracticaService.findByDisciplina(idDisciplina));
    }
    //J
    // TESTEADO
    // Eliminar relación cancha-disciplina
    @DeleteMapping("/{idCancha}/{idDisciplina}")
    public ResponseEntity<Void> delete(@PathVariable Long idCancha, @PathVariable Long idDisciplina) {
        sepracticaId id = new sepracticaId(idCancha, idDisciplina);
        sepracticaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
