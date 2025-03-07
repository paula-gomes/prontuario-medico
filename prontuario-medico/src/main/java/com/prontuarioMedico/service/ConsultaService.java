package com.prontuarioMedico.service;

import com.prontuarioMedico.dto.*;
import com.prontuarioMedico.entities.*;
import com.prontuarioMedico.repositories.ConsultaRepository;
import com.prontuarioMedico.repositories.PacienteRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
        Consulta consulta;

        // Se for um novo cadastro, cria uma nova consulta
        if (dto.getId() != null) {
            consulta = consultaRepository.findById(dto.getId())
                    .orElseThrow(() -> new EntityNotFoundException("Consulta não encontrada"));
        } else {
            consulta = new Consulta();
        }

        consulta.setDataConsulta(dto.getDataConsulta());

        // Garantindo que o campo `dataHora` seja preenchido
        if (dto.getDataConsulta() != null) {
            consulta.setDataHora(dto.getDataConsulta());
        } else {
            consulta.setDataHora(LocalDateTime.now());
        }

        // Buscar o paciente apenas pelo ID
        if (dto.getPaciente() != null && dto.getPaciente().getId() != null) {
            Paciente paciente = pacienteRepository.findById(dto.getPaciente().getId())
                    .orElseThrow(() -> new EntityNotFoundException("Paciente não encontrado"));
            consulta.setPaciente(paciente);
        } else {
            throw new IllegalArgumentException("ID do paciente é obrigatório para marcar consulta.");
        }

        // Verifica se há exames antes de associar
        if (dto.getExames() != null) {
            List<Exame> exames = dto.getExames().stream()
                    .map(this::toExameEntity)
                    .collect(Collectors.toList());

            for (Exame exame : exames) {
                exame.setConsulta(consulta); // 🔹 Vincula corretamente a consulta ao exame
            }

            consulta.setExames(exames);
        } else {
            consulta.setExames(Collections.emptyList());
        }


        // Verifica se há diagnósticos antes de associar
        consulta.setDiagnosticos(dto.getDiagnosticos() != null
                ? dto.getDiagnosticos().stream().map(this::toDiagnosticoEntity).collect(Collectors.toList())
                : Collections.emptyList());

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
                        ? new PacienteDto(
                        consulta.getPaciente().getId(),
                        consulta.getPaciente().getNome(),
                        consulta.getPaciente().getCpf(),
                        consulta.getPaciente().getDataNascimento(),
                        consulta.getPaciente().getEndereco(),
                        consulta.getPaciente().getTelefone(),
                        consulta.getPaciente().getProntuario() != null ? consulta.getPaciente().getProntuario().getId() : null
                ) : null,
                consulta.getDataConsulta(),
                consulta.getDiagnosticos() != null
                        ? consulta.getDiagnosticos().stream().map(this::toDiagnosticoDto).collect(Collectors.toList())
                        : Collections.emptyList(),
                consulta.getPrescricoes() != null
                        ? consulta.getPrescricoes().stream().map(this::toPrescricaoDto).collect(Collectors.toList())
                        : Collections.emptyList(),
                consulta.getExames() != null && !consulta.getExames().isEmpty()
                        ? consulta.getExames().stream().map(this::toExameDto).collect(Collectors.toList())
                        : Collections.emptyList(),
                consulta.getImageUrl()
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
                exame.getConsulta() != null ? exame.getConsulta().getId() : null,
                exame.getTipo() != null ? exame.getTipo() : "Tipo não informado",
                exame.getNome() != null ? exame.getNome() : "Nome não informado",
                exame.getResultado() != null ? exame.getResultado() : "Resultado não informado"
        );
    }



    private Exame toExameEntity(ExameDto dto) {
        Exame exame = new Exame();
        exame.setId(null); // 🔹 Garante que será tratado como novo, mesmo se vier com ID
        exame.setNome(dto.getNome());
        exame.setResultado(dto.getResultado());
        exame.setTipo(dto.getTipo() != null ? dto.getTipo() : "Não especificado");

        return exame; // A associação com a consulta será feita no método `save`
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
