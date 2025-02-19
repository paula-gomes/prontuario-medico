package com.prontuarioMedico.service;

import com.prontuarioMedico.dto.ExameDto;
import com.prontuarioMedico.entities.Exame;
import com.prontuarioMedico.mapper.ExameMapper;
import com.prontuarioMedico.repositories.ExameRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExameService {

    @Autowired
    private ExameRepository exameRepository;
    @Autowired
    private ExameMapper exameMapper;

    public List<ExameDto> findAll() {
        List<Exame> exames = exameRepository.findAll();
        return exameMapper.toDtoList(exames);
    }

    public Optional<ExameDto> findById(Long id) {
        return exameRepository.findById(id)
                .map(exameMapper::toDto);
    }

    @Transactional
    public ExameDto save(ExameDto dto) {
        Exame exame = exameMapper.toEntity(dto);
        exame = exameRepository.save(exame);
        return exameMapper.toDto(exame);
    }

    @Transactional
    public Optional<ExameDto> update(Long id, ExameDto dto) {
        return exameRepository.findById(id).map(existingExame -> {
            exameMapper.updateEntityFromDto(dto, existingExame);
            Exame updated = exameRepository.save(existingExame);
            return exameMapper.toDto(updated);
        });
    }


    public void deleteById(Long id) {
        exameRepository.deleteById(id);
    }
}