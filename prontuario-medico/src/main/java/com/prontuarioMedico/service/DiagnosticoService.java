package com.prontuarioMedico.service;

import com.prontuarioMedico.dto.DiagnosticoDto;
import com.prontuarioMedico.entities.Diagnostico;
import com.prontuarioMedico.mapper.DiagnosticoMapper;
import com.prontuarioMedico.repositories.DiagnosticoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DiagnosticoService {

    @Autowired
    private DiagnosticoRepository diagnosticoRepository;

    @Autowired
    private DiagnosticoMapper diagnosticoMapper;

    public List<DiagnosticoDto> findAll() {
        List<Diagnostico> diagnosticos = diagnosticoRepository.findAll();
        return diagnosticoMapper.toDtoList(diagnosticos);
    }

    public Optional<DiagnosticoDto> findById(Long id) {
        return diagnosticoRepository.findById(id)
                .map(diagnosticoMapper::toDto);
    }

    @Transactional
    public DiagnosticoDto save(DiagnosticoDto dto) {
        Diagnostico diagnostico = diagnosticoMapper.toEntity(dto);
        diagnostico = diagnosticoRepository.save(diagnostico);
        return diagnosticoMapper.toDto(diagnostico);
    }

    @Transactional
    public Optional<DiagnosticoDto> update(Long id, DiagnosticoDto dto){
        return diagnosticoRepository.findById(id).map(existingDiagnostico ->{
            diagnosticoMapper.updateEntityFromDto(dto, existingDiagnostico);
            Diagnostico updated = diagnosticoRepository.save(existingDiagnostico);
            return diagnosticoMapper.toDto(updated);
        });
    }


    public void deleteById(Long id) {
        diagnosticoRepository.deleteById(id);
    }
}