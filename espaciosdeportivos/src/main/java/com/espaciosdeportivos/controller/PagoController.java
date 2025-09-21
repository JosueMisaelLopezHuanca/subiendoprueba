package com.espaciosdeportivos.controller;

import com.espaciosdeportivos.dto.PagoDTO;
import com.espaciosdeportivos.service.IPagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/pagos")
public class PagoController {

    @Autowired
    private IPagoService pagoService;

    @GetMapping
    public List<PagoDTO> listarTodos() {
        return pagoService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PagoDTO> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(pagoService.obtenerPorId(id));
    }

    @PostMapping
    public ResponseEntity<PagoDTO> crear(@Valid @RequestBody PagoDTO dto) {
        return ResponseEntity.ok(pagoService.crear(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PagoDTO> actualizar(@PathVariable Long id, @Valid @RequestBody PagoDTO dto) {
        return ResponseEntity.ok(pagoService.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        pagoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/estado/{estado}")
    public List<PagoDTO> buscarPorEstado(@PathVariable String estado) {
        return pagoService.buscarPorEstado(estado);
    }

    @GetMapping("/metodo/{metodoPago}")
    public List<PagoDTO> buscarPorMetodo(@PathVariable String metodoPago) {
        return pagoService.buscarPorMetodo(metodoPago);
    }

    @GetMapping("/tipo/{tipoPago}")
    public List<PagoDTO> buscarPorTipo(@PathVariable String tipoPago) {
        return pagoService.buscarPorTipo(tipoPago);
    }

    @GetMapping("/fecha/{fecha}")
    public List<PagoDTO> buscarPorFecha(@PathVariable String fecha) {
        return pagoService.buscarPorFecha(LocalDate.parse(fecha));
    }

    @GetMapping("/rango-fechas")
    public List<PagoDTO> buscarPorRangoFechas(@RequestParam("inicio") String inicio,
                                              @RequestParam("fin") String fin) {
        return pagoService.buscarPorRangoFechas(LocalDate.parse(inicio), LocalDate.parse(fin));
    }
}
