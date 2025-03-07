package com.prontuarioMedico.service;

import com.prontuarioMedico.dto.DiagnosticoDto;
import com.prontuarioMedico.entities.Diagnostico;
import com.prontuarioMedico.repositories.DiagnosticoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DiagnosticoService {

    @Autowired
    private DiagnosticoRepository diagnosticoRepository;

    public List<DiagnosticoDto> findAll() {
        List<Diagnostico> diagnosticos = diagnosticoRepository.findAll();
        return diagnosticos.stream().map(this::toDto).collect(Collectors.toList());
    }

    public Optional<DiagnosticoDto> findById(Long id) {
        return diagnosticoRepository.findById(id).map(this::toDto);
    }

    @Transactional
    public DiagnosticoDto save(DiagnosticoDto dto) {
        Diagnostico diagnostico = toEntity(dto);
        diagnostico = diagnosticoRepository.save(diagnostico);
        return toDto(diagnostico);
    }

    @Transactional
    public Optional<DiagnosticoDto> update(Long id, DiagnosticoDto dto) {
        return diagnosticoRepository.findById(id).map(existingDiagnostico -> {
            updateEntityFromDto(dto, existingDiagnostico);
            Diagnostico updated = diagnosticoRepository.save(existingDiagnostico);
            return toDto(updated);
        });
    }

    public void deleteById(Long id) {
        diagnosticoRepository.deleteById(id);
    }

    // 🔹 Métodos de conversão manual

    private DiagnosticoDto toDto(Diagnostico diagnostico) {
        return new DiagnosticoDto(
                diagnostico.getId(),
                diagnostico.getDescricao(),
                diagnostico.getConsulta() != null ? diagnostico.getConsulta().getId() : null // Apenas ID da consulta
        );
    }

    private Diagnostico toEntity(DiagnosticoDto dto) {
        Diagnostico diagnostico = new Diagnostico();
        diagnostico.setId(dto.getId());
        diagnostico.setDescricao(dto.getDescricao());
        // O relacionamento com a consulta pode ser tratado no service de Consulta, se necessário
        return diagnostico;
    }

    private void updateEntityFromDto(DiagnosticoDto dto, Diagnostico diagnostico) {
        diagnostico.setDescricao(dto.getDescricao());
        // Se necessário atualizar a consulta, precisaria buscar no banco antes
    }
}
