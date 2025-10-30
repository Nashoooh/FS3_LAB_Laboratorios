package com.ignacio.fs3_lab_laboratorios.model;

import jakarta.persistence.*;

@Entity
@Table(name = "solicitudes_analisis")
public class SolicitudAnalisis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String fechaSolicitud;
    private String estado;
    private Integer usuarioId;

    @ManyToOne
    @JoinColumn(name = "laboratorio_id")
    private Laboratorio laboratorio;

    @ManyToOne
    @JoinColumn(name = "analisis_id")
    private Analisis analisis;

    public SolicitudAnalisis() {}

    public SolicitudAnalisis(Integer id, String fechaSolicitud, String estado) {
        this.id = id;
        this.fechaSolicitud = fechaSolicitud;
        this.estado = estado;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getFechaSolicitud() { return fechaSolicitud; }
    public void setFechaSolicitud(String fechaSolicitud) { this.fechaSolicitud = fechaSolicitud; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public Integer getUsuarioId() { return usuarioId; }
    public void setUsuarioId(Integer usuarioId) { this.usuarioId = usuarioId; }

    public Laboratorio getLaboratorio() { return laboratorio; }
    public void setLaboratorio(Laboratorio laboratorio) { this.laboratorio = laboratorio; }

    public Analisis getAnalisis() { return analisis; }
    public void setAnalisis(Analisis analisis) { this.analisis = analisis; }
}