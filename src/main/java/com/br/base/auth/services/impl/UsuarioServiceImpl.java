package com.br.base.auth.services.impl;

import com.br.base.auth.dto.UsuarioDTO;
import com.br.base.auth.models.Perfil;
import com.br.base.auth.models.Usuario;
import com.br.base.auth.repositories.PerfilRepository;
import com.br.base.auth.repositories.UsuarioRepository;
import com.br.base.auth.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PerfilRepository perfilRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Usuario create(UsuarioDTO usuarioDTO) throws RuntimeException{
        try {
            Optional<Usuario> verificaUsuario = usuarioRepository.findByEmail(usuarioDTO.getEmail());

            if(verificaUsuario != null) throw new RuntimeException("O email já está cadastrado. ");
            Usuario usuario = new Usuario();

            Perfil perfil = perfilRepository.findById(usuarioDTO.getPerfil().getId()).orElseThrow(() -> new RuntimeException("Perfil não encontrado. "));
            usuario.setPerfil(perfil);

            usuario.setNome(usuarioDTO.getNome().trim());
            usuario.setCpf(usuarioDTO.getCpf().trim());
            usuario.setEmail(usuarioDTO.getEmail().trim());
            usuario.setMatricula(usuarioDTO.getMatricula().trim());
            usuario.setStatus(usuarioDTO.isStatus());

            usuario.setSenha(passwordEncoder.encode(usuarioDTO.getSenha().trim()));

            return usuarioRepository.save(usuario);
        }
        catch (Exception e) {
            throw new RuntimeException("O Usuário não foi cadastrado. " + e.getMessage());
        }
    }

    @Override
    public List<Usuario> findAll() {
        try {
            return usuarioRepository.findAll();
        }
        catch (Exception e) {
            throw new RuntimeException("Nenhum Usuário foi encontrado. " + e.getMessage());
        }
    }

    @Override
    public Usuario findOne(Long id, String email, String nome, String cpf) {
        try {
            if (id != null) {
                return usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado. "));
            }

            if (email != null && !email.isBlank()) {
                return usuarioRepository.findByEmail(email.trim()).orElseThrow(() -> new RuntimeException("Usuário não encontrado. "));
            }

            if (cpf != null && !cpf.isBlank()) {
                return usuarioRepository.findByCpf(cpf.trim()).orElseThrow(() -> new RuntimeException("Usuário não encontrado. "));
            }

            if (nome != null && !nome.isBlank()) {
                return usuarioRepository.findByNome(nome.trim()).orElseThrow(() -> new RuntimeException("Usuário não encontrado. "));
            }

            return null;
        } catch (Exception e) {
            throw new IllegalArgumentException("É necessário informar algum parâmetro. " + e.getMessage());
        }
    }

    @Override
    public Usuario findById(Long id) {
        return usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado. "));
    }

    @Override
    public Usuario findByEmail(String email) {
        return usuarioRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("Usuário não encontrado. "));
    }

    @Override
    public void update(Long id, String nome, Perfil perfil, boolean status) throws RuntimeException {
        try {
            Usuario usuarioUpdate = usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado. "));

            if (nome != null && !nome.trim().isEmpty()) usuarioUpdate.setNome(nome.trim());

            if (status) usuarioUpdate.setStatus(status);

            if (perfil != null) {
                Perfil verificaPerfil = perfilRepository.findById(perfil.getId()).orElseThrow(() -> new RuntimeException("Perfil não encontrado. "));

                usuarioUpdate.setPerfil(verificaPerfil);
            }

            usuarioRepository.save(usuarioUpdate);
        }
        catch (Exception e) {
            throw new RuntimeException("O Usuário não foi alterado. " + e.getMessage());
        }
    }

    @Override
    public void updateSenha(Long id, String novaSenha) throws RuntimeException{
        try {
            Usuario usuarioCheck = usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado. "));

            if (novaSenha != null) usuarioCheck.setSenha(passwordEncoder.encode(novaSenha));

            usuarioRepository.save(usuarioCheck);
        }
        catch (Exception e) {
            throw new RuntimeException("A senha do Usuário não foi alterada. " + e.getMessage());
        }
    }

    @Override
    public void delete(Long id) {
        try {
            Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado. "));
            usuarioRepository.delete(usuario);
        }
        catch (Exception e)       {
            throw new RuntimeException("Usuário não pode ser excluido. " + e.getMessage());
        }
    }
}
