package br.com.cleartech.pgmc.mgu.services;

import java.util.List;

import br.com.cleartech.pgmc.mgu.entities.Perfil;

public interface PerfilService {

	public List<Perfil> findByPrestadora( Long idPrestadora );

}
