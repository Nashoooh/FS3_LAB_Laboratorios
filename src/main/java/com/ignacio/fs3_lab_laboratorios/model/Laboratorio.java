package com.ignacio.fs3_lab_laboratorios.model;

import jakarta.persistence.*;

@Entity
@Table(name = "laboratorios")
public class Laboratorio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;
    private String ubicacion;
    private int capacidad;
    private String direccion;
    private String telefono;

    public Laboratorio() {}

    public Laboratorio(String nombre, String ubicacion, int capacidad, String direccion, String telefono) {
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.capacidad = capacidad;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getUbicacion() { return ubicacion; }
    public void setUbicacion(String ubicacion) { this.ubicacion = ubicacion; }

    public int getCapacidad() { return capacidad; }
    public void setCapacidad(int capacidad) { this.capacidad = capacidad; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public void mostrarInfo() {
        System.out.println("Nombre del Laboratorio: " + nombre);
        System.out.println("Ubicación: " + ubicacion);
        System.out.println("Capacidad: " + capacidad);
        System.out.println("Dirección: " + direccion);
        System.out.println("Teléfono: " + telefono);
    }
}