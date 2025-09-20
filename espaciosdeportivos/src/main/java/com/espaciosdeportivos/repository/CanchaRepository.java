package com.espaciosdeportivos.repository;

import com.espaciosdeportivos.model.Cancha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CanchaRepository extends JpaRepository<Cancha, Long> {

    // Solo activas (soft delete)
    List<Cancha> findByEstadoboolTrue();

    // Activa por id
    Optional<Cancha> findByIdCanchaAndEstadoboolTrue(Long idCancha);

    // Por área deportiva (solo activas)
    List<Cancha> findByAreaDeportiva_IdAreaDeportivaAndEstadoboolTrue(Long idAreaDeportiva);

    // (Opcional) Unicidad de nombre dentro del área
    boolean existsByNombreIgnoreCaseAndAreaDeportiva_IdAreaDeportiva(String nombre, Long idAreaDeportiva);
}
