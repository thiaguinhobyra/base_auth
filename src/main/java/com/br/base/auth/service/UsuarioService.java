package com.br.base.auth.service;

import com.br.base.auth.dto.UsuarioDTO;
import com.br.base.auth.models.Usuario;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UsuarioService {
    Usuario create(UsuarioDTO usuarioDTO);

    List<Usuario> findUsuarios(UsuarioDTO usuarioDTO);

    Usuario findById(Long id);

    Usuario findByEmail(String email);

    void update(UsuarioDTO usuarioDTO);

    void updateSenha(Long id, String novaSenha);

}
