package com.prontuarioMedico.dto;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class ProntuarioDto {

    private Long id;

    @NotNull(message = "A data da criação é obrigatória.")
    private LocalDateTime dataCriacao;

    private Long pacienteId;
    private String descricao;

    public ProntuarioDto() {
        this.dataCriacao = LocalDateTime.now();
    }

    public ProntuarioDto(Long id, LocalDateTime dataCriacao, Long pacienteId) {
        this.id = id;
        this.dataCriacao = dataCriacao;
        this.pacienteId = pacienteId;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public ProntuarioDto(Long id, String descricao) {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Long getPacienteId() {
        return pacienteId;
    }

    public void setPacienteId(Long pacienteId) {
        this.pacienteId = pacienteId;
    }
}
