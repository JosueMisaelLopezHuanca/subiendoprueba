package com.espaciosdeportivos.dto;

import lombok.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonaDTO {
    private Long idPersona;
    private String nombre;
    private String aPaterno;
    private String aMaterno;
    private LocalDate fechaNacimiento;
    private String telefono;
    private String email;
    private String ci;
}
