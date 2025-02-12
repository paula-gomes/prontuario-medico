package com.prontuarioMedico.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "prescricoes")
@Getter
@Setter
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