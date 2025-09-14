package com.espaciosdeportivos.model;


import lombok.*;
import jakarta.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "cancha")
public class Cancha {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cancha")
    private Long id_cancha;

    @Column(name = "nombre_cancha", nullable = false, length = 100)
    private String nombre;

    @Column(name = "costo_hora", nullable = false)
    private Double costo_hora;

    @Column(name = "capacidad", nullable = false)
    private Integer capacidad;

    @Column(name = "estado", nullable = false, length = 100)
    private String estado;

    @Column(name = "mantenimiento", nullable = false, length = 100)
    private String mantenimiento;

    @Column(name = "hora_inicio")
    private String hora_inicio;

    @Column(name = "hora_fin")
    private String hora_fin;

    @Column(name = "tipo_superficie", nullable = false, length = 100)
    private String tipo_superficie;

    @Column(name = "tamaño", nullable = false, length = 100)
    private String tamaño;

    @Column(name = "iluminacion", nullable = false, length = 100)
    private String iluminacion;

    @Column(name = "cubierta", nullable = false, length = 100)
    private String cubierta;

    @Column(name = "url_imagen", length = 800)
    private String url_imagen;

    @ManyToOne
    @JoinColumn(name = "id_areadeportiva")
    private AreaDeportiva areaDeportiva;

    @OneToMany(mappedBy = "cancha")
    private List<dispone> equipamiento;

    //K
    /*@OneToMany(mappedBy = "cancha")
    private List<califica> comentario;
    //K
    @OneToMany(mappedBy = "cancha", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<califica> calificaciones;*/

}