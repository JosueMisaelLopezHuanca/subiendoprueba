package com.espaciosdeportivos.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "usuario_control")
@PrimaryKeyJoinColumn(name = "id_us_control")
public class UsuarioControl extends Persona {

    @Column(name = "estado_operativo", nullable = false, length = 50)
    private String estadoOperativo;

    @Column(name = "hora_inicio_turno", nullable = false)
    private LocalDateTime horaInicioTurno;

    @Column(name = "hora_fin_turno", nullable = false)
    private LocalDateTime horaFinTurno;

    @Column(name = "direccion", nullable = false, length = 200)
    private String direccion;

    public UsuarioControl() {}

    public String getEstadoOperativo() {
        return estadoOperativo;
    }

    public void setEstadoOperativo(String estadoOperativo) {
        this.estadoOperativo = estadoOperativo;
    }

    public LocalDateTime getHoraInicioTurno() {
        return horaInicioTurno;
    }

    public void setHoraInicioTurno(LocalDateTime horaInicioTurno) {
        this.horaInicioTurno = horaInicioTurno;
    }

    public LocalDateTime getHoraFinTurno() {
        return horaFinTurno;
    }

    public void setHoraFinTurno(LocalDateTime horaFinTurno) {
        this.horaFinTurno = horaFinTurno;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    //K
    @OneToMany(mappedBy = "usuarioControl", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Qr> qr;


}