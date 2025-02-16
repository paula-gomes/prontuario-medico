package com.prontuarioMedico.mapper;

import com.prontuarioMedico.dto.ProntuarioDto;
import com.prontuarioMedico.entities.Prontuario;

public class ProntuarioMapper {
    public static Prontuario toEntity(ProntuarioDto dto) {
        if (dto == null) return null;
        Prontuario prontuario = new Prontuario();
        prontuario.setId(dto.getId());
        prontuario.setDataCriacao(dto.getDataCriacao());
        return prontuario;
    }


    public static ProntuarioDto toDto(Prontuario prontuario) {
        if (prontuario == null) return null;
        ProntuarioDto dto = new ProntuarioDto();
        dto.setId(prontuario.getId());
        dto.setDataCriacao(prontuario.getDataCriacao());
        return dto;
    }


    public static void updateEntityFromDto(ProntuarioDto dto, Prontuario prontuario) {
        if (dto.getDataCriacao() != null) {
            prontuario.setDataCriacao(dto.getDataCriacao());
        }
    }
}
