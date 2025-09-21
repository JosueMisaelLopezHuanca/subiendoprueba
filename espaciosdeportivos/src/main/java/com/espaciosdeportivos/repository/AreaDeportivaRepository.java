package com.espaciosdeportivos.repository;


import com.espaciosdeportivos.model.AreaDeportiva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AreaDeportivaRepository extends JpaRepository<AreaDeportiva, Long> {

    // Solo activos
    List<AreaDeportiva> findByEstadoTrue();

    // Activo por id
    Optional<AreaDeportiva> findByIdAreaDeportivaAndEstadoTrue(Long idAreaDeportiva);
    
    // Por zona (solo activos)
    List<AreaDeportiva> findByZona_IdZonaAndEstadoTrue(Long idZona);

    // (Opcional) Por administrador
    List<AreaDeportiva> findByAdministrador_IdPersonaAndEstadoTrue(Long idAdministrador);

    // (Opcional) Unicidad por nombre dentro de la misma zona
    boolean existsByNombreAreaIgnoreCaseAndZona_IdZona(String nombreArea, Long idZona);
}
