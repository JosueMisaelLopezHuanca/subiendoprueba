package com.espaciosdeportivos.service.impl;

import com.espaciosdeportivos.dto.ComentarioDTO;
import com.espaciosdeportivos.model.Comentario;
import com.espaciosdeportivos.repository.ComentarioRepository;
import com.espaciosdeportivos.service.IComentarioService;
import com.espaciosdeportivos.validation.ComentarioValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ComentarioServiceImpl implements IComentarioService {

    private final ComentarioRepository comentarioRepository;
    private final ComentarioValidator comentarioValidator;

    // Repositorios comentados temporalmente
    // @Autowired
    // private PersonaRepository personaRepository;

    // @Autowired
    // private CanchaRepository canchaRepository;

    @Autowired
    public ComentarioServiceImpl(ComentarioRepository comentarioRepository, ComentarioValidator comentarioValidator) {
        this.comentarioRepository = comentarioRepository;
        this.comentarioValidator = comentarioValidator;
    }

    @Override
    public List<ComentarioDTO> obtenerTodosLosComentarios() {
        return comentarioRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public ComentarioDTO obtenerComentarioPorId(Long id) {
        Comentario comentario = comentarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comentario no encontrado con ID: " + id));
        return convertToDTO(comentario);
    }

    @Override
    @Transactional
    public ComentarioDTO crearComentario(ComentarioDTO comentarioDTO) {
        comentarioValidator.validarComentario(comentarioDTO);

        Comentario comentario = convertToEntity(comentarioDTO);
        Comentario comentarioGuardado = comentarioRepository.save(comentario);

        return convertToDTO(comentarioGuardado);
    }

    @Override
    @Transactional
    public ComentarioDTO actualizarComentario(Long id, ComentarioDTO comentarioDTO) {
        Comentario comentarioExistente = comentarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comentario no encontrado con ID: " + id));

        comentarioValidator.validarComentario(comentarioDTO);

        comentarioExistente.setContenido(comentarioDTO.getContenido());
        comentarioExistente.setCalificacion(comentarioDTO.getCalificacion());
        comentarioExistente.setFecha(comentarioDTO.getFecha());
        comentarioExistente.setEstado(comentarioDTO.getEstado());

        Comentario comentarioActualizado = comentarioRepository.save(comentarioExistente);
        return convertToDTO(comentarioActualizado);
    }

    @Override
    @Transactional
    public ComentarioDTO eliminarComentario(Long id) {
        Comentario comentarioExistente = comentarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comentario no encontrado con ID: " + id));

        comentarioExistente.setEstado(false); // baja lógica
        Comentario comentarioEliminado = comentarioRepository.save(comentarioExistente);

        return convertToDTO(comentarioEliminado);
    }

    @Override
    @Transactional
    public Comentario obtenerComentarioConBloqueo(Long id) {
        Comentario comentario = comentarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comentario no encontrado con ID: " + id));
        try {
            Thread.sleep(15000); // simula espera
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return comentario;
    }

    @Override
    @Transactional
    public void eliminarComentarioFisicamente(Long id) {
        Comentario comentarioExistente = comentarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comentario no encontrado con ID: " + id));
        comentarioRepository.delete(comentarioExistente);
    }

    private ComentarioDTO convertToDTO(Comentario comentario) {
        return ComentarioDTO.builder()
                .idComentario(comentario.getIdComentario())
                .contenido(comentario.getContenido())
                .calificacion(comentario.getCalificacion())
                .fecha(comentario.getFecha())
                .idCancha(comentario.getCancha() != null ? comentario.getCancha().getIdCancha() : null)
                .build();
    }

    private Comentario convertToEntity(ComentarioDTO dto) {
        // Persona persona = personaRepository.findById(dto.getIdPersona())
        //     .orElseThrow(() -> new RuntimeException("Persona no encontrada con ID: " + dto.getIdPersona()));

        // Cancha cancha = canchaRepository.findById(dto.getIdCancha())
        //     .orElseThrow(() -> new RuntimeException("Cancha no encontrada con ID: " + dto.getIdCancha()));

        return Comentario.builder()
                .idComentario(dto.getIdComentario())
                .contenido(dto.getContenido())
                .calificacion(dto.getCalificacion())
                .fecha(dto.getFecha())
                .estado(dto.getEstado())
                // .persona(persona)
                // .cancha(cancha)
                .build();
    }
}
