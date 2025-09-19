package com.espaciosdeportivos.dto;

import lombok.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioControlDTO {
    private Long idUsControl;
    private String nombre;
    private String aPaterno;
    private String aMaterno;
    private LocalDate fechaNacimiento;
    private String telefono;
    private String email;
    private String ci;

    private String estadoOperativo;
    private LocalTime horaInicioTurno;
    private LocalTime horaFinTurno;
    private String direccion;
}
