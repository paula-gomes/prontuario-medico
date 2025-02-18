package com.prontuarioMedico.mapper;

import com.prontuarioMedico.dto.PacienteDto;
import com.prontuarioMedico.entities.Paciente;
import org.mapstruct.*;

import java.util.List;


@Mapper(componentModel = "spring")
public interface PacienteMapper {

    @Mapping(source = "prontuario.id", target = "prontuario.id")
    Paciente toEntity(PacienteDto dto);

    @Mapping(source = "prontuario.id", target = "prontuario.id")
    @Mapping(source = "prontuario.dataCriacao", target = "prontuario.dataCriacao")
    PacienteDto toDto(Paciente paciente);

    List<PacienteDto> toDtoList(List<Paciente> pacientes);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(PacienteDto dto, @MappingTarget Paciente entity);

}
