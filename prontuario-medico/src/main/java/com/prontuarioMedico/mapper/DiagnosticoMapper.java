package com.prontuarioMedico.mapper;

import com.prontuarioMedico.dto.DiagnosticoDto;
import com.prontuarioMedico.entities.Diagnostico;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DiagnosticoMapper {
    Diagnostico toEntity(DiagnosticoDto dto);

    DiagnosticoDto toDto(Diagnostico diagnostico);

    List<DiagnosticoDto>toDtoList(List<Diagnostico> diagnosticos);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(DiagnosticoDto dto, @MappingTarget Diagnostico entity);
}
