package com.espaciosdeportivos.service.impl;

import com.espaciosdeportivos.dto.AreaDeportivaDTO;
import com.espaciosdeportivos.model.AreaDeportiva;
import com.espaciosdeportivos.model.Zona;
import com.espaciosdeportivos.model.Administrador;
import com.espaciosdeportivos.repository.AreaDeportivaRepository;
import com.espaciosdeportivos.repository.ZonaRepository;
import com.espaciosdeportivos.repository.AdministradorRepository;
import com.espaciosdeportivos.service.IAreaDeportivaService;
import com.espaciosdeportivos.validation.AreaDeportivaValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AreaDeportivaServiceImpl implements IAreaDeportivaService {
    private final AreaDeportivaRepository areaDeportivaRepository;
    private final ZonaRepository zonaRepository;
    private final AdministradorRepository administradorRepository;
    private final AreaDeportivaValidator areaDeportivaValidator;

    @Autowired
    public AreaDeportivaServiceImpl(
            AreaDeportivaRepository areaDeportivaRepository,
            ZonaRepository zonaRepository,
            AdministradorRepository administradorRepository,
            AreaDeportivaValidator areaDeportivaValidator
    ) {
        this.areaDeportivaRepository = areaDeportivaRepository;
        this.zonaRepository = zonaRepository;
        this.administradorRepository = administradorRepository;
        this.areaDeportivaValidator = areaDeportivaValidator;
    }

    @Override
    @Transactional(readOnly = true)
    public List<AreaDeportivaDTO> obtenerTodasLasAreas() {
        return areaDeportivaRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public AreaDeportivaDTO obtenerAreaPorId(Long id) {
        AreaDeportiva area = areaDeportivaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Área deportiva no encontrada con ID: " + id));
        return convertToDTO(area);
    }

    @Override
    @Transactional
    public AreaDeportivaDTO crearArea(@Valid AreaDeportivaDTO dto) {
        areaDeportivaValidator.validarArea(dto);

        Zona zona = zonaRepository.findById(dto.getIdZona())
                .orElseThrow(() -> new RuntimeException("Zona no encontrada con ID: " + dto.getIdZona()));
        Administrador admin = administradorRepository.findById(dto.getIdAdministrador())
                .orElseThrow(() -> new RuntimeException("Administrador no encontrado con ID: " + dto.getIdAdministrador()));

        AreaDeportiva area = convertToEntity(dto);
        area.setZona(zona);
        area.setAdministrador(admin);

        AreaDeportiva guardada = areaDeportivaRepository.save(area);
        return convertToDTO(guardada);
    }

    @Override
    @Transactional
    public AreaDeportivaDTO actualizarArea(Long id, @Valid AreaDeportivaDTO dto) {
        AreaDeportiva existente = areaDeportivaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Área deportiva no encontrada con ID: " + id));
        areaDeportivaValidator.validarArea(dto);

        Zona zona = zonaRepository.findById(dto.getIdZona())
                .orElseThrow(() -> new RuntimeException("Zona no encontrada con ID: " + dto.getIdZona()));
        Administrador admin = administradorRepository.findById(dto.getIdAdministrador())
                .orElseThrow(() -> new RuntimeException("Administrador no encontrado con ID: " + dto.getIdAdministrador()));

        existente.setNombreArea(dto.getNombreArea());
        existente.setDescripcionArea(dto.getDescripcion_area());
        existente.setEmailArea(dto.getEmailArea());
        existente.setTelefonoArea(dto.getTelefonoArea());
        existente.setHoraInicioArea(dto.getHoraInicioArea().toString());
        existente.setHoraFinArea(dto.getHoraFinArea().toString());
        existente.setEstadoArea(dto.getEstadoArea());
        existente.setUrlImagen(dto.getUrlImagen());
        existente.setLatitud(dto.getLatitud());
        existente.setLongitud(dto.getLongitud());
        existente.setZona(zona);
        existente.setAdministrador(admin);

        AreaDeportiva actualizada = areaDeportivaRepository.save(existente);
        return convertToDTO(actualizada);
    }

    @Override
    @Transactional
    public AreaDeportivaDTO eliminarArea(Long id) {
        AreaDeportiva existente = areaDeportivaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Área deportiva no encontrada con ID: " + id));
        areaDeportivaRepository.delete(existente);
        return convertToDTO(existente);
    }

    // Métodos privados de conversión
    private AreaDeportivaDTO convertToDTO(AreaDeportiva area) {
        return AreaDeportivaDTO.builder()
                .idAreadeportiva(area.getIdAreaDeportiva())
                .nombreArea(area.getNombreArea())
                .descripcion_area(area.getDescripcionArea())
                .emailArea(area.getEmailArea())
                .telefonoArea(area.getTelefonoArea())
                // Convierte String a LocalTime si es necesario
                .horaInicioArea(area.getHoraInicioArea() != null ? java.time.LocalTime.parse(area.getHoraInicioArea()) : null)
                .horaFinArea(area.getHoraFinArea() != null ? java.time.LocalTime.parse(area.getHoraFinArea()) : null)
                .estadoArea(area.getEstadoArea())
                .urlImagen(area.getUrlImagen())
                .latitud(area.getLatitud())
                .longitud(area.getLongitud())
                .idZona(area.getZona() != null ? area.getZona().getIdZona() : null)
                .idAdministrador(area.getAdministrador() != null ? area.getAdministrador().getIdPersona() : null)
                .build();
    }

    private AreaDeportiva convertToEntity(AreaDeportivaDTO dto) {
        return AreaDeportiva.builder()
                .idAreaDeportiva(dto.getIdAreadeportiva())
                .nombreArea(dto.getNombreArea())
                .descripcionArea(dto.getDescripcion_area())
                .emailArea(dto.getEmailArea())
                .telefonoArea(dto.getTelefonoArea())
                .horaInicioArea(dto.getHoraInicioArea() != null ? dto.getHoraInicioArea().toString() : null)
                .horaFinArea(dto.getHoraFinArea() != null ? dto.getHoraFinArea().toString() : null)
                .estadoArea(dto.getEstadoArea())
                .urlImagen(dto.getUrlImagen())
                .latitud(dto.getLatitud())
                .longitud(dto.getLongitud())
                .build();
    }
}