package com.prontuarioMedico.service;

import com.prontuarioMedico.dto.*;
import com.prontuarioMedico.entities.*;
import com.prontuarioMedico.repositories.ConsultaRepository;
import com.prontuarioMedico.repositories.PacienteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ConsultaService {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    public List<ConsultaDto> findAll() {
        List<Consulta> consultas = consultaRepository.findAll();
        return consultas.stream().map(this::toDto).collect(Collectors.toList());
    }

    public Optional<ConsultaDto> findById(Long id) {
        return consultaRepository.findById(id).map(this::toDto);
    }

    @Transactional
    public ConsultaDto save(ConsultaDto dto) {
        Consulta consulta = toEntity(dto);
        System.out.println("Data antes de persistir no banco: " + consulta.getDataConsulta());

        consulta.getDiagnosticos().forEach(d -> d.setConsulta(consulta));
        consulta.getExames().forEach(e -> e.setConsulta(consulta));
        consulta.getPrescricoes().forEach(p -> p.setConsulta(consulta));

        Consulta savedConsulta = consultaRepository.save(consulta);
        return toDto(savedConsulta);
    }

    @Transactional
    public Optional<ConsultaDto> update(Long id, ConsultaDto dto) {
        return consultaRepository.findById(id).map(existingConsulta -> {
            updateEntityFromDto(dto, existingConsulta);
            Consulta updated = consultaRepository.save(existingConsulta);
            return toDto(updated);
        });
    }

    public void deleteById(Long id) {
        consultaRepository.deleteById(id);
    }

    /**
     * Método para converter entidade `Consulta` para `ConsultaDto`
     */
    private ConsultaDto toDto(Consulta consulta) {
        return new ConsultaDto(
                consulta.getId(),
                consulta.getPaciente() != null
                        ? new PacienteDto(consulta.getPaciente().getId(), consulta.getPaciente().getNome(),
                        consulta.getPaciente().getCpf()) // 🔹 Ajustado para um construtor válido de PacienteDto
                        : null,
                consulta.getDataHora(),
                consulta.getDiagnosticos() != null
                        ? consulta.getDiagnosticos().stream().map(this::toDiagnosticoDto).collect(Collectors.toList())
                        : Collections.emptyList(),
                consulta.getPrescricoes() != null
                        ? consulta.getPrescricoes().stream().map(this::toPrescricaoDto).collect(Collectors.toList())
                        : Collections.emptyList(),
                consulta.getExames() != null
                        ? consulta.getExames().stream().map(this::toExameDto).collect(Collectors.toList())
                        : Collections.emptyList(),
                null
        );
    }

    /**
     * Método para converter `ConsultaDto` em entidade `Consulta`
     */
    private Consulta toEntity(ConsultaDto dto) {
        Consulta consulta = new Consulta();
        consulta.setId(dto.getId());

        if (dto.getPaciente() != null && dto.getPaciente().getId() != null) {
            Paciente paciente = pacienteRepository.findById(dto.getPaciente().getId())
                    .orElseThrow(() -> new RuntimeException("Paciente não encontrado!"));
            consulta.setPaciente(paciente);
        } else {
            throw new RuntimeException("❌ Erro: Paciente é obrigatório!");
        }

        consulta.setDataConsulta(dto.getDataConsulta());
        consulta.setDataHora(dto.getDataConsulta());
        consulta.setImageUrl(dto.getImageUrl()); // ✅ Mapeando o campo corretamente

        consulta.setDiagnosticos(dto.getDiagnosticos() != null
                ? dto.getDiagnosticos().stream().map(this::toDiagnosticoEntity).collect(Collectors.toList())
                : Collections.emptyList());

        consulta.setPrescricoes(dto.getPrescricoes() != null
                ? dto.getPrescricoes().stream().map(this::toPrescricaoEntity).collect(Collectors.toList())
                : Collections.emptyList());

        consulta.setExames(dto.getExames() != null
                ? dto.getExames().stream().map(this::toExameEntity).collect(Collectors.toList())
                : Collections.emptyList());

        return consulta;
    }


    /**
     * Atualiza uma entidade `Consulta` com base no DTO recebido
     */
    private void updateEntityFromDto(ConsultaDto dto, Consulta consulta) {
        if (dto.getPaciente() != null && dto.getPaciente().getId() != null) {
            Paciente paciente = pacienteRepository.findById(dto.getPaciente().getId())
                    .orElseThrow(() -> new RuntimeException("Paciente não encontrado!"));
            consulta.setPaciente(paciente);
        } else {
            throw new RuntimeException("❌ Erro: Paciente é obrigatório!");
        }

        consulta.setDataConsulta(dto.getDataConsulta());
        consulta.setDataHora(dto.getDataConsulta());

        consulta.setDiagnosticos(dto.getDiagnosticos() != null
                ? dto.getDiagnosticos().stream().map(this::toDiagnosticoEntity).collect(Collectors.toList())
                : Collections.emptyList());

        consulta.setPrescricoes(dto.getPrescricoes() != null
                ? dto.getPrescricoes().stream().map(this::toPrescricaoEntity).collect(Collectors.toList())
                : Collections.emptyList());

        consulta.setExames(dto.getExames() != null
                ? dto.getExames().stream().map(this::toExameEntity).collect(Collectors.toList())
                : Collections.emptyList());
    }

    /**
     * Métodos auxiliares para conversão de `Diagnostico`, `Exame` e `Prescricao`
     */
    private DiagnosticoDto toDiagnosticoDto(Diagnostico diagnostico) {
        return new DiagnosticoDto(
                diagnostico.getId(),
                diagnostico.getDescricao()
        );
    }

    private Diagnostico toDiagnosticoEntity(DiagnosticoDto dto) {
        Diagnostico diagnostico = new Diagnostico();
        diagnostico.setId(dto.getId());
        diagnostico.setDescricao(dto.getDescricao());
        return diagnostico;
    }

    private ExameDto toExameDto(Exame exame) {
        return new ExameDto(
                exame.getId(),
                exame.getNome(),
                exame.getResultado()
        );
    }

    private Exame toExameEntity(ExameDto dto) {
        Exame exame = new Exame();
        exame.setId(dto.getId());
        exame.setTipo(dto.getTipo() != null ? dto.getTipo() : "Desconhecido"); // ✅ Evita null
        exame.setNome(dto.getNome());
        exame.setResultado(dto.getResultado());
        return exame;
    }

    private PrescricaoDto toPrescricaoDto(Prescricao prescricao) {
        return new PrescricaoDto(
                prescricao.getId(),
                prescricao.getMedicamento(),
                prescricao.getDosagem()
        );
    }

    private Prescricao toPrescricaoEntity(PrescricaoDto dto) {
        Prescricao prescricao = new Prescricao();
        prescricao.setId(dto.getId());
        prescricao.setMedicamento(dto.getMedicamento());
        prescricao.setDosagem(dto.getDosagem());
        return prescricao;
    }
}
