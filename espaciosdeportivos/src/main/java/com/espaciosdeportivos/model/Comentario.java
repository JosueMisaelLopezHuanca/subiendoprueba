package com.espaciosdeportivos.model;

import lombok.*;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "comentario")
public class Comentario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_comentario")
    private Long id_comentario;

    @Column(name = "comentario", nullable = false, length = 500)
    private String comentario;

    @Column(name = "calificacion")
    private Integer calificacion;

    @Column(name = "fecha", nullable = false)
    private LocalDateTime fecha;

    // Relación con Persona (autor del comentario)
    @ManyToOne
    @JoinColumn(name = "id_persona")
    private Persona persona;

    //K
    //realcion con cancha
    @ManyToOne
    @JoinColumn(name = "id_cancha")
    private Cancha cancha;
    
    // Relación con Califica (para M:N con Cancha)
    /*@OneToMany(mappedBy = "comentario")
    private List<califica> cancha; */

}
