package com.ignacio.fs3_lab_laboratorios.controller;

import com.ignacio.fs3_lab_laboratorios.model.Analisis;
import com.ignacio.fs3_lab_laboratorios.repository.AnalisisRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/analisis")
public class AnalisisController {
    private final AnalisisRepository analisisRepository;

    public AnalisisController(AnalisisRepository analisisRepository) {
        this.analisisRepository = analisisRepository;
    }

    @GetMapping
    public List<Analisis> getAll() {
        return analisisRepository.findAll();
    }

    @GetMapping("/{id}")
    public Analisis getById(@PathVariable Integer id) {
        return analisisRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Analisis create(@RequestBody Analisis analisis) {
        return analisisRepository.save(analisis);
    }

    @PutMapping("/{id}")
    public Analisis update(@PathVariable Integer id, @RequestBody Analisis analisisDetails) {
        Analisis analisis = analisisRepository.findById(id).orElseThrow(() -> new RuntimeException("Análisis no encontrado"));
        analisis.setNombre(analisisDetails.getNombre());
        analisis.setDescripcion(analisisDetails.getDescripcion());
        return analisisRepository.save(analisis);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        analisisRepository.deleteById(id);
    }
}