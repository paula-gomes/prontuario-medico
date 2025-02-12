package com.prontuarioMedico.services;

import com.prontuarioMedico.entities.Prescricao;
import com.prontuarioMedico.repositories.PrescricaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PrescricaoService {

    @Autowired
    private PrescricaoRepository prescricaoRepository;

    public List<Prescricao> findAll() {
        return prescricaoRepository.findAll();
    }

    public Optional<Prescricao> findById(Long id) {
        return prescricaoRepository.findById(id);
    }

    public Prescricao save(Prescricao prescricao) {
        return prescricaoRepository.save(prescricao);
    }

    public void deleteById(Long id) {
        prescricaoRepository.deleteById(id);
    }
}