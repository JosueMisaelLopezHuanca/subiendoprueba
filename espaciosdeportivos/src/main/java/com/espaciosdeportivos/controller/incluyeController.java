package com.espaciosdeportivos.controller;

import com.espaciosdeportivos.dto.incluyeDTO;
import com.espaciosdeportivos.model.incluyeId;
import com.espaciosdeportivos.service.IincluyeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/incluye")
@RequiredArgsConstructor
public class incluyeController {

    private final IincluyeService incluyeService;
    //J
    // TESTEADO
    // Crear relación cancha-reserva-disciplina
    @PostMapping
    public ResponseEntity<incluyeDTO> create(@Valid @RequestBody incluyeDTO dto) {
        return ResponseEntity.ok(incluyeService.create(dto));
    }
    //J
    // TESTEADO
    // Listar todas las relaciones
    @GetMapping
    public ResponseEntity<List<incluyeDTO>> findAll() {
        return ResponseEntity.ok(incluyeService.findAll());
    }
    //J
    // TESTEADO
    // Buscar todas las disciplinas incluidas en una reserva
    @GetMapping("/reserva/{idReserva}")
    public ResponseEntity<List<incluyeDTO>> findByReserva(@PathVariable Long idReserva) {
        return ResponseEntity.ok(incluyeService.findByReserva(idReserva));
    }
    //J
    // TESTEADO
    // Buscar todas las reservas que incluyen una cancha
    @GetMapping("/cancha/{idCancha}")
    public ResponseEntity<List<incluyeDTO>> findByCancha(@PathVariable Long idCancha) {
        return ResponseEntity.ok(incluyeService.findByCancha(idCancha));
    }
    //J
    // TESTEADO
    // Buscar todas las reservas que incluyen una disciplina
    @GetMapping("/disciplina/{idDisciplina}")
    public ResponseEntity<List<incluyeDTO>> findByDisciplina(@PathVariable Long idDisciplina) {
        return ResponseEntity.ok(incluyeService.findByDisciplina(idDisciplina));
    }
    //J
    // TESTEADO
    // Eliminar relación cancha-reserva-disciplina
    @DeleteMapping("/{idCancha}/{idReserva}/{idDisciplina}")
    public ResponseEntity<Void> delete(@PathVariable Long idCancha,
                                       @PathVariable Long idReserva,
                                       @PathVariable Long idDisciplina) {
        incluyeId id = new incluyeId(idCancha, idReserva, idDisciplina);
        incluyeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
