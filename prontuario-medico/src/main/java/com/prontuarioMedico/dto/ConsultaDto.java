package com.prontuarioMedico.dto;

import com.prontuarioMedico.entities.Diagnostico;
import com.prontuarioMedico.entities.Exame;
import com.prontuarioMedico.entities.Prescricao;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

public class ConsultaDto {
    @Column(nullable = false)
    public String paciente;

    @Column(nullable = false)
    public LocalDateTime dataConsulta;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "consulta_diagnostico",
            joinColumns = @JoinColumn(name = "consulta_id"),
            inverseJoinColumns = @JoinColumn(name = "diagnostico_id")
    )
    public List<Diagnostico> diagnosticos;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "consulta_prescricao",
            joinColumns = @JoinColumn(name = "consulta_id"),
            inverseJoinColumns = @JoinColumn(name = "prescricao_id")
    )
    public List<Prescricao> prescricoes;

    @OneToMany(mappedBy = "consulta", cascade = CascadeType.ALL)
    public List<Exame> exames;

    public String imageUrl;

    public String getPaciente() {
        return paciente;
    }

    public void setPaciente(String paciente) {
        this.paciente = paciente;
    }

    public LocalDateTime getDataConsulta() {
        return dataConsulta;
    }

    public void setDataConsulta(LocalDateTime dataConsulta) {
        this.dataConsulta = dataConsulta;
    }

    public List<Diagnostico> getDiagnosticos() {
        return diagnosticos;
    }

    public void setDiagnosticos(List<Diagnostico> diagnosticos) {
        this.diagnosticos = diagnosticos;
    }

    public List<Prescricao> getPrescricoes() {
        return prescricoes;
    }

    public void setPrescricoes(List<Prescricao> prescricoes) {
        this.prescricoes = prescricoes;
    }

    public List<Exame> getExames() {
        return exames;
    }

    public void setExames(List<Exame> exames) {
        this.exames = exames;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
