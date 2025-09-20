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

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.validation.Valid;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class AreaDeportivaServiceimpl implements IAreaDeportivaService {

    private final AreaDeportivaRepository areaDeportivaRepository;
    private final ZonaRepository zonaRepository;
    private final AdministradorRepository administradorRepository;
    private final AreaDeportivaValidator areaDeportivaValidator;

    @Override
    @Transactional(readOnly = true)
    public List<AreaDeportivaDTO> obtenerTodasLasAreasDeportivas() {
        return areaDeportivaRepository.findByEstadoTrue()
                .stream()
                .map(this::toDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public AreaDeportivaDTO obtenerAreaDeportivaPorId(Long id) {
        AreaDeportiva area = areaDeportivaRepository.findByIdAreaDeportivaAndEstadoTrue(id)
                .orElseThrow(() -> new RuntimeException("Área deportiva no encontrada con ID: " + id));
        return toDto(area);
    }

    @Override
    public AreaDeportivaDTO crearAreaDeportiva(@Valid AreaDeportivaDTO dto) {
        areaDeportivaValidator.validarArea(dto);

        Zona zona = zonaRepository.findById(dto.getIdZona())
                .orElseThrow(() -> new RuntimeException("Zona no encontrada con ID: " + dto.getIdZona()));
        Administrador admin = administradorRepository.findById(dto.getIdAdministrador())
                .orElseThrow(() -> new RuntimeException("Administrador no encontrado con ID: " + dto.getIdAdministrador()));

        AreaDeportiva entidad = toEntity(dto);
        entidad.setIdAreaDeportiva(null);
        entidad.setEstado(Boolean.TRUE);
        entidad.setZona(zona);
        entidad.setAdministrador(admin);

        return toDto(areaDeportivaRepository.save(entidad));
    }

    @Override
    public AreaDeportivaDTO actualizarAreaDeportiva(Long id, @Valid AreaDeportivaDTO dto) {
        AreaDeportiva existente = areaDeportivaRepository.findByIdAreaDeportivaAndEstadoTrue(id)
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
        existente.setHoraInicioArea(dto.getHoraInicioArea() != null ? dto.getHoraInicioArea().toString() : null);
        existente.setHoraFinArea(dto.getHoraFinArea() != null ? dto.getHoraFinArea().toString() : null);
        existente.setEstadoArea(dto.getEstadoArea());
        existente.setUrlImagen(dto.getUrlImagen());
        existente.setLatitud(dto.getLatitud());
        existente.setLongitud(dto.getLongitud());
        existente.setEstado(dto.getEstado());
        existente.setZona(zona);
        existente.setAdministrador(admin);

        return toDto(areaDeportivaRepository.save(existente));
    }

    @Override
    public AreaDeportivaDTO eliminarAreaDeportiva(Long id) {
        AreaDeportiva existente = areaDeportivaRepository.findByIdAreaDeportivaAndEstadoTrue(id)
                .orElseThrow(() -> new RuntimeException("Área deportiva no encontrada con ID: " + id));
        existente.setEstado(Boolean.FALSE); // baja lógica
        return toDto(areaDeportivaRepository.save(existente));
    }

    // ---------- mapping ----------
    private AreaDeportivaDTO toDto(AreaDeportiva a) {
        return AreaDeportivaDTO.builder()
                .idAreadeportiva(a.getIdAreaDeportiva())
                .nombreArea(a.getNombreArea())
                .descripcion_area(a.getDescripcionArea())
                .emailArea(a.getEmailArea())
                .telefonoArea(a.getTelefonoArea())
                .horaInicioArea(parseTime(a.getHoraInicioArea()))
                .horaFinArea(parseTime(a.getHoraFinArea()))
                .estadoArea(a.getEstadoArea())
                .urlImagen(a.getUrlImagen())
                .latitud(a.getLatitud())
                .longitud(a.getLongitud())
                .idZona(a.getZona() != null ? a.getZona().getIdZona() : null)
                .idAdministrador(a.getAdministrador() != null ? a.getAdministrador().getIdPersona() : null)
                .estado(a.getEstado())
                .build();
    }

    private AreaDeportiva toEntity(AreaDeportivaDTO d) {
        return AreaDeportiva.builder()
                .idAreaDeportiva(d.getIdAreadeportiva())
                .nombreArea(d.getNombreArea())
                .descripcionArea(d.getDescripcion_area())
                .emailArea(d.getEmailArea())
                .telefonoArea(d.getTelefonoArea())
                .horaInicioArea(d.getHoraInicioArea() != null ? d.getHoraInicioArea().toString() : null)
                .horaFinArea(d.getHoraFinArea() != null ? d.getHoraFinArea().toString() : null)
                .estadoArea(d.getEstadoArea())
                .urlImagen(d.getUrlImagen())
                .latitud(d.getLatitud())
                .longitud(d.getLongitud())
                .estado(d.getEstado() != null ? d.getEstado() : Boolean.TRUE)
                .build();
    }

    private LocalTime parseTime(String t) {
        return (t != null && !t.isBlank()) ? LocalTime.parse(t) : null;
    }
}
