package com.prontuarioMedico.service;

import com.prontuarioMedico.entities.Diagnostico;
import com.prontuarioMedico.repositories.DiagnosticoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DiagnosticoService {

    @Autowired
    private DiagnosticoRepository diagnosticoRepository;

    public List<Diagnostico> findAll() {
        return diagnosticoRepository.findAll();
    }

    public Optional<Diagnostico> findById(Long id) {
        return diagnosticoRepository.findById(id);
    }

    public Diagnostico save(Diagnostico diagnostico) {
        return diagnosticoRepository.save(diagnostico);
    }

    public void deleteById(Long id) {
        diagnosticoRepository.deleteById(id);
    }
}