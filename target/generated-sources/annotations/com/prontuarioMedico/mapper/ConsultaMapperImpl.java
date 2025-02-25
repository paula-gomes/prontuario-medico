package com.prontuarioMedico.mapper;

import com.prontuarioMedico.dto.ConsultaDto;
import com.prontuarioMedico.entities.Consulta;
import com.prontuarioMedico.entities.Diagnostico;
import com.prontuarioMedico.entities.Exame;
import com.prontuarioMedico.entities.Prescricao;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-02-23T18:12:55-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 22 (Oracle Corporation)"
)
@Component
public class ConsultaMapperImpl implements ConsultaMapper {

    @Override
    public Consulta toEntity(ConsultaDto dto) {
        if ( dto == null ) {
            return null;
        }

        Consulta consulta = new Consulta();

        consulta.paciente = dto.getPaciente();
        consulta.dataConsulta = dto.getDataConsulta();
        List<Diagnostico> list = dto.getDiagnosticos();
        if ( list != null ) {
            consulta.diagnosticos = new ArrayList<Diagnostico>( list );
        }
        List<Prescricao> list1 = dto.getPrescricoes();
        if ( list1 != null ) {
            consulta.prescricoes = new ArrayList<Prescricao>( list1 );
        }
        List<Exame> list2 = dto.getExames();
        if ( list2 != null ) {
            consulta.exames = new ArrayList<Exame>( list2 );
        }
        consulta.imageUrl = dto.getImageUrl();

        return consulta;
    }

    @Override
    public ConsultaDto toDto(Consulta consulta) {
        if ( consulta == null ) {
            return null;
        }

        ConsultaDto consultaDto = new ConsultaDto();

        consultaDto.setPaciente( consulta.paciente );
        consultaDto.setDataConsulta( consulta.dataConsulta );
        List<Diagnostico> list = consulta.diagnosticos;
        if ( list != null ) {
            consultaDto.setDiagnosticos( new ArrayList<Diagnostico>( list ) );
        }
        List<Prescricao> list1 = consulta.prescricoes;
        if ( list1 != null ) {
            consultaDto.setPrescricoes( new ArrayList<Prescricao>( list1 ) );
        }
        List<Exame> list2 = consulta.exames;
        if ( list2 != null ) {
            consultaDto.setExames( new ArrayList<Exame>( list2 ) );
        }
        consultaDto.setImageUrl( consulta.imageUrl );

        return consultaDto;
    }

    @Override
    public List<ConsultaDto> toDtoList(List<Consulta> consultas) {
        if ( consultas == null ) {
            return null;
        }

        List<ConsultaDto> list = new ArrayList<ConsultaDto>( consultas.size() );
        for ( Consulta consulta : consultas ) {
            list.add( toDto( consulta ) );
        }

        return list;
    }

    @Override
    public void updateEntityFromDto(ConsultaDto dto, Consulta entity) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getPaciente() != null ) {
            entity.paciente = dto.getPaciente();
        }
        if ( dto.getDataConsulta() != null ) {
            entity.dataConsulta = dto.getDataConsulta();
        }
        if ( entity.diagnosticos != null ) {
            List<Diagnostico> list = dto.getDiagnosticos();
            if ( list != null ) {
                entity.diagnosticos.clear();
                entity.diagnosticos.addAll( list );
            }
        }
        else {
            List<Diagnostico> list = dto.getDiagnosticos();
            if ( list != null ) {
                entity.diagnosticos = new ArrayList<Diagnostico>( list );
            }
        }
        if ( entity.prescricoes != null ) {
            List<Prescricao> list1 = dto.getPrescricoes();
            if ( list1 != null ) {
                entity.prescricoes.clear();
                entity.prescricoes.addAll( list1 );
            }
        }
        else {
            List<Prescricao> list1 = dto.getPrescricoes();
            if ( list1 != null ) {
                entity.prescricoes = new ArrayList<Prescricao>( list1 );
            }
        }
        if ( entity.exames != null ) {
            List<Exame> list2 = dto.getExames();
            if ( list2 != null ) {
                entity.exames.clear();
                entity.exames.addAll( list2 );
            }
        }
        else {
            List<Exame> list2 = dto.getExames();
            if ( list2 != null ) {
                entity.exames = new ArrayList<Exame>( list2 );
            }
        }
        if ( dto.getImageUrl() != null ) {
            entity.imageUrl = dto.getImageUrl();
        }
    }
}
