package com.espaciosdeportivos.service.impl;

import com.espaciosdeportivos.dto.ComentarioDTO;
import com.espaciosdeportivos.model.Persona;
import com.espaciosdeportivos.model.Cancha;
import com.espaciosdeportivos.model.Comentario;
import com.espaciosdeportivos.repository.PersonaRepository;
import com.espaciosdeportivos.repository.CanchaRepository;
import com.espaciosdeportivos.repository.ComentarioRepository;
import com.espaciosdeportivos.service.IComentarioService;
import com.espaciosdeportivos.validation.ComentarioValidator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.validation.Valid;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ComentarioServiceImpl implements IComentarioService {

    private final ComentarioRepository comentarioRepository;
    private final PersonaRepository personaRepository;
    private final CanchaRepository canchaRepository;
    private final ComentarioValidator comentarioValidator;

    @Override
    @Transactional(readOnly = true)
    public List<ComentarioDTO> obtenerTodosLosComentarios() {
        return comentarioRepository.findAll().stream()
                .map(this::convertToDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public ComentarioDTO obtenerComentarioPorId(Long id) {
        Comentario comentario = comentarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comentario no encontrado con ID: " + id));
        return convertToDTO(comentario);
    }

    @Override
    public ComentarioDTO crearComentario(@Valid ComentarioDTO dto) {
        comentarioValidator.validarComentario(dto);

        Persona persona = personaRepository.findById(dto.getIdPersona())
                .orElseThrow(() -> new RuntimeException("Persona no encontrada con ID: " + dto.getIdPersona()));

        Cancha cancha = canchaRepository.findById(dto.getIdCancha())
                .orElseThrow(() -> new RuntimeException("Cancha no encontrada con ID: " + dto.getIdCancha()));

        Comentario entidad = toEntity(dto, persona, cancha);
        entidad.setIdComentario(null);
        entidad.setEstado(Boolean.TRUE);

        return convertToDTO(comentarioRepository.save(entidad));
    }

    @Override
    public ComentarioDTO actualizarComentario(Long id, @Valid ComentarioDTO dto) {
        Comentario existente = comentarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comentario no encontrado con ID: " + id));

        comentarioValidator.validarComentario(dto);

        Persona persona = personaRepository.findById(dto.getIdPersona())
                .orElseThrow(() -> new RuntimeException("Persona no encontrada con ID: " + dto.getIdPersona()));

        Cancha cancha = canchaRepository.findById(dto.getIdCancha())
                .orElseThrow(() -> new RuntimeException("Cancha no encontrada con ID: " + dto.getIdCancha()));

        existente.setContenido(dto.getContenido());
        existente.setCalificacion(dto.getCalificacion());
        existente.setFecha(dto.getFecha());
        existente.setEstado(dto.getEstado());
        existente.setPersona(persona);
        existente.setCancha(cancha);

        return convertToDTO(comentarioRepository.save(existente));
    }

    @Override
    public ComentarioDTO eliminarComentario(Long id) {
        Comentario existente = comentarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comentario no encontrado con ID: " + id));
        existente.setEstado(Boolean.FALSE);
        return convertToDTO(comentarioRepository.save(existente));
    }

    @Override
    public void eliminarComentarioFisicamente(Long id) {
        Comentario existente = comentarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comentario no encontrado con ID: " + id));
        comentarioRepository.delete(existente);
    }

    @Override
    public Comentario obtenerComentarioConBloqueo(Long id) {
        Comentario comentario = comentarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comentario no encontrado con ID: " + id));
        try {
            Thread.sleep(15000); // Simula espera
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return comentario;
    }

    // ---------- mapping ----------
    private ComentarioDTO convertToDTO(Comentario c) {
        return ComentarioDTO.builder()
                .idComentario(c.getIdComentario())
                .contenido(c.getContenido())
                .calificacion(c.getCalificacion())
                .fecha(c.getFecha())
                .estado(c.getEstado())
                .idPersona(c.getPersona() != null ? c.getPersona().getIdPersona() : null)
                .idCancha(c.getCancha() != null ? c.getCancha().getIdCancha() : null)
                .build();
    }

    private Comentario toEntity(ComentarioDTO d, Persona persona, Cancha cancha) {
        return Comentario.builder()
                .idComentario(d.getIdComentario())
                .contenido(d.getContenido())
                .calificacion(d.getCalificacion())
                .fecha(d.getFecha())
                .estado(d.getEstado() != null ? d.getEstado() : Boolean.TRUE)
                .persona(persona)
                .cancha(cancha)
                .build();
    }
}
