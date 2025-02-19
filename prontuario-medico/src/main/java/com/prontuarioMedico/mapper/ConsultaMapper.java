package com.prontuarioMedico.mapper;

import com.prontuarioMedico.dto.ConsultaDto;
import com.prontuarioMedico.entities.Consulta;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ConsultaMapper {
    Consulta toEntity(ConsultaDto dto);

    ConsultaDto toDto(Consulta consulta);

    List<ConsultaDto> toDtoList(List<Consulta> consultas);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(ConsultaDto dto, @MappingTarget Consulta entity);

}
