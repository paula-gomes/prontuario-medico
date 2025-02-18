package com.prontuarioMedico.mapper;


import com.prontuarioMedico.dto.ProntuarioDto;
import com.prontuarioMedico.entities.Prontuario;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProntuarioMapper {

    Prontuario toEntity(ProntuarioDto dto);

    ProntuarioDto toDto(Prontuario prontuario);

    List<ProntuarioDto> toDtoList(List<Prontuario> prontuarios);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(ProntuarioDto dto, @MappingTarget Prontuario entity);


}
