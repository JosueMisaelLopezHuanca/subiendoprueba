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
    private Long id_equipamiento;

    @Column(name ="nombre_equipamiento" ,nullable = false)
    private String nombre;

    @Column(name ="tipo_equipamiento" ,nullable = false)
    private String tipo;

    @Column(name ="descripcion" , length = 400)
    private String descripcion;

    @Column(name ="estado" ,nullable = false)
    private String estado;

    @Column(name ="url_imagen" , length = 800)
    private String url_imagen;

    //@OneToMany(mappedBy = "equipamiento")
    //private List<dispone> cancha;

}