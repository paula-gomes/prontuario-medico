package com.prontuarioMedico.entities;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "consultas")
public class Consulta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String paciente;

    @Column(nullable = false)
    private LocalDateTime dataConsulta;

    @OneToMany(mappedBy = "consulta", cascade = CascadeType.ALL)
    private List<Diagnostico> diagnosticos;

    @OneToMany(mappedBy = "consulta", cascade = CascadeType.ALL)
    private List<Prescricao> prescricoes;

    // Getters and Setters
}