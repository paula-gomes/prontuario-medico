package com.prontuarioMedico.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "consultas")
public class Consulta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "paciente_id", nullable = false)
    private Paciente paciente;

    @Column(nullable = false)
    public LocalDateTime dataConsulta;

    @JsonManagedReference(value = "consulta-diagnosticos")
    @OneToMany(mappedBy = "consulta", cascade = CascadeType.ALL)
    private List<Diagnostico> diagnosticos;

    @JsonManagedReference(value = "prescricao-consulta")
    @OneToMany(mappedBy = "consulta", cascade = CascadeType.ALL)
    private List<Prescricao> prescricoes;

    @JsonManagedReference(value = "exame-consulta")
    @OneToMany(mappedBy = "consulta", cascade = CascadeType.ALL)
    private List<Exame> exames;

    private String imageUrl;

}
