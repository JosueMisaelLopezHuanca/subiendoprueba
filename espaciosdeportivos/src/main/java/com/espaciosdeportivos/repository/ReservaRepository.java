package com.espaciosdeportivos.repository;

import com.espaciosdeportivos.model.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {

    List<Reserva> findByCliente_IdPersona(Long idPersona);
    List<Reserva> findByEstadoReserva(String estadoReserva);
    List<Reserva> findByCliente_IdPersonaAndEstadoReserva(Long idPersona, String estadoReserva);
    List<Reserva> findByFechaReserva(LocalDate fechaReserva);
    List<Reserva> findByFechaReservaBetween(LocalDate inicio, LocalDate fin);
    List<Reserva> findByFechaReservaBetweenAndEstadoReserva(LocalDate inicio, LocalDate fin, String estadoReserva);
    List<Reserva> findByHoraInicio(LocalTime horaInicio);
    List<Reserva> findByFechaReservaAndHoraInicioBeforeAndHoraFinAfter(LocalDate fechaReserva, LocalTime horaFin, LocalTime horaInicio);
    boolean existsByCliente_IdPersonaAndFechaReserva(Long idPersona, LocalDate fechaReserva);
    long countByFechaReserva(LocalDate fechaReserva);
    List<Reserva> findByCliente_IdPersonaAndFechaReservaAfter(Long idPersona, LocalDate fechaActual);
}
