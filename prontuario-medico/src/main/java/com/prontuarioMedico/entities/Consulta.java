package com.prontuarioMedico.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "consultas")
public class Consulta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
