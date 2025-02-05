package com.prontuarioMedico.entities;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "exames")
public class Exame {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "consulta_id", nullable = false)
    private Consulta consulta;

    @Column(nullable = false)
    private String tipo;

    private LocalDateTime dataExame = LocalDateTime.now();

    // Getters and Setters
}