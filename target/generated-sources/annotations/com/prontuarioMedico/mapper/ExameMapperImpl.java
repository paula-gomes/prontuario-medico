package com.prontuarioMedico.mapper;

import com.prontuarioMedico.dto.ExameDto;
import com.prontuarioMedico.entities.Exame;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-03-03T18:15:59-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 22 (Oracle Corporation)"
)
@Component
public class ExameMapperImpl implements ExameMapper {

    @Override
    public Exame toEntity(ExameDto dto) {
        if ( dto == null ) {
            return null;
        }

        Exame exame = new Exame();

        exame.tipo = dto.getTipo();
        exame.dataExame = dto.getDataExame();

        return exame;
    }

    @Override
    public ExameDto toDto(Exame exame) {
        if ( exame == null ) {
            return null;
        }

        ExameDto exameDto = new ExameDto();

        exameDto.setTipo( exame.tipo );
        exameDto.setDataExame( exame.dataExame );

        return exameDto;
    }

    @Override
    public List<ExameDto> toDtoList(List<Exame> exames) {
        if ( exames == null ) {
            return null;
        }

        List<ExameDto> list = new ArrayList<ExameDto>( exames.size() );
        for ( Exame exame : exames ) {
            list.add( toDto( exame ) );
        }

        return list;
    }

    @Override
    public void updateEntityFromDto(ExameDto dto, Exame entity) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getTipo() != null ) {
            entity.tipo = dto.getTipo();
        }
        if ( dto.getDataExame() != null ) {
            entity.dataExame = dto.getDataExame();
        }
    }
}
