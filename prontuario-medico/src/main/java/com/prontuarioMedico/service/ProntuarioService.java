package com.prontuarioMedico.services;

import com.prontuarioMedico.entities.Prontuario;
import com.prontuarioMedico.repositories.ProntuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProntuarioService {

    @Autowired
    private ProntuarioRepository prontuarioRepository;

    public List<Prontuario> findAll() {
        return prontuarioRepository.findAll();
    }

    public Optional<Prontuario> findById(Long id) {
        return prontuarioRepository.findById(id);
    }

    public Prontuario save(Prontuario prontuario) {
        return prontuarioRepository.save(prontuario);
    }

    public void deleteById(Long id) {
        prontuarioRepository.deleteById(id);
    }
}