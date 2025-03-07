package com.prontuarioMedico.service;

import com.prontuarioMedico.dto.ExameDto;
import com.prontuarioMedico.entities.Exame;
import com.prontuarioMedico.repositories.ExameRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ExameService {

    @Autowired
    private ExameRepository exameRepository;

    public List<ExameDto> findAll() {
        List<Exame> exames = exameRepository.findAll();
        return exames.stream().map(this::toDto).collect(Collectors.toList());
    }

    public Optional<ExameDto> findById(Long id) {
        return exameRepository.findById(id).map(this::toDto);
    }

    @Transactional
    public ExameDto save(ExameDto dto) {
        Exame exame = toEntity(dto);
        exame = exameRepository.save(exame);
        return toDto(exame);
    }

    @Transactional
    public Optional<ExameDto> update(Long id, ExameDto dto) {
        return exameRepository.findById(id).map(existingExame -> {
            updateEntityFromDto(dto, existingExame);
            Exame updated = exameRepository.save(existingExame);
            return toDto(updated);
        });
    }

    public void deleteById(Long id) {
        exameRepository.deleteById(id);
    }

    // Métodos de conversão manual

    private ExameDto toDto(Exame exame) {
        return new ExameDto(
                exame.getId(),
                exame.getNome(),
                exame.getResultado()
        );
    }

    private Exame toEntity(ExameDto dto) {
        Exame exame = new Exame();
        exame.setId(dto.getId());
        exame.setNome(dto.getNome());
        exame.setResultado(dto.getResultado());
        return exame;
    }

    private void updateEntityFromDto(ExameDto dto, Exame exame) {
        exame.setNome(dto.getNome());
        exame.setResultado(dto.getResultado());
    }
}
