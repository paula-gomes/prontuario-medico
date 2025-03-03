package com.prontuarioMedico.dto;

import java.time.LocalDateTime;

public class DiagnosticoDto {
    private Long id;
    private String descricao;
    private LocalDateTime dataDiagnostico;

    public DiagnosticoDto() {
        this.dataDiagnostico = LocalDateTime.now();
    }

    public DiagnosticoDto(Long id, String descricao, LocalDateTime dataDiagnostico) {
        this.id = id;
        this.descricao = descricao;
        this.dataDiagnostico = dataDiagnostico;
    }

    public DiagnosticoDto(Long id, String descricao, Long aLong) {
    }

    public DiagnosticoDto(Long id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDateTime getDataDiagnostico() {
        return dataDiagnostico;
    }

    public void setDataDiagnostico(LocalDateTime dataDiagnostico) {
        this.dataDiagnostico = dataDiagnostico;
    }
}
