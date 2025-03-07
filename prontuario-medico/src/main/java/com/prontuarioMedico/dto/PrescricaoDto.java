package com.prontuarioMedico.dto;

import java.time.LocalDateTime;

public class PrescricaoDto {
    private Long id;
    private Long consultaId; // Apenas o ID da consulta
    private String medicamento;
    private String dosagem;
    private LocalDateTime dataPrescricao;

    public PrescricaoDto() {
        this.dataPrescricao = LocalDateTime.now();
    }

    public PrescricaoDto(Long id, Long consultaId, String medicamento, String dosagem, LocalDateTime dataPrescricao) {
        this.id = id;
        this.consultaId = consultaId;
        this.medicamento = medicamento;
        this.dosagem = dosagem;
        this.dataPrescricao = dataPrescricao;
    }

    public PrescricaoDto(Long id, String medicamento, String dosagem) {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getConsultaId() {
        return consultaId;
    }

    public void setConsultaId(Long consultaId) {
        this.consultaId = consultaId;
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
}
