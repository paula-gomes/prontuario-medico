package com.prontuarioMedico.service;

import com.prontuarioMedico.dto.PrescricaoDto;
import com.prontuarioMedico.entities.Prescricao;
import com.prontuarioMedico.mapper.PrescricaoMapper;
import com.prontuarioMedico.repositories.PrescricaoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class PrescricaoService {

    @Autowired
    private PrescricaoRepository prescricaoRepository;

    @Autowired
    private PrescricaoMapper prescricaoMapper;

    public List<PrescricaoDto> findAll() {
        List<Prescricao> prescricoes = prescricaoRepository.findAll();
        return prescricaoMapper.toDtoList(prescricoes);
    }

    public Optional<PrescricaoDto> findById(Long id) {
        return prescricaoRepository.findById(id)
                .map(prescricaoMapper::toDto);
    }

    @Transactional
    public PrescricaoDto save(PrescricaoDto dto) {
        Prescricao prescricao = prescricaoMapper.toEntity(dto);
        prescricao = prescricaoRepository.save(prescricao);
        return prescricaoMapper.toDto(prescricao);
    }

    @Transactional
    public Optional<PrescricaoDto> update(Long id, PrescricaoDto dto) {
        return prescricaoRepository.findById(id).map(existingPrescricao -> {
            prescricaoMapper.updateEntityFromDto(dto, existingPrescricao);
            Prescricao updated = prescricaoRepository.save(existingPrescricao);
            return prescricaoMapper.toDto(updated);
        });
    }

    public void deleteById(Long id) {
        prescricaoRepository.deleteById(id);
    }
}