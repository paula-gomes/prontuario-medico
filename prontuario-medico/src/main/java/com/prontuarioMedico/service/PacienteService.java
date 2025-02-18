package com.prontuarioMedico.service;

import com.prontuarioMedico.dto.PacienteDto;
import com.prontuarioMedico.entities.Paciente;
import com.prontuarioMedico.entities.Prontuario;
import com.prontuarioMedico.mapper.PacienteMapper;
import com.prontuarioMedico.repositories.PacienteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;
    @Autowired
    private  PacienteMapper pacienteMapper;

    public List<PacienteDto> findAll() {
        List<Paciente> pacientes = pacienteRepository.findAll();
        return pacienteMapper.toDtoList(pacientes);
    }

    public Optional<PacienteDto> findById(Long id) {
        return pacienteRepository.findById(id)
                .map(pacienteMapper::toDto);
    }

    public PacienteDto salvarPaciente(PacienteDto dto) {
        Paciente paciente = pacienteMapper.toEntity(dto);
        paciente = pacienteRepository.save(paciente);
        return pacienteMapper.toDto(paciente);
    }

    @Transactional
    public Optional<PacienteDto> updatePaciente(Long id, PacienteDto pacienteDto) {
        return pacienteRepository.findById(id).map(existingPaciente -> {
            pacienteMapper.updateEntityFromDto(pacienteDto, existingPaciente);
            Paciente updated = pacienteRepository.save(existingPaciente);
            return pacienteMapper.toDto(updated);
        });
    }

    public void deleteById(Long id) {
        pacienteRepository.deleteById(id);
    }
}