package com.prontuarioMedico.mapper;

import com.prontuarioMedico.dto.PrescricaoDto;
import com.prontuarioMedico.entities.Prescricao;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PrescricaoMapper {

    Prescricao toEntity(PrescricaoDto dto);

    PrescricaoDto toDto(Prescricao prescricao);

    List<PrescricaoDto> toDtoList(List<Prescricao> prescricoes);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(PrescricaoDto dto, @MappingTarget Prescricao entity);
}
