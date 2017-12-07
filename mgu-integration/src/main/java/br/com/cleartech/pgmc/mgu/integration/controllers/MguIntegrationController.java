package br.com.cleartech.pgmc.mgu.integration.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.cleartech.pgmc.mgu.entities.Perfil;
import br.com.cleartech.pgmc.mgu.entities.Sistema;
import br.com.cleartech.pgmc.mgu.enums.CodigoMensagem;
import br.com.cleartech.pgmc.mgu.integration.models.MguResposta;
import br.com.cleartech.pgmc.mgu.services.PerfilService;

@RestController( "/mgu" )
public class MguIntegrationController {

	@Autowired
	private PerfilService perfilService;

	@PostMapping( "/sistema" )
	public MguResposta getPerfis( @RequestBody Sistema sistema ) throws Exception {
		CodigoMensagem codigo = null;
		MguResposta mgu = new MguResposta();
		if ( sistema == null || sistema.getDcSistema() == null || sistema.getDcSistema().isEmpty() ) {
			codigo = CodigoMensagem.RETORNO_19;
		} else {
			List<Perfil> listPerfil = perfilService.findBySistema( sistema );
			if ( listPerfil == null || listPerfil.isEmpty() ) {
				codigo = CodigoMensagem.RETORNO_21;
			} else {
				Perfil geral = new Perfil();
				geral.setListaPerfil( listPerfil );
				// for (Perfil ite : listPerfil) {
				// Perfil i = new Perfil();
				// i.setId(ite.getId());
				// i.setDcPerfil(ite.getDcPerfil());
				// geral.addPerfil(i);
				// }
				codigo = CodigoMensagem.RETORNO_20;
				mgu.setPerfil( geral );
			}
		}
		mgu.setRetorno( codigo.getCodigo() );
		mgu.setDescricao( codigo.getDescricao() );
		return mgu;
	}
}
