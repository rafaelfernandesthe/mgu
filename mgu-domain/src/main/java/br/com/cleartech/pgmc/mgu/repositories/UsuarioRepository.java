package br.com.cleartech.pgmc.mgu.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cleartech.pgmc.mgu.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
