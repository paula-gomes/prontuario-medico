package com.prontuarioMedico.access;

import com.prontuarioMedico.entities.Consulta;
import com.prontuarioMedico.entities.Diagnostico;

public class DiagnosticoAccess {

    private final Diagnostico diagnostico;

    public DiagnosticoAccess(Diagnostico diagnostico) {
        this.diagnostico = diagnostico;
    }

    public Consulta getConsulta() {
        return diagnostico.consulta;
    }

    public void setConsulta(Consulta consulta) {
        diagnostico.consulta = consulta;
    }

    public String getDescricao() {
        return diagnostico.descricao;
    }

    public void setDescricao(String descricao) {
        diagnostico.descricao = descricao;
    }
}
