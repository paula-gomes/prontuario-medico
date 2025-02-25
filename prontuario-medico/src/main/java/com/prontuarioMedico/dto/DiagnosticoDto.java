package com.prontuarioMedico.dto;

import com.prontuarioMedico.entities.Consulta;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.time.LocalDateTime;

public class DiagnosticoDto {


    @Column(nullable = false)
    public String descricao;

    private LocalDateTime dataDiagnostico = LocalDateTime.now();


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
