package com.prontuarioMedico.dto;

import com.prontuarioMedico.entities.Consulta;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.time.LocalDateTime;


public class ExameDto {
    @ManyToOne
    @JoinColumn(name = "consulta_id", nullable = false)
    public Consulta consulta;

    @Column(nullable = false)
    public String tipo;

    public LocalDateTime dataExame = LocalDateTime.now();

    public Consulta getConsulta() {
        return consulta;
    }

    public void setConsulta(Consulta consulta) {
        this.consulta = consulta;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public LocalDateTime getDataExame() {
        return dataExame;
    }

    public void setDataExame(LocalDateTime dataExame) {
        this.dataExame = dataExame;
    }
}
