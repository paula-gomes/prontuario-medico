package com.prontuarioMedico.entities;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "diagnosticos")
public class Diagnostico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "consulta_id", nullable = false)
    private Consulta consulta;

    @Column(nullable = false)
    private String descricao;

    private LocalDateTime dataDiagnostico = LocalDateTime.now();

    // Getters and Setters
}