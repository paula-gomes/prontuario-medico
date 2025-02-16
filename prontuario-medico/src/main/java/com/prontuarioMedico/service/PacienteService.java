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

    public List<PacienteDto> findAll() {
        return pacienteRepository.findAll().stream()
                .map(PacienteMapper::toDto)
                .collect(Collectors.toList());
    }

    public Optional<PacienteDto> findById(Long id) {
        return pacienteRepository.findById(id)
                .map(PacienteMapper::toDto);
    }

    @Transactional
    public Paciente save(Paciente paciente) {
        if (paciente.getProntuario() != null) {
            paciente.getProntuario().setPaciente(paciente);
        }
        return pacienteRepository.save(paciente);
    }

/*    public Optional<PacienteDto> updatePaciente(Long id, PacienteDto pacienteDto) {
        return pacienteRepository.findById(id).map(existingPaciente -> {
            PacienteMapper.updateEntityFromDto(pacienteDto, existingPaciente);
            Paciente updated = pacienteRepository.save(existingPaciente);
            return PacienteMapper.toDto(updated);
        });
    }*/

    public void deleteById(Long id) {
        pacienteRepository.deleteById(id);
    }
}