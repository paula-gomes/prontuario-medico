package com.prontuarioMedico.services;

import com.prontuarioMedico.entities.Exame;
import com.prontuarioMedico.repositories.ExameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExameService {

    @Autowired
    private ExameRepository exameRepository;

    public List<Exame> findAll() {
        return exameRepository.findAll();
    }

    public Optional<Exame> findById(Long id) {
        return exameRepository.findById(id);
    }

    public Exame save(Exame exame) {
        return exameRepository.save(exame);
    }

    public void deleteById(Long id) {
        exameRepository.deleteById(id);
    }
}