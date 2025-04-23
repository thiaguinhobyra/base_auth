package com.br.base.auth.repositories;

import com.br.base.auth.models.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PerfilRepository extends JpaRepository<Perfil, Long> {
}
