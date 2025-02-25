package com.prontuarioMedico.mapper;

import com.prontuarioMedico.dto.DiagnosticoDto;
import com.prontuarioMedico.entities.Diagnostico;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-02-23T18:27:00-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 22 (Oracle Corporation)"
)
@Component
public class DiagnosticoMapperImpl implements DiagnosticoMapper {

    @Override
    public Diagnostico toEntity(DiagnosticoDto dto) {
        if ( dto == null ) {
            return null;
        }

        Diagnostico diagnostico = new Diagnostico();

        diagnostico.setConsulta( dto.getConsulta() );
        diagnostico.setDescricao( dto.getDescricao() );
        diagnostico.setDataDiagnostico( dto.getDataDiagnostico() );

        return diagnostico;
    }

    @Override
    public DiagnosticoDto toDto(Diagnostico diagnostico) {
        if ( diagnostico == null ) {
            return null;
        }

        DiagnosticoDto diagnosticoDto = new DiagnosticoDto();

        diagnosticoDto.setConsulta( diagnostico.getConsulta() );
        diagnosticoDto.setDescricao( diagnostico.getDescricao() );
        diagnosticoDto.setDataDiagnostico( diagnostico.getDataDiagnostico() );

        return diagnosticoDto;
    }

    @Override
    public List<DiagnosticoDto> toDtoList(List<Diagnostico> diagnosticos) {
        if ( diagnosticos == null ) {
            return null;
        }

        List<DiagnosticoDto> list = new ArrayList<DiagnosticoDto>( diagnosticos.size() );
        for ( Diagnostico diagnostico : diagnosticos ) {
            list.add( toDto( diagnostico ) );
        }

        return list;
    }

    @Override
    public void updateEntityFromDto(DiagnosticoDto dto, Diagnostico entity) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getConsulta() != null ) {
            entity.setConsulta( dto.getConsulta() );
        }
        if ( dto.getDescricao() != null ) {
            entity.setDescricao( dto.getDescricao() );
        }
        if ( dto.getDataDiagnostico() != null ) {
            entity.setDataDiagnostico( dto.getDataDiagnostico() );
        }
    }
}
