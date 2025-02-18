package com.prontuarioMedico.mapper;

import com.prontuarioMedico.dto.ProntuarioDto;
import com.prontuarioMedico.entities.Prontuario;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-02-18T08:31:24-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 22 (Oracle Corporation)"
)
@Component
public class ProntuarioMapperImpl implements ProntuarioMapper {

    @Override
    public Prontuario toEntity(ProntuarioDto dto) {
        if ( dto == null ) {
            return null;
        }

        Prontuario prontuario = new Prontuario();

        prontuario.setId( dto.getId() );
        prontuario.setDataCriacao( dto.getDataCriacao() );

        return prontuario;
    }

    @Override
    public ProntuarioDto toDto(Prontuario prontuario) {
        if ( prontuario == null ) {
            return null;
        }

        ProntuarioDto prontuarioDto = new ProntuarioDto();

        prontuarioDto.setId( prontuario.getId() );
        prontuarioDto.setDataCriacao( prontuario.getDataCriacao() );

        return prontuarioDto;
    }

    @Override
    public List<ProntuarioDto> toDtoList(List<Prontuario> prontuarios) {
        if ( prontuarios == null ) {
            return null;
        }

        List<ProntuarioDto> list = new ArrayList<ProntuarioDto>( prontuarios.size() );
        for ( Prontuario prontuario : prontuarios ) {
            list.add( toDto( prontuario ) );
        }

        return list;
    }

    @Override
    public void updateEntityFromDto(ProntuarioDto dto, Prontuario entity) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getId() != null ) {
            entity.setId( dto.getId() );
        }
        if ( dto.getDataCriacao() != null ) {
            entity.setDataCriacao( dto.getDataCriacao() );
        }
    }
}
