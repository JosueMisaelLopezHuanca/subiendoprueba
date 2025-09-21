package com.espaciosdeportivos.controller;

import com.espaciosdeportivos.dto.participaDTO;
import com.espaciosdeportivos.service.IparticipaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/participa")
@RequiredArgsConstructor
public class participaController {

    private final IparticipaService participaService;
    //J
    // TESTEADO
    // Crear o asociar un invitado a una reserva
    @PostMapping
    public ResponseEntity<participaDTO> create(@Valid @RequestBody participaDTO dto) {
        return ResponseEntity.ok(participaService.save(dto));
    }

    //J
    // TESTEADO
    // Eliminar la relación invitado-reserva
    @DeleteMapping("/{idInvitado}/{idReserva}")
    public ResponseEntity<Void> delete(@PathVariable Long idInvitado, @PathVariable Long idReserva) {
        participaService.delete(idInvitado, idReserva);
        return ResponseEntity.noContent().build();
    }
    //J
    // TESTEADO
    // Eliminar la relación invitado-reserva
    // Obtener todas las participaciones
    @GetMapping
    public ResponseEntity<List<participaDTO>> getAll() {
        return ResponseEntity.ok(participaService.getAll());
    }
    //J
    // TESTEADO
    // Eliminar la relación invitado-reserva
    // Obtener todos los invitados de una reserva
    @GetMapping("/reserva/{idReserva}")
    public ResponseEntity<List<participaDTO>> getByReserva(@PathVariable Long idReserva) {
        return ResponseEntity.ok(participaService.getByReserva(idReserva));
    }
    //J
    // TESTEADO
    // Eliminar la relación invitado-reserva
    // Obtener todos los invitados de una reserva
    // Obtener todas las reservas en las que participa un invitado
    @GetMapping("/invitado/{idInvitado}")
    public ResponseEntity<List<participaDTO>> getByInvitado(@PathVariable Long idInvitado) {
        return ResponseEntity.ok(participaService.getByInvitado(idInvitado));
    }
}
