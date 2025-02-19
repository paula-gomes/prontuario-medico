package com.prontuarioMedico.service;

import com.prontuarioMedico.dto.ConsultaDto;
import com.prontuarioMedico.entities.Consulta;
import com.prontuarioMedico.mapper.ConsultaMapper;
import com.prontuarioMedico.repositories.ConsultaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConsultaService {

    @Autowired
    private ConsultaRepository consultaRepository;
    @Autowired
    private ConsultaMapper consultaMapper;

    public List<ConsultaDto> findAll() {
        List<Consulta> consultas = consultaRepository.findAll();
        return consultaMapper.toDtoList(consultas);
    }

    public Optional<ConsultaDto> findById(Long id) {
        return consultaRepository.findById(id)
                .map(consultaMapper::toDto);
    }

    @Transactional
    public ConsultaDto save(ConsultaDto dto) {
        Consulta consulta = consultaMapper.toEntity(dto);
        consulta = consultaRepository.save(consulta);
        return consultaMapper.toDto(consulta);
    }
    @Transactional
    public Optional<ConsultaDto> update(Long id, ConsultaDto dto) {
        return consultaRepository.findById(id).map(existingConsulta -> {
            consultaMapper.updateEntityFromDto(dto, existingConsulta);
            Consulta updated = consultaRepository.save(existingConsulta);
            return consultaMapper.toDto(updated);
        });
    }

    public void deleteById(Long id) {
        consultaRepository.deleteById(id);
    }
}