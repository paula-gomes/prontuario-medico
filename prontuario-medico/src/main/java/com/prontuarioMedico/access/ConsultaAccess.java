package com.prontuarioMedico.access;


import com.prontuarioMedico.entities.Consulta;
import com.prontuarioMedico.entities.Diagnostico;
import com.prontuarioMedico.entities.Exame;
import com.prontuarioMedico.entities.Prescricao;

import java.time.LocalDateTime;
import java.util.List;

public class ConsultaAccess {

    private final Consulta consulta;

    public ConsultaAccess(Consulta consulta) {
        this.consulta = consulta;
    }

    public String getPaciente() {
        return consulta.paciente;
    }

    public void setPaciente(String paciente) {
        consulta.paciente = paciente;
    }

    public LocalDateTime getDataConsulta() {
        return consulta.dataConsulta;
    }

    public void setDataConsulta(LocalDateTime dataConsulta) {
        consulta.dataConsulta = dataConsulta;
    }

    public List<Diagnostico> getDiagnosticos() {
        return consulta.diagnosticos;
    }

    public void setDiagnosticos(List<Diagnostico> diagnosticos) {
        consulta.diagnosticos = diagnosticos;
    }

    public List<Exame> getExames() {
        return consulta.exames;
    }

    public void setExames(List<Exame> exames) {
        consulta.exames = exames;
    }

    public List<Prescricao> getPrescricoes() {
        return consulta.prescricoes;
    }

    public void setPrescricoes(List<Prescricao> prescricoes) {
        consulta.prescricoes = prescricoes;
    }

    public String getImageUrl() {
        return consulta.imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        consulta.imageUrl = imageUrl;
    }
}