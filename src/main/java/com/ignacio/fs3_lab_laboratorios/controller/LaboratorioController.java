package com.ignacio.fs3_lab_laboratorios.controller;

import com.ignacio.fs3_lab_laboratorios.model.Laboratorio;
import com.ignacio.fs3_lab_laboratorios.service.LaboratorioService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/laboratorios")
public class LaboratorioController {
    private final LaboratorioService laboratorioService;

    public LaboratorioController(LaboratorioService laboratorioService) {
        this.laboratorioService = laboratorioService;
    }

    @GetMapping
    public List<Laboratorio> getAll() {
        return laboratorioService.getAllLaboratorios();
    }

    @GetMapping("/{id}")
    public Laboratorio getById(@PathVariable Integer id) {
        return laboratorioService.getLaboratorioById(id).orElse(null);
    }

    @PostMapping
    public Laboratorio create(@RequestBody Laboratorio lab) {
        return laboratorioService.createLaboratorio(lab);
    }

    @PutMapping("/{id}")
    public Laboratorio update(@PathVariable Integer id, @RequestBody Laboratorio labDetails) {
        return laboratorioService.updateLaboratorio(id, labDetails);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        laboratorioService.deleteLaboratorio(id);
    }
}