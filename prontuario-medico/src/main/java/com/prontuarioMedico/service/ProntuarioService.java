package com.prontuarioMedico.service;

import com.prontuarioMedico.dto.ProntuarioDto;
import com.prontuarioMedico.entities.Prontuario;
import com.prontuarioMedico.repositories.ProntuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProntuarioService {

    @Autowired
    private ProntuarioRepository prontuarioRepository;

    public List<ProntuarioDto> findAll() {
        List<Prontuario> prontuarios = prontuarioRepository.findAll();
        return prontuarios.stream().map(this::toDto).collect(Collectors.toList());
    }

    public Optional<ProntuarioDto> findById(Long id) {
        return prontuarioRepository.findById(id).map(this::toDto);
    }

    @Transactional
    public ProntuarioDto save(ProntuarioDto dto) {
        Prontuario prontuario = toEntity(dto);
        prontuario = prontuarioRepository.save(prontuario);
        return toDto(prontuario);
    }

    @Transactional
    public Optional<ProntuarioDto> update(Long id, ProntuarioDto dto) {
        return prontuarioRepository.findById(id).map(existingProntuario -> {
            updateEntityFromDto(dto, existingProntuario);
            Prontuario updated = prontuarioRepository.save(existingProntuario);
            return toDto(updated);
        });
    }

    public void deleteById(Long id) {
        prontuarioRepository.deleteById(id);
    }


    private ProntuarioDto toDto(Prontuario prontuario) {
        return new ProntuarioDto(
                prontuario.getId(),
                prontuario.getDescricao()
        );
    }

    private Prontuario toEntity(ProntuarioDto dto) {
        Prontuario prontuario = new Prontuario();
        prontuario.setId(dto.getId());
        prontuario.setDescricao(dto.getDescricao());
        return prontuario;
    }

    private void updateEntityFromDto(ProntuarioDto dto, Prontuario prontuario) {
        prontuario.setDescricao(dto.getDescricao());
    }
}
