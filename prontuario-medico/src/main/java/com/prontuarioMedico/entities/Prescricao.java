package com.prontuarioMedico.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "prescricoes")
public class Prescricao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonBackReference(value = "prescricao-consulta")
    @ManyToOne
    @JoinColumn(name = "consulta_id", nullable = false)
    private Consulta consulta;

    @Column(nullable = false)
    private String medicamento;

    private String dosagem;

    private LocalDateTime dataPrescricao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Consulta getConsulta() {
        return consulta;
    }

    public void setConsulta(Consulta consulta) {
        this.consulta = consulta;
    }

    public String getMedicamento() {
        return medicamento;
    }

    public void setMedicamento(String medicamento) {
        this.medicamento = medicamento;
    }

    public String getDosagem() {
        return dosagem;
    }

    public void setDosagem(String dosagem) {
        this.dosagem = dosagem;
    }

    public LocalDateTime getDataPrescricao() {
        return dataPrescricao;
    }

    public void setDataPrescricao(LocalDateTime dataPrescricao) {
        this.dataPrescricao = dataPrescricao;
    }

    @PrePersist
    protected void prePersist() {
        if (dataPrescricao == null) {
            dataPrescricao = LocalDateTime.now();
        }
    }
}
