package com.prontuarioMedico.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "prontuarios")
@Data
public class Prontuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime dataCriacao = LocalDateTime.now();

    @OneToOne
    @JoinColumn(name = "paciente_id", nullable = false, unique = true)
    @JsonBackReference(value = "prontuario-paciente")
    private Paciente paciente;

}
