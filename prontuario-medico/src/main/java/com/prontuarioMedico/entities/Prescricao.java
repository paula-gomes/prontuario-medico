package com.prontuarioMedico.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "prescricoes")
public class Prescricao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "consulta_id", nullable = false)
    public Consulta consulta;

    @Column(nullable = false)
    public String medicamento;

    public String dosagem;
    public LocalDateTime dataPrescricao = LocalDateTime.now();

    // Getters and Setters
}