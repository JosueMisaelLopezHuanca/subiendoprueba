package com.espaciosdeportivos.controller;

import com.espaciosdeportivos.dto.ReservaDTO;
import com.espaciosdeportivos.service.IReservaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.time.LocalDate;

@RestController
@RequestMapping("/api/reservas")
public class ReservaController {

    @Autowired
    private IReservaService reservaService;
    //J - TESTEADO
    @GetMapping
    public List<ReservaDTO> listarTodas() {
        return reservaService.listarTodas();
    }
    //J - TESTEADO
    @GetMapping("/{id}")
    public ResponseEntity<ReservaDTO> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(reservaService.obtenerPorId(id));
    }
    //J - TESTEADO
    @PostMapping
    public ResponseEntity<ReservaDTO> crear(@Valid @RequestBody ReservaDTO dto) {
        return ResponseEntity.ok(reservaService.crear(dto));
    }
    //J - TESTEADO
    @PutMapping("/{id}")
    public ResponseEntity<ReservaDTO> actualizar(@PathVariable Long id, @Valid @RequestBody ReservaDTO dto) {
        return ResponseEntity.ok(reservaService.actualizar(id, dto));
    }
    //J - TESTEADO
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        reservaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
    //J - TESTEADO
    @GetMapping("/cliente/{idCliente}")
    public List<ReservaDTO> buscarPorCliente(@PathVariable Long idCliente) {
        return reservaService.buscarPorCliente(idCliente);
    }
    //J - TESTEADO
    @GetMapping("/estado/{estado}")
    public List<ReservaDTO> buscarPorEstado(@PathVariable String estado) {
        return reservaService.buscarPorEstado(estado);
    }
    //J - TESTEADO
    //http://localhost:8032/api/reservas/rango-fechas?inicio=2025-09-01&fin=2025-09-30
    @GetMapping("/rango-fechas")
    public List<ReservaDTO> buscarPorRangoFechas(@RequestParam("inicio") String inicio,
                                                 @RequestParam("fin") String fin) {
        return reservaService.buscarPorRangoFechas(LocalDate.parse(inicio), LocalDate.parse(fin));
    }
}
