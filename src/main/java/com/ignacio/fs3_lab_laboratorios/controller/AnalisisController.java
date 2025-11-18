package com.ignacio.fs3_lab_laboratorios.controller;

import com.ignacio.fs3_lab_laboratorios.model.Analisis;
import com.ignacio.fs3_lab_laboratorios.service.AnalisisService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/analisis")
public class AnalisisController {
    private final AnalisisService analisisService;

    public AnalisisController(AnalisisService analisisService) {
        this.analisisService = analisisService;
    }

    @GetMapping
    public List<Analisis> getAll() {
        return analisisService.getAllAnalisis();
    }

    @GetMapping("/{id}")
    public Analisis getById(@PathVariable Integer id) {
        return analisisService.getAnalisisById(id).orElse(null);
    }

    @PostMapping
    public Analisis create(@RequestBody Analisis analisis) {
        return analisisService.createAnalisis(analisis);
    }

    @PutMapping("/{id}")
    public Analisis update(@PathVariable Integer id, @RequestBody Analisis analisisDetails) {
        return analisisService.updateAnalisis(id, analisisDetails);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        analisisService.deleteAnalisis(id);
    }
}