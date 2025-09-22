package com.espaciosdeportivos.repository;

import com.espaciosdeportivos.model.Zona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ZonaRepository extends JpaRepository<Zona, Long> {

    // Solo activos
    List<Zona> findByEstadoTrue();

    // Activo por id
    Optional<Zona> findByIdZonaAndEstadoTrue(Long idZona);

    // // Por macrodistrito (solo activos)
    // List<Zona> findByMacrodistrito_IdMacrodistritoAndEstadoTrue(Long idMacrodistrito);

    // // (Opcional) Unicidad por nombre dentro de un macrodistrito
    // boolean existsByNombreIgnoreCaseAndMacrodistrito_IdMacrodistrito(String nombre, Long idMacrodistrito);
}


