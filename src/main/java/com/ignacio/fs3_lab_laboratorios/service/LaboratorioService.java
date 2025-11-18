package com.ignacio.fs3_lab_laboratorios.service;

import com.ignacio.fs3_lab_laboratorios.model.Laboratorio;
import com.ignacio.fs3_lab_laboratorios.repository.LaboratorioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LaboratorioService {

    private final LaboratorioRepository laboratorioRepository;

    public LaboratorioService(LaboratorioRepository laboratorioRepository) {
        this.laboratorioRepository = laboratorioRepository;
    }

    public List<Laboratorio> getAllLaboratorios() {
        return laboratorioRepository.findAll();
    }

    public Optional<Laboratorio> getLaboratorioById(Integer id) {
        return laboratorioRepository.findById(id);
    }

    public Laboratorio createLaboratorio(Laboratorio laboratorio) {
        return laboratorioRepository.save(laboratorio);
    }

    public Laboratorio updateLaboratorio(Integer id, Laboratorio labDetails) {
        Laboratorio lab = laboratorioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Laboratorio no encontrado"));
        lab.setNombre(labDetails.getNombre());
        lab.setDireccion(labDetails.getDireccion());
        lab.setTelefono(labDetails.getTelefono());
        lab.setUbicacion(labDetails.getUbicacion());
        lab.setCapacidad(labDetails.getCapacidad());
        return laboratorioRepository.save(lab);
    }

    public void deleteLaboratorio(Integer id) {
        laboratorioRepository.deleteById(id);
    }
}
