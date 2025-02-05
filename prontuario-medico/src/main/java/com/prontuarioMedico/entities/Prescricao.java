package com.prontuarioMedico.entities;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "prescricoes")
public class Prescricao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "consulta_id", nullable = false)
    private Consulta consulta;

    @Column(nullable = false)
    private String medicamento;

    private String dosagem;
    private LocalDateTime dataPrescricao = LocalDateTime.now();

    // Getters and Setters
}