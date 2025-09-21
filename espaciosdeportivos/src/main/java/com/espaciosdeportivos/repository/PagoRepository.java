package com.espaciosdeportivos.repository;

import com.espaciosdeportivos.model.Pago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PagoRepository extends JpaRepository<Pago, Long> {

    // Buscar pagos por estado (pendiente, confirmado, etc.)
    List<Pago> findByEstado(String estado);

    // Buscar pagos por método (efectivo, tarjeta, etc.)
    List<Pago> findByMetodoPago(String metodoPago);

    // Buscar pagos por tipo (parcial, total, etc.)
    List<Pago> findByTipoPago(String tipoPago);

    // Buscar pagos realizados en una fecha específica
    List<Pago> findByFecha(LocalDate fecha);

    // Buscar pagos dentro de un rango de fechas
    List<Pago> findByFechaBetween(LocalDate inicio, LocalDate fin);

    // Validar si existe un pago con el mismo monto y reserva (ejemplo de verificación)
    boolean existsByMontoAndReserva_IdReserva(Double monto, Long idReserva);
}
