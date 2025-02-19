package com.prontuarioMedico.mapper;

import com.prontuarioMedico.dto.ExameDto;
import com.prontuarioMedico.entities.Exame;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ExameMapper {
    Exame toEntity(ExameDto dto);

    ExameDto toDto(Exame exame);

    List<ExameDto> toDtoList(List<Exame> exames);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(ExameDto dto, @MappingTarget Exame entity);
}
