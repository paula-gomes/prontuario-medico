package com.prontuarioMedico.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Getter
@Setter
@Table(name = "consultas")
public class Consulta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String paciente;

    @Column(nullable = false)
    private LocalDateTime dataConsulta;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "consulta_diagnostico",
            joinColumns = @JoinColumn(name = "consulta_id"),
            inverseJoinColumns = @JoinColumn(name = "diagnostico_id")
    )
    private List<Diagnostico> diagnosticos;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "consulta_prescricao",
            joinColumns = @JoinColumn(name = "consulta_id"),
            inverseJoinColumns = @JoinColumn(name = "prescricao_id")
    )
    private List<Prescricao> prescricoes;

    private List<Exame> exames;

    private String imageUrl;

    // Getters and Setters
}