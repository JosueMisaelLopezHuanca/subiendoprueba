package com.espaciosdeportivos.repository;

import com.espaciosdeportivos.model.participa;
import com.espaciosdeportivos.model.participaId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface participaRepository extends JpaRepository<participa, participaId> {

    List<participa> findById_IdReserva(Long idReserva);

    List<participa> findById_IdInvitado(Long idInvitado);

    boolean existsById_IdInvitadoAndId_IdReserva(Long idInvitado, Long idReserva);
}
