package com.ignacio.fs3_lab_laboratorios.controller;

import com.ignacio.fs3_lab_laboratorios.model.SolicitudAnalisis;
import com.ignacio.fs3_lab_laboratorios.model.Laboratorio;
import com.ignacio.fs3_lab_laboratorios.model.Analisis;
import com.ignacio.fs3_lab_laboratorios.repository.SolicitudAnalisisRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.List;
import com.ignacio.fs3_lab_laboratorios.repository.LaboratorioRepository;
import com.ignacio.fs3_lab_laboratorios.repository.AnalisisRepository;

@RestController
@RequestMapping("/solicitud-analisis")
public class SolicitudAnalisisController {

    private final SolicitudAnalisisRepository solicitudAnalisisRepository;
    private final LaboratorioRepository laboratorioRepository;
    private final AnalisisRepository analisisRepository;

    public SolicitudAnalisisController(SolicitudAnalisisRepository solicitudAnalisisRepository,
                                       LaboratorioRepository laboratorioRepository,
                                       AnalisisRepository analisisRepository) {
        this.solicitudAnalisisRepository = solicitudAnalisisRepository;
        this.laboratorioRepository = laboratorioRepository;
        this.analisisRepository = analisisRepository;
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
    public ResponseEntity<?> createSolicitud(@RequestBody SolicitudAnalisis solicitudAnalisis) {
        try {
            if (solicitudAnalisis.getLaboratorio() != null && solicitudAnalisis.getLaboratorio().getId() != null) {
                Laboratorio lab = laboratorioRepository.findById(solicitudAnalisis.getLaboratorio().getId()).orElse(null);
                solicitudAnalisis.setLaboratorio(lab);
            }
            if (solicitudAnalisis.getAnalisis() != null && solicitudAnalisis.getAnalisis().getId() != null) {
                Analisis ana = analisisRepository.findById(solicitudAnalisis.getAnalisis().getId()).orElse(null);
                solicitudAnalisis.setAnalisis(ana);
            }
            SolicitudAnalisis saved = solicitudAnalisisRepository.save(solicitudAnalisis);
            return ResponseEntity.ok(saved);
        } catch (Exception e) {
            String msg = e.getMessage();
            String errorMsg = "Error desconocido";
            if (msg != null && msg.contains("FOREIGN KEY (`usuario_id`)")) {
                errorMsg = "El usuario ingresado no existe.";
            } else if (msg != null && msg.contains("FOREIGN KEY (`laboratorio_id`)")) {
                errorMsg = "El laboratorio ingresado no existe.";
            } else if (msg != null && msg.contains("FOREIGN KEY (`analisis_id`)")) {
                errorMsg = "El análisis ingresado no existe.";
            } else if (msg != null) {
                errorMsg = msg;
            }
            return ResponseEntity.badRequest().body(java.util.Collections.singletonMap("error", errorMsg));
        }
    }

    @PutMapping("/{id}")
    public SolicitudAnalisis updateSolicitud(@PathVariable Integer id, @RequestBody SolicitudAnalisis solicitudAnalisisDetails) {
        SolicitudAnalisis solicitudAnalisis = solicitudAnalisisRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Solicitud no encontrada"));
        solicitudAnalisis.setUsuarioId(solicitudAnalisisDetails.getUsuarioId());
        if (solicitudAnalisisDetails.getLaboratorio() != null && solicitudAnalisisDetails.getLaboratorio().getId() != null) {
            Laboratorio lab = laboratorioRepository.findById(solicitudAnalisisDetails.getLaboratorio().getId()).orElse(null);
            solicitudAnalisis.setLaboratorio(lab);
        } else {
            solicitudAnalisis.setLaboratorio(null);
        }
        if (solicitudAnalisisDetails.getAnalisis() != null && solicitudAnalisisDetails.getAnalisis().getId() != null) {
            Analisis ana = analisisRepository.findById(solicitudAnalisisDetails.getAnalisis().getId()).orElse(null);
            solicitudAnalisis.setAnalisis(ana);
        } else {
            solicitudAnalisis.setAnalisis(null);
        }
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