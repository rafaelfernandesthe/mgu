package br.com.cleartech.pgmc.mgu.services;

import java.util.List;

import br.com.cleartech.pgmc.mgu.entities.Perfil;
import br.com.cleartech.pgmc.mgu.entities.Sistema;

public interface PerfilService {

	public List<Perfil> findByPrestadora( Long idPrestadora );

	public List<Perfil> findBySistema( Sistema sistema );

}
