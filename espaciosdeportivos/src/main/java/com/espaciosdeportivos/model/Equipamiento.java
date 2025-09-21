package com.espaciosdeportivos.model;

import jakarta.persistence.*;
import lombok.*;
//import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "equipamiento")
public class Equipamiento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_equipamiento", nullable = false)
    private Long idequipamiento;

    @Column(name ="nombre_equipamiento" ,nullable = false)
    private String nombreequipamiento;

    @Column(name ="tipo_equipamiento" ,nullable = false)
    private String tipoequipamiento;

    @Column(name ="descripcion" , length = 400)
    private String descripcion;

    @Column(name ="estado_equipamiento" ,nullable = false)
    private String estado;

    @Column(name = "estado", nullable = false)
    private Boolean estadobool;

    @Column(name ="url_imagen" , length = 800)
    private String urlimagen;

    //@OneToMany(mappedBy = "equipamiento")
    //private List<dispone> cancha;

}