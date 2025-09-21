package com.espaciosdeportivos.repository;

import com.espaciosdeportivos.model.sepractica;
import com.espaciosdeportivos.model.sepracticaId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface sepracticaRepository extends JpaRepository<sepractica, sepracticaId> {

    // Obtener todas las disciplinas que se practican en una cancha
    List<sepractica> findById_IdCancha(Long idCancha);

    // Obtener todas las canchas donde se practica una disciplina
    List<sepractica> findById_IdDisciplina(Long idDisciplina);

    // Verificar si ya existe una relación entre cancha y disciplina
    boolean existsById_IdCanchaAndId_IdDisciplina(Long idCancha, Long idDisciplina);
}
