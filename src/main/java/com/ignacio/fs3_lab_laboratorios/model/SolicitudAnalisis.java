package com.ignacio.fs3_lab_laboratorios.model;

import jakarta.persistence.*;

@Entity
public class SolicitudAnalisis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombrePaciente;
    private String tipoAnalisis;
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

    public SolicitudAnalisis(Integer id, String nombrePaciente, String tipoAnalisis, String fechaSolicitud, String estado) {
        this.id = id;
        this.nombrePaciente = nombrePaciente;
        this.tipoAnalisis = tipoAnalisis;
        this.fechaSolicitud = fechaSolicitud;
        this.estado = estado;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getNombrePaciente() { return nombrePaciente; }
    public void setNombrePaciente(String nombrePaciente) { this.nombrePaciente = nombrePaciente; }

    public String getTipoAnalisis() { return tipoAnalisis; }
    public void setTipoAnalisis(String tipoAnalisis) { this.tipoAnalisis = tipoAnalisis; }

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

    public void mostrarInformacion() {
        System.out.println("ID: " + id);
        System.out.println("Nombre del Paciente: " + nombrePaciente);
        System.out.println("Tipo de Análisis: " + tipoAnalisis);
        System.out.println("Fecha de Solicitud: " + fechaSolicitud);
        System.out.println("Estado: " + estado);
    }
}