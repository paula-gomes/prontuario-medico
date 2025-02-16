package com.prontuarioMedico.access;


import com.prontuarioMedico.entities.Consulta;
import com.prontuarioMedico.entities.Exame;

import java.time.LocalDateTime;

public class ExameAccess {

    private final Exame exame;

    public ExameAccess(Exame exame) {
        this.exame = exame;
    }

    public Consulta getConsulta() {
        return exame.consulta;
    }

    public void setConsulta(Consulta consulta) {
        exame.consulta = consulta;
    }

    public String getTipo() {
        return exame.tipo;
    }

    public void setTipo(String tipo) {
        exame.tipo = tipo;
    }

    public LocalDateTime getDataExame() {
        return exame.dataExame;
    }

    public void setDataExame(LocalDateTime dataExame) {
        exame.dataExame = dataExame;
    }
}