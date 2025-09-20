package com.espaciosdeportivos.dto;

import java.io.Serializable;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CanchaDTO implements Serializable {
    private Long idCancha;

    @NotBlank(message = "El nombre de la cancha es obligatorio")
    private String nombre;

    @NotNull(message = "El costo por hora es obligatorio")
    @Positive(message = "El costo por hora debe ser un valor positivo")
    private Double costoHora;

    @NotNull(message = "La capacidad es obligatoria")
    @Positive(message = "La capacidad debe ser un valor positivo")
    private Integer capacidad;
    @NotBlank(message = "El estado es obligatorio")
    private String estado;
    @NotBlank(message = "El mantenimiento es obligatorio")
    private String mantenimiento;

    @NotBlank(message = "La hora de inicio es obligatoria")
    @JsonFormat(pattern = "HH:mm")
    private String horaInicio;

    @NotBlank(message = "La hora de fin es obligatoria")
    @JsonFormat(pattern = "HH:mm")
    private String horaFin;

    @NotBlank(message = "El tipo de superficie es obligatorio")
    private String tipoSuperficie;

    @NotBlank(message = "El tamaño es obligatorio")
    private String tamaño;

    @NotBlank(message = "La iluminación es obligatoria")
    private String iluminacion;

    @NotBlank(message = "El tipo de cubierta es obligatorio")
    private String cubierta;


    private String urlImagen;

    @NotNull(message = "El id del área deportiva es obligatorio")
    @Positive(message = "El id del área deportiva debe ser un valor positivo")  
    private Long idAreadeportiva;
    
}
