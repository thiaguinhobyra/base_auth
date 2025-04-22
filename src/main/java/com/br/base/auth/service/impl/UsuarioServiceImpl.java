package com.br.base.auth.service.impl;

import com.br.base.auth.dto.UsuarioDTO;
import com.br.base.auth.models.Usuario;
import com.br.base.auth.repositories.UsuarioRepository;
import com.br.base.auth.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

public class UsuarioServiceImpl implements UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Usuario create(UsuarioDTO usuarioDTO) {
        return null;
    }

    @Override
    public List<Usuario> findUsuarios(UsuarioDTO usuarioDTO) {
        return null;
    }

    @Override
    public Usuario findById(Long id) {
        return null;
    }

    @Override
    public Usuario findByEmail(String email) {
        return null;
    }

    @Override
    public void update(UsuarioDTO usuarioDTO) {

    }

    @Override
    public void updateSenha(Long id, String novaSenha) {

    }
}
