package com.ignacio.fs3_lab_laboratorios.service;

import com.ignacio.fs3_lab_laboratorios.model.Analisis;
import com.ignacio.fs3_lab_laboratorios.repository.AnalisisRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnalisisService {

    private final AnalisisRepository analisisRepository;

    public AnalisisService(AnalisisRepository analisisRepository) {
        this.analisisRepository = analisisRepository;
    }

    public List<Analisis> getAllAnalisis() {
        return analisisRepository.findAll();
    }

    public Optional<Analisis> getAnalisisById(Integer id) {
        return analisisRepository.findById(id);
    }

    public Analisis createAnalisis(Analisis analisis) {
        return analisisRepository.save(analisis);
    }

    public Analisis updateAnalisis(Integer id, Analisis analisisDetails) {
        Analisis analisis = analisisRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Análisis no encontrado"));
        analisis.setNombre(analisisDetails.getNombre());
        analisis.setDescripcion(analisisDetails.getDescripcion());
        return analisisRepository.save(analisis);
    }

    public void deleteAnalisis(Integer id) {
        analisisRepository.deleteById(id);
    }
}
