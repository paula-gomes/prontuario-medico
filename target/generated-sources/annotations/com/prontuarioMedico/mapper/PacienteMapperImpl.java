package com.prontuarioMedico.mapper;

import com.prontuarioMedico.dto.PacienteDto;
import com.prontuarioMedico.dto.ProntuarioDto;
import com.prontuarioMedico.entities.Paciente;
import com.prontuarioMedico.entities.Prontuario;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-02-18T08:31:22-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 22 (Oracle Corporation)"
)
@Component
public class PacienteMapperImpl implements PacienteMapper {

    @Override
    public Paciente toEntity(PacienteDto dto) {
        if ( dto == null ) {
            return null;
        }

        Paciente paciente = new Paciente();

        paciente.setProntuario( prontuarioDtoToProntuario( dto.getProntuario() ) );
        paciente.setId( dto.getId() );
        paciente.setNome( dto.getNome() );
        paciente.setCpf( dto.getCpf() );
        paciente.setDataNascimento( dto.getDataNascimento() );
        paciente.setEndereco( dto.getEndereco() );
        paciente.setTelefone( dto.getTelefone() );

        return paciente;
    }

    @Override
    public PacienteDto toDto(Paciente paciente) {
        if ( paciente == null ) {
            return null;
        }

        PacienteDto pacienteDto = new PacienteDto();

        pacienteDto.setProntuario( prontuarioToProntuarioDto( paciente.getProntuario() ) );
        pacienteDto.setId( paciente.getId() );
        pacienteDto.setNome( paciente.getNome() );
        pacienteDto.setCpf( paciente.getCpf() );
        pacienteDto.setDataNascimento( paciente.getDataNascimento() );
        pacienteDto.setEndereco( paciente.getEndereco() );
        pacienteDto.setTelefone( paciente.getTelefone() );

        return pacienteDto;
    }

    @Override
    public List<PacienteDto> toDtoList(List<Paciente> pacientes) {
        if ( pacientes == null ) {
            return null;
        }

        List<PacienteDto> list = new ArrayList<PacienteDto>( pacientes.size() );
        for ( Paciente paciente : pacientes ) {
            list.add( toDto( paciente ) );
        }

        return list;
    }

    @Override
    public void updateEntityFromDto(PacienteDto dto, Paciente entity) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getId() != null ) {
            entity.setId( dto.getId() );
        }
        if ( dto.getNome() != null ) {
            entity.setNome( dto.getNome() );
        }
        if ( dto.getCpf() != null ) {
            entity.setCpf( dto.getCpf() );
        }
        if ( dto.getDataNascimento() != null ) {
            entity.setDataNascimento( dto.getDataNascimento() );
        }
        if ( dto.getEndereco() != null ) {
            entity.setEndereco( dto.getEndereco() );
        }
        if ( dto.getTelefone() != null ) {
            entity.setTelefone( dto.getTelefone() );
        }
        if ( dto.getProntuario() != null ) {
            if ( entity.getProntuario() == null ) {
                entity.setProntuario( new Prontuario() );
            }
            prontuarioDtoToProntuario1( dto.getProntuario(), entity.getProntuario() );
        }
    }

    protected Prontuario prontuarioDtoToProntuario(ProntuarioDto prontuarioDto) {
        if ( prontuarioDto == null ) {
            return null;
        }

        Prontuario prontuario = new Prontuario();

        prontuario.setId( prontuarioDto.getId() );
        prontuario.setDataCriacao( prontuarioDto.getDataCriacao() );

        return prontuario;
    }

    protected ProntuarioDto prontuarioToProntuarioDto(Prontuario prontuario) {
        if ( prontuario == null ) {
            return null;
        }

        ProntuarioDto prontuarioDto = new ProntuarioDto();

        prontuarioDto.setId( prontuario.getId() );
        prontuarioDto.setDataCriacao( prontuario.getDataCriacao() );

        return prontuarioDto;
    }

    protected void prontuarioDtoToProntuario1(ProntuarioDto prontuarioDto, Prontuario mappingTarget) {
        if ( prontuarioDto == null ) {
            return;
        }

        if ( prontuarioDto.getId() != null ) {
            mappingTarget.setId( prontuarioDto.getId() );
        }
        if ( prontuarioDto.getDataCriacao() != null ) {
            mappingTarget.setDataCriacao( prontuarioDto.getDataCriacao() );
        }
    }
}
