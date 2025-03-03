package com.prontuarioMedico.service;

import com.prontuarioMedico.dto.PrescricaoDto;
import com.prontuarioMedico.entities.Prescricao;
import com.prontuarioMedico.repositories.PrescricaoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PrescricaoService {

    @Autowired
    private PrescricaoRepository prescricaoRepository;

    public List<PrescricaoDto> findAll() {
        List<Prescricao> prescricoes = prescricaoRepository.findAll();
        return prescricoes.stream().map(this::toDto).collect(Collectors.toList());
    }

    public Optional<PrescricaoDto> findById(Long id) {
        return prescricaoRepository.findById(id).map(this::toDto);
    }

    @Transactional
    public PrescricaoDto save(PrescricaoDto dto) {
        Prescricao prescricao = toEntity(dto);
        prescricao = prescricaoRepository.save(prescricao);
        return toDto(prescricao);
    }

    @Transactional
    public Optional<PrescricaoDto> update(Long id, PrescricaoDto dto) {
        return prescricaoRepository.findById(id).map(existingPrescricao -> {
            updateEntityFromDto(dto, existingPrescricao);
            Prescricao updated = prescricaoRepository.save(existingPrescricao);
            return toDto(updated);
        });
    }

    public void deleteById(Long id) {
        prescricaoRepository.deleteById(id);
    }

    // Métodos de conversão manual

    private PrescricaoDto toDto(Prescricao prescricao) {
        return new PrescricaoDto(
                prescricao.getId(),
                prescricao.getMedicamento(),
                prescricao.getDosagem()
        );
    }

    private Prescricao toEntity(PrescricaoDto dto) {
        Prescricao prescricao = new Prescricao();
        prescricao.setId(dto.getId());
        prescricao.setMedicamento(dto.getMedicamento());
        prescricao.setDosagem(dto.getDosagem());
        return prescricao;
    }

    private void updateEntityFromDto(PrescricaoDto dto, Prescricao prescricao) {
        prescricao.setMedicamento(dto.getMedicamento());
        prescricao.setDosagem(dto.getDosagem());
    }
}
