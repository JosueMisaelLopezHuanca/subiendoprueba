package com.espaciosdeportivos.repository;

import com.espaciosdeportivos.model.incluye;
import com.espaciosdeportivos.model.incluyeId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface incluyeRepository extends JpaRepository<incluye, incluyeId> {

    // Obtener todas las disciplinas incluidas en una reserva
    List<incluye> findById_IdReserva(Long idReserva);

    // Obtener todas las reservas que incluyen una cancha específica
    List<incluye> findById_IdCancha(Long idCancha);

    // Obtener todas las reservas que incluyen una disciplina específica
    List<incluye> findById_IdDisciplina(Long idDisciplina);

    // Verificar si ya existe la relación cancha-reserva-disciplina
    boolean existsById_IdCanchaAndId_IdReservaAndId_IdDisciplina(Long idCancha, Long idReserva, Long idDisciplina);
}
