package com.br.base.auth.controllers;

import com.br.base.auth.dto.UsuarioDTO;
import com.br.base.auth.models.Perfil;
import com.br.base.auth.models.Usuario;
import com.br.base.auth.services.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<Usuario> create(@RequestBody @Valid UsuarioDTO usuarioDTO){
        Usuario usuario = usuarioService.create(usuarioDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> findAll() {
        List<Usuario> usuarios = usuarioService.findAll();
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/param")
    public ResponseEntity<Usuario> findOne(
        @RequestParam(name = "id", required = false) Long id,
        @RequestParam(name = "email", required = false) String email,
        @RequestParam(name = "nome", required = false) String nome,
        @RequestParam(name = "cpf", required = false) String cpf
    ) {
        Usuario usuario = usuarioService.findOne(id, email, nome, cpf);
        return ResponseEntity.ok(usuario);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> findById(@RequestParam(name = "id", required = false) Long id) throws SQLException {
        Usuario usuario = usuarioService.findById(id);
        if (!Objects.isNull(usuario))
            return ResponseEntity.ok(usuario);
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{email}")
    public ResponseEntity<Usuario> findByEmail(@RequestParam(name = "email", required = false) String email) throws SQLException {
        Usuario usuario = usuarioService.findByEmail(email);
        if (!Objects.isNull(usuario))
            return ResponseEntity.ok(usuario);
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(
            @RequestParam(name = "id", required = true) Long id,
            @RequestParam(name = "nome", required = false) String nome,
            @RequestParam(name = "perfil", required = false) Perfil perfil,
            @RequestParam(name = "status", required = false) boolean status
    ) {
        usuarioService.update(id, nome, perfil, status);
        return ResponseEntity.ok("Usuário atualizado com sucesso. ");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(
            @RequestParam(name = "id") Long id
    ) {
        usuarioService.delete(id);
        return ResponseEntity.ok("Usuário removido com sucesso. ");
    }
}
