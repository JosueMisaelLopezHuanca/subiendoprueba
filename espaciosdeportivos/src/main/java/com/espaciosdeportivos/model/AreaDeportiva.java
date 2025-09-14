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
@Table(name = "areadeportiva")
public class AreaDeportiva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_areadeportiva")
    private Long id_areadeportiva;

    @Column(name = "nombre_area", nullable = false, length = 100)
    private String nombre_area;

    @Column(name = "descripcion_area", length = 400)
    private String descripcion_area;

    @Column(name = "email_area", length = 100)
    private String email_area;

    @Column(name = "telefono_area", length = 8)
    private String telefono_area;

    @Column(name = "hora_inicio_area")
    private String hora_inicio_area;
    @Column(name = "hora_fin_area")
    private String hora_fin_area;

    @Column(name = "estado_area", nullable = false, length = 100)
    private String estado_area;

    @Column(name = "url_imagen", length = 800)
    private String url_imagen;

    @Column(name = "latitud")
    private Double latitud;

    @Column(name = "longitud")
    private Double longitud;

    @ManyToOne
    @JoinColumn(name = "id_zona")
    private Zona zona;

    @OneToMany(mappedBy = "areaDeportiva", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Cancha> cancha;

    //@ManyToOne
    //@JoinColumn(name = "id_administrador")
    //private Administrador administrador;
    
}