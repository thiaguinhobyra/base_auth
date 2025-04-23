package com.br.base.auth.dto;

import com.br.base.auth.models.Perfil;

public class UsuarioDTO {
    Long id;
    String nome;
    String email;
    String senha;
    boolean status;
    Perfil perfil;
    String cpf;
    String matricula;

    public UsuarioDTO(Long id, String nome, String email, String senha, boolean status, String cpf, String matricula, Perfil perfil) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.status = status;
        this.cpf = cpf;
        this.matricula = matricula;
        this.perfil = perfil;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public boolean isStatus() {
        return status;
    }

    public String getCpf() {
        return cpf;
    }

    public String getMatricula() {
        return matricula;
    }

    public Perfil getPerfil() {
        return perfil;
    }
}
