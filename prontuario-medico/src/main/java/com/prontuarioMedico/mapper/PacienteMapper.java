package com.prontuarioMedico.mapper;

import com.prontuarioMedico.dto.PacienteDto;
import com.prontuarioMedico.dto.ProntuarioDto;
import com.prontuarioMedico.entities.Paciente;
import com.prontuarioMedico.entities.Prontuario;

import java.time.LocalDateTime;

public class PacienteMapper {

    public static Paciente toEntity(PacienteDto dto) {
        if (dto == null) return null;

        Paciente paciente = new Paciente();
        paciente.setNome(dto.getNome());
        paciente.setCpf(dto.getCpf());
        paciente.setDataNascimento(dto.getDataNascimento());
        paciente.setEndereco(dto.getEndereco());
        paciente.setTelefone(dto.getTelefone());

        if (dto.getProntuario() != null && dto.getProntuario().getId() != null) {
            Prontuario prontuario = new Prontuario();
            prontuario.setId(dto.getProntuario().getId());
            prontuario.setPaciente(paciente);
            paciente.setProntuario(prontuario);
        }

        return paciente;
    }

    public static PacienteDto toDto(Paciente paciente) {
        if (paciente == null) return null;

        PacienteDto dto = new PacienteDto();
        dto.setId(paciente.getId());
        dto.setNome(paciente.getNome());
        dto.setCpf(paciente.getCpf());
        dto.setDataNascimento(paciente.getDataNascimento());
        dto.setEndereco(paciente.getEndereco());
        dto.setTelefone(paciente.getTelefone());

        if (paciente.getProntuario() != null) {
            ProntuarioDto prontuarioDto = new ProntuarioDto();
            prontuarioDto.setId(paciente.getProntuario().getId());
            prontuarioDto.setDataCriacao(paciente.getProntuario().getDataCriacao());
            dto.setProntuario(prontuarioDto);
        }

        return dto;
    }
}
