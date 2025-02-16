package com.prontuarioMedico.dto;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public class PacienteDto {

    private Long id;

    @NotBlank(message = "O nome é obrigatório.")
    @Size(max = 100, message = "O nome deve ter no máximo 100 caracteres.")
    private String nome;

    @NotBlank(message = "O CPF é obrigatório.")
    @Pattern(regexp = "\\d{11}", message = "O CPF deve conter 11 dígitos numéricos.")
    private String cpf;

    @NotNull(message = "A data de nascimento é obrigatória.")
    private LocalDate dataNascimento;

    @Size(max = 200, message = "O endereço deve ter no máximo 200 caracteres.")
    private String endereco;

    @Pattern(regexp = "\\d{10,11}", message = "O telefone deve conter entre 10 e 11 dígitos numéricos.")
    private String telefone;

    private ProntuarioDto prontuario;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank(message = "O nome é obrigatório.") @Size(max = 100, message = "O nome deve ter no máximo 100 caracteres.") String getNome() {
        return nome;
    }

    public void setNome(@NotBlank(message = "O nome é obrigatório.") @Size(max = 100, message = "O nome deve ter no máximo 100 caracteres.") String nome) {
        this.nome = nome;
    }

    public @NotBlank(message = "O CPF é obrigatório.") @Pattern(regexp = "\\d{11}", message = "O CPF deve conter 11 dígitos numéricos.") String getCpf() {
        return cpf;
    }

    public void setCpf(@NotBlank(message = "O CPF é obrigatório.") @Pattern(regexp = "\\d{11}", message = "O CPF deve conter 11 dígitos numéricos.") String cpf) {
        this.cpf = cpf;
    }

    public @NotNull(message = "A data de nascimento é obrigatória.") LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(@NotNull(message = "A data de nascimento é obrigatória.") LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public @Size(max = 200, message = "O endereço deve ter no máximo 200 caracteres.") String getEndereco() {
        return endereco;
    }

    public void setEndereco(@Size(max = 200, message = "O endereço deve ter no máximo 200 caracteres.") String endereco) {
        this.endereco = endereco;
    }

    public @Pattern(regexp = "\\d{10,11}", message = "O telefone deve conter entre 10 e 11 dígitos numéricos.") String getTelefone() {
        return telefone;
    }

    public void setTelefone(@Pattern(regexp = "\\d{10,11}", message = "O telefone deve conter entre 10 e 11 dígitos numéricos.") String telefone) {
        this.telefone = telefone;
    }

    public ProntuarioDto getProntuario() {
        return prontuario;
    }

    public void setProntuario(ProntuarioDto prontuario) {
        this.prontuario = prontuario;
    }
}
