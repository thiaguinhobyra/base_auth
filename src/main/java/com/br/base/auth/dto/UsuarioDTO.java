package com.br.base.auth.dto;

public class UsuarioDTO {
    Long id;
    String firstName;
    String lastName;
    String cpf;
    String matricula;

    public UsuarioDTO(Long id, String firstName, String lastName, String cpf, String matricula) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.cpf = cpf;
        this.matricula = matricula;
    }
}
