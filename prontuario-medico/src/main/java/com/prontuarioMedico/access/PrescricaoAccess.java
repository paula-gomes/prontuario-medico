package com.prontuarioMedico.access;

import com.prontuarioMedico.entities.Consulta;
import com.prontuarioMedico.entities.Prescricao;

import java.time.LocalDateTime;

public class PrescricaoAccess {

    private final Prescricao prescricao;

    public PrescricaoAccess(Prescricao prescricao) {
        this.prescricao = prescricao;
    }

    public Consulta getConsulta() {
        return prescricao.consulta;
    }

    public void setConsulta(Consulta consulta) {
        prescricao.consulta = consulta;
    }

    public String getMedicamento() {
        return prescricao.medicamento;
    }

    public void setMedicamento(String medicamento) {
        prescricao.medicamento = medicamento;
    }

    public String getDosagem() {
        return prescricao.dosagem;
    }

    public void setDosagem(String dosagem) {
        prescricao.dosagem = dosagem;
    }

    public LocalDateTime getDataPrescricao() {
        return prescricao.dataPrescricao;
    }

    public void setDataPrescricao(LocalDateTime dataPrescricao) {
        prescricao.dataPrescricao = dataPrescricao;
    }
}
