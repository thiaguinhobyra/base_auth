package com.br.base.auth.services;

import com.br.base.auth.dto.UsuarioDTO;
import com.br.base.auth.models.Perfil;
import com.br.base.auth.models.Usuario;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UsuarioService {
    Usuario create(UsuarioDTO usuarioDTO);

    Usuario findOne(Long id, String email, String nome, String cpf);

    List<Usuario> findAll();

    Usuario findById(Long id);

    Usuario findByEmail(String email);

    void update(Long id, String nome, Perfil perfil, boolean status);

    void updateSenha(Long id, String novaSenha);

    void delete(Long id);

}
