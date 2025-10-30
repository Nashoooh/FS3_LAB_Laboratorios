package com.ignacio.fs3_lab_laboratorios.controller;

import com.ignacio.fs3_lab_laboratorios.model.SolicitudAnalisis;
import com.ignacio.fs3_lab_laboratorios.repository.SolicitudAnalisisRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.List;

@RestController
@RequestMapping("/solicitud-analisis")
public class SolicitudAnalisisController {

    private final SolicitudAnalisisRepository solicitudAnalisisRepository;

    public SolicitudAnalisisController(SolicitudAnalisisRepository solicitudAnalisisRepository) {
        this.solicitudAnalisisRepository = solicitudAnalisisRepository;
    }

    @GetMapping
    public List<SolicitudAnalisis> getAllSolicitudes() {
        return solicitudAnalisisRepository.findAll();
    }

    @GetMapping("/{id}")
    public SolicitudAnalisis getSolicitudById(@PathVariable Integer id) {
        return solicitudAnalisisRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Solicitud no encontrada"));
    }

    @PostMapping
    public SolicitudAnalisis createSolicitud(@RequestBody SolicitudAnalisis solicitudAnalisis) {
        return solicitudAnalisisRepository.save(solicitudAnalisis);
    }

    @PutMapping("/{id}")
    public SolicitudAnalisis updateSolicitud(@PathVariable Integer id, @RequestBody SolicitudAnalisis solicitudAnalisisDetails) {
        SolicitudAnalisis solicitudAnalisis = solicitudAnalisisRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Solicitud no encontrada"));
        solicitudAnalisis.setUsuarioId(solicitudAnalisisDetails.getUsuarioId());
        solicitudAnalisis.setLaboratorio(solicitudAnalisisDetails.getLaboratorio());
        solicitudAnalisis.setAnalisis(solicitudAnalisisDetails.getAnalisis());
        solicitudAnalisis.setFechaSolicitud(solicitudAnalisisDetails.getFechaSolicitud());
        solicitudAnalisis.setEstado(solicitudAnalisisDetails.getEstado());
        return solicitudAnalisisRepository.save(solicitudAnalisis);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSolicitud(@PathVariable Integer id) {
        SolicitudAnalisis solicitudAnalisis = solicitudAnalisisRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Solicitud no encontrada"));
        solicitudAnalisisRepository.delete(solicitudAnalisis);
        return ResponseEntity.ok().build();
    }
}