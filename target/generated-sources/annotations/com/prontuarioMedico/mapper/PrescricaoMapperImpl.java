package com.prontuarioMedico.mapper;

import com.prontuarioMedico.dto.PrescricaoDto;
import com.prontuarioMedico.entities.Prescricao;
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
public class PrescricaoMapperImpl implements PrescricaoMapper {

    @Override
    public Prescricao toEntity(PrescricaoDto dto) {
        if ( dto == null ) {
            return null;
        }

        Prescricao prescricao = new Prescricao();

        prescricao.medicamento = dto.getMedicamento();
        prescricao.dosagem = dto.getDosagem();
        prescricao.dataPrescricao = dto.getDataPrescricao();

        return prescricao;
    }

    @Override
    public PrescricaoDto toDto(Prescricao prescricao) {
        if ( prescricao == null ) {
            return null;
        }

        PrescricaoDto prescricaoDto = new PrescricaoDto();

        prescricaoDto.setMedicamento( prescricao.medicamento );
        prescricaoDto.setDosagem( prescricao.dosagem );
        prescricaoDto.setDataPrescricao( prescricao.dataPrescricao );

        return prescricaoDto;
    }

    @Override
    public List<PrescricaoDto> toDtoList(List<Prescricao> prescricoes) {
        if ( prescricoes == null ) {
            return null;
        }

        List<PrescricaoDto> list = new ArrayList<PrescricaoDto>( prescricoes.size() );
        for ( Prescricao prescricao : prescricoes ) {
            list.add( toDto( prescricao ) );
        }

        return list;
    }

    @Override
    public void updateEntityFromDto(PrescricaoDto dto, Prescricao entity) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getMedicamento() != null ) {
            entity.medicamento = dto.getMedicamento();
        }
        if ( dto.getDosagem() != null ) {
            entity.dosagem = dto.getDosagem();
        }
        if ( dto.getDataPrescricao() != null ) {
            entity.dataPrescricao = dto.getDataPrescricao();
        }
    }
}
