package com.prontuarioMedico.dto;

import java.time.LocalDateTime;
import java.util.List;
public class ConsultaDto {
    private Long id;
    private PacienteDto paciente;
    private LocalDateTime dataConsulta;
    private List<DiagnosticoDto> diagnosticos;
    private List<PrescricaoDto> prescricoes;
    private List<ExameDto> exames;
    private String imageUrl;

    public ConsultaDto(Long id, PacienteDto paciente, LocalDateTime dataConsulta, List<DiagnosticoDto> diagnosticos, List<PrescricaoDto> prescricoes, List<ExameDto> exames, Object o) {
        this.id = id;
        this.paciente = paciente;
        this.dataConsulta = dataConsulta;
        this.diagnosticos = diagnosticos;
        this.prescricoes = prescricoes;
        this.exames = exames;
    }

    public Long getId() {
        return id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public PacienteDto getPaciente() {
        return paciente;
    }

    public LocalDateTime getDataConsulta() {
        return dataConsulta;
    }

    public List<DiagnosticoDto> getDiagnosticos() {
        return diagnosticos;
    }

    public List<PrescricaoDto> getPrescricoes() {
        return prescricoes;
    }

    public List<ExameDto> getExames() {
        return exames;
    }
}
