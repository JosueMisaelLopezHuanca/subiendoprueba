package com.espaciosdeportivos.repository;

import com.espaciosdeportivos.model.Disciplina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DisciplinaRepository extends JpaRepository<Disciplina, Long> {

    // Ejemplo: verificar si ya existe una disciplina con el mismo nombre
    boolean existsByNombre(String nombre);

    // Ejemplo: buscar disciplinas por parte del nombre (ignorando mayúsculas/minúsculas)
    java.util.List<Disciplina> findByNombreContainingIgnoreCase(String nombre);
}
