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
}
