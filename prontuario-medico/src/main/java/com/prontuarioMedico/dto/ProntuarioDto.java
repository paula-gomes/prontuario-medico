package com.prontuarioMedico.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;


public class ProntuarioDto {

    private Long id;

    @NotNull(message = "A data da criação é obrigatoria.")
    private LocalDateTime dataCriacao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotNull(message = "A data da criação é obrigatoria.") LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(@NotNull(message = "A data da criação é obrigatoria.") LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }
}
