package com.prontuarioMedico.dto;

import java.time.LocalDate;

public class PacienteDto {

    private Long id;

    private String nome;

    private String cpf;

    private LocalDate dataNascimento;

    private String endereco;

    private String telefone;

    private ProntuarioDto prontuario;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public ProntuarioDto getProntuario() {
        return prontuario;
    }

    public void setProntuario(ProntuarioDto prontuario) {
        this.prontuario = prontuario;
    }
}
