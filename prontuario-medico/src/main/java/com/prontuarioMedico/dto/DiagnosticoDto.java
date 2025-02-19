package com.prontuarioMedico.dto;

import com.prontuarioMedico.entities.Consulta;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.time.LocalDateTime;

public class DiagnosticoDto {

    @ManyToOne
    @JoinColumn(name = "consulta_id", nullable = false)
    public Consulta consulta;

    @Column(nullable = false)
    public String descricao;

    private LocalDateTime dataDiagnostico = LocalDateTime.now();

    public Consulta getConsulta() {
        return consulta;
    }

    public void setConsulta(Consulta consulta) {
        this.consulta = consulta;
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
