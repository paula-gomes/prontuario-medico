package com.prontuarioMedico.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;


public class ConsultaDto {
    private PacienteDto paciente;
    private LocalDateTime dataConsulta;
    private List<DiagnosticoDto> diagnosticos;
    private List<PrescricaoDto> prescricoes;
    private List<ExameDto> exames;
    private String imageUrl;

    public PacienteDto getPaciente() {
        return paciente;
    }

    public void setPaciente(PacienteDto paciente) {
        this.paciente = paciente;
    }

    public LocalDateTime getDataConsulta() {
        return dataConsulta;
    }

    public void setDataConsulta(LocalDateTime dataConsulta) {
        this.dataConsulta = dataConsulta;
    }

    public List<DiagnosticoDto> getDiagnosticos() {
        return diagnosticos;
    }

    public void setDiagnosticos(List<DiagnosticoDto> diagnosticos) {
        this.diagnosticos = diagnosticos;
    }

    public List<PrescricaoDto> getPrescricoes() {
        return prescricoes;
    }

    public void setPrescricoes(List<PrescricaoDto> prescricoes) {
        this.prescricoes = prescricoes;
    }

    public List<ExameDto> getExames() {
        return exames;
    }

    public void setExames(List<ExameDto> exames) {
        this.exames = exames;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
