package com.espaciosdeportivos.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

/**
 * Clase Embeddable que representa la clave primaria compuesta para la entidad EspacioEquipamiento.
 * Contiene los IDs del espacio y del equipamiento para definir una relación many-to-many.
 */
@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode 
public class disponeId {
    @Column(name = "id_cancha")
    private Long id_cancha;

    @Column(name = "id_equipamiento")
    private Long id_equipamiento;
}