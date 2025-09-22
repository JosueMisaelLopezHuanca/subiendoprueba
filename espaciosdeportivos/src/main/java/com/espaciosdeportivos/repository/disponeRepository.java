package com.espaciosdeportivos.repository;

import com.espaciosdeportivos.model.dispone;
import com.espaciosdeportivos.model.disponeId;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface disponeRepository extends JpaRepository<dispone, disponeId> {

    // Lista por cancha con relaciones precargadas (evita LazyInitialization al mapear fuera)
    @EntityGraph(attributePaths = {"cancha", "equipamiento"})
    List<dispone> findById_IdCancha(Long idCancha);

    // Si sueles leer una asociación puntual y luego acceder a relaciones,
    // puedes tener una variante con EntityGraph también:
    @EntityGraph(attributePaths = {"cancha", "equipamiento"})
    Optional<dispone> findWithGraphById(disponeId id);

    List<dispone> findByEquipamiento_IdEquipamiento(Long idEquipamiento);

    Optional<dispone> findById_IdCanchaAndId_IdEquipamiento(Long idCancha, Long idEquipamiento);

    boolean existsById_IdCanchaAndId_IdEquipamiento(Long idCancha, Long idEquipamiento);
}
