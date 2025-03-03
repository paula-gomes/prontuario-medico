package com.prontuarioMedico.service;

import com.prontuarioMedico.dto.PacienteDto;
import com.prontuarioMedico.entities.Paciente;
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
        List<Paciente> pacientes = pacienteRepository.findAll();
        return pacientes.stream().map(this::toDto).collect(Collectors.toList());
    }

    public Optional<PacienteDto> findById(Long id) {
        return pacienteRepository.findById(id).map(this::toDto);
    }

    public PacienteDto salvarPaciente(PacienteDto dto) {
        Paciente paciente = toEntity(dto);
        paciente = pacienteRepository.save(paciente);
        return toDto(paciente);
    }

    @Transactional
    public Optional<PacienteDto> updatePaciente(Long id, PacienteDto dto) {
        return pacienteRepository.findById(id).map(existingPaciente -> {
            updateEntityFromDto(dto, existingPaciente);
            Paciente updated = pacienteRepository.save(existingPaciente);
            return toDto(updated);
        });
    }

    public void deleteById(Long id) {
        pacienteRepository.deleteById(id);
    }


    public PacienteDto toDto(Paciente paciente) {
        return new PacienteDto(
                paciente.getId(),
                paciente.getNome(),
                paciente.getCpf(),
                paciente.getDataNascimento(),
                paciente.getEndereco(),
                paciente.getTelefone(),
                paciente.getProntuario() != null ? paciente.getProntuario().getId() : null
        );
    }


    public Paciente toEntity(PacienteDto dto) {
        Paciente paciente = new Paciente();
        paciente.setId(dto.getId());
        paciente.setNome(dto.getNome());
        paciente.setCpf(dto.getCpf());
        paciente.setDataNascimento(dto.getDataNascimento());
        paciente.setEndereco(dto.getEndereco());
        paciente.setTelefone(dto.getTelefone());

        return paciente;
    }


    private void updateEntityFromDto(PacienteDto dto, Paciente paciente) {
        paciente.setNome(dto.getNome());
        paciente.setCpf(dto.getCpf());
        paciente.setDataNascimento(dto.getDataNascimento());
    }
}
