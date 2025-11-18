package com.ignacio.fs3_lab_laboratorios.controller;

import com.ignacio.fs3_lab_laboratorios.model.SolicitudAnalisis;
import com.ignacio.fs3_lab_laboratorios.service.SolicitudAnalisisService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.List;

@RestController
@RequestMapping("/solicitud-analisis")
public class SolicitudAnalisisController {

    private final SolicitudAnalisisService solicitudAnalisisService;

    public SolicitudAnalisisController(SolicitudAnalisisService solicitudAnalisisService) {
        this.solicitudAnalisisService = solicitudAnalisisService;
    }

    @GetMapping
    public List<SolicitudAnalisis> getAllSolicitudes() {
        return solicitudAnalisisService.getAllSolicitudes();
    }

    @GetMapping("/{id}")
    public SolicitudAnalisis getSolicitudById(@PathVariable Integer id) {
        return solicitudAnalisisService.getSolicitudById(id)
                .orElseThrow(() -> new RuntimeException("Solicitud no encontrada"));
    }

    @PostMapping
    public ResponseEntity<?> createSolicitud(@RequestBody SolicitudAnalisis solicitudAnalisis) {
        try {
            SolicitudAnalisis saved = solicitudAnalisisService.createSolicitud(solicitudAnalisis);
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
        return solicitudAnalisisService.updateSolicitud(id, solicitudAnalisisDetails);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSolicitud(@PathVariable Integer id) {
        solicitudAnalisisService.deleteSolicitud(id);
        return ResponseEntity.ok().build();
    }
}