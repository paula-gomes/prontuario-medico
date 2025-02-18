package com.prontuarioMedico.service;

import com.prontuarioMedico.dto.ProntuarioDto;
import com.prontuarioMedico.entities.Prontuario;
import com.prontuarioMedico.mapper.ProntuarioMapper;
import com.prontuarioMedico.repositories.ProntuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProntuarioService {

    @Autowired
    private ProntuarioRepository prontuarioRepository;
    @Autowired
    private ProntuarioMapper prontuarioMapper;

    public List<ProntuarioDto> findAll() {
        List<Prontuario> prontuarios = prontuarioRepository.findAll();
        return prontuarioMapper.toDtoList(prontuarios);
    }

    public Optional<ProntuarioDto> findById(Long id) {
        return prontuarioRepository.findById(id)
                .map(prontuarioMapper::toDto);
    }

    @Transactional
    public ProntuarioDto save(ProntuarioDto dto) {
        Prontuario prontuario = prontuarioMapper.toEntity(dto);
        prontuario = prontuarioRepository.save(prontuario);
        return prontuarioMapper.toDto(prontuario);
    }

    @Transactional
    public Optional<ProntuarioDto> update(Long id, ProntuarioDto dto) {
        return prontuarioRepository.findById(id).map(existingProntuario -> {
            prontuarioMapper.updateEntityFromDto(dto, existingProntuario);
            Prontuario updated = prontuarioRepository.save(existingProntuario);
            return prontuarioMapper.toDto(updated);
        });
    }

    public void deleteById(Long id) {
        prontuarioRepository.deleteById(id);
    }
}