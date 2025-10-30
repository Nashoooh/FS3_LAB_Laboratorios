package com.ignacio.fs3_lab_laboratorios.controller;

import com.ignacio.fs3_lab_laboratorios.model.Laboratorio;
import com.ignacio.fs3_lab_laboratorios.repository.LaboratorioRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/laboratorios")
public class LaboratorioController {
    private final LaboratorioRepository laboratorioRepository;

    public LaboratorioController(LaboratorioRepository laboratorioRepository) {
        this.laboratorioRepository = laboratorioRepository;
    }

    @GetMapping
    public List<Laboratorio> getAll() {
        return laboratorioRepository.findAll();
    }

    @GetMapping("/{id}")
    public Laboratorio getById(@PathVariable Integer id) {
        return laboratorioRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Laboratorio create(@RequestBody Laboratorio lab) {
        return laboratorioRepository.save(lab);
    }

    @PutMapping("/{id}")
    public Laboratorio update(@PathVariable Integer id, @RequestBody Laboratorio labDetails) {
        Laboratorio lab = laboratorioRepository.findById(id).orElseThrow(() -> new RuntimeException("Laboratorio no encontrado"));
        lab.setNombre(labDetails.getNombre());
        lab.setDireccion(labDetails.getDireccion());
        lab.setTelefono(labDetails.getTelefono());
        return laboratorioRepository.save(lab);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        laboratorioRepository.deleteById(id);
    }
}