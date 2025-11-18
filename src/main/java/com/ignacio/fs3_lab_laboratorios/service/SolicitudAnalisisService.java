package com.ignacio.fs3_lab_laboratorios.service;

import com.ignacio.fs3_lab_laboratorios.model.SolicitudAnalisis;
import com.ignacio.fs3_lab_laboratorios.model.Laboratorio;
import com.ignacio.fs3_lab_laboratorios.model.Analisis;
import com.ignacio.fs3_lab_laboratorios.repository.SolicitudAnalisisRepository;
import com.ignacio.fs3_lab_laboratorios.repository.LaboratorioRepository;
import com.ignacio.fs3_lab_laboratorios.repository.AnalisisRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SolicitudAnalisisService {

    private final SolicitudAnalisisRepository solicitudAnalisisRepository;
    private final LaboratorioRepository laboratorioRepository;
    private final AnalisisRepository analisisRepository;

    public SolicitudAnalisisService(SolicitudAnalisisRepository solicitudAnalisisRepository,
                                    LaboratorioRepository laboratorioRepository,
                                    AnalisisRepository analisisRepository) {
        this.solicitudAnalisisRepository = solicitudAnalisisRepository;
        this.laboratorioRepository = laboratorioRepository;
        this.analisisRepository = analisisRepository;
    }

    public List<SolicitudAnalisis> getAllSolicitudes() {
        return solicitudAnalisisRepository.findAll();
    }

    public Optional<SolicitudAnalisis> getSolicitudById(Integer id) {
        return solicitudAnalisisRepository.findById(id);
    }

    public SolicitudAnalisis createSolicitud(SolicitudAnalisis solicitudAnalisis) {
        // Buscar y asignar objetos completos de laboratorio y análisis
        if (solicitudAnalisis.getLaboratorio() != null && solicitudAnalisis.getLaboratorio().getId() != null) {
            Laboratorio lab = laboratorioRepository.findById(solicitudAnalisis.getLaboratorio().getId()).orElse(null);
            solicitudAnalisis.setLaboratorio(lab);
        }
        if (solicitudAnalisis.getAnalisis() != null && solicitudAnalisis.getAnalisis().getId() != null) {
            Analisis ana = analisisRepository.findById(solicitudAnalisis.getAnalisis().getId()).orElse(null);
            solicitudAnalisis.setAnalisis(ana);
        }
        return solicitudAnalisisRepository.save(solicitudAnalisis);
    }

    public SolicitudAnalisis updateSolicitud(Integer id, SolicitudAnalisis solicitudAnalisisDetails) {
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

    public void deleteSolicitud(Integer id) {
        SolicitudAnalisis solicitudAnalisis = solicitudAnalisisRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Solicitud no encontrada"));
        solicitudAnalisisRepository.delete(solicitudAnalisis);
    }
}
