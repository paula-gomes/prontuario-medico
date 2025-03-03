package com.prontuarioMedico.dto;

import java.time.LocalDateTime;

public class ExameDto {
    private Long id;
    private Long consultaId;
    private String tipo;
    private LocalDateTime dataExame;
    private String nome;
    private String resultado;

    public ExameDto() {
        this.dataExame = LocalDateTime.now();
    }

    public ExameDto(Long id, String nome, String resultado) {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public LocalDateTime getDataExame() {
        return dataExame;
    }

    public void setDataExame(LocalDateTime dataExame) {
        this.dataExame = dataExame;
    }
}
