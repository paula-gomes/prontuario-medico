package com.prontuarioMedico.entities;

import javax.persistence.*;


import java.time.LocalDate;

@Entity
@Table(name = "pacientes")
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(unique = true, nullable = false)
    private String cpf;

    private LocalDate dataNascimento;
    private String endereco;
    private String telefone;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "prontuario_id", referencedColumnName = "id")
    private Prontuario prontuario;


}