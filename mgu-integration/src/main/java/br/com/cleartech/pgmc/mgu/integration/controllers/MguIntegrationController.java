package br.com.cleartech.pgmc.mgu.integration.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cleartech.pgmc.mgu.enums.CodigoMensagem;

@RestController
public class MguIntegrationController {

	// @Autowired
	// private PerfilService perfilService;

	@GetMapping( "/status2" )
	public CodigoMensagem status2() {
		return CodigoMensagem.RETORNO_20;
	}
	//
	// @GetMapping( "/status" )
	// public String status() {
	// return "SUCESSO";
	// }
	//
	// @PostMapping( "/sistema" )
	// public MguResposta getPerfis( @RequestBody Sistema sistema ) throws
	// Exception {
	// CodigoMensagem codigo = null;
	// MguResposta mgu = new MguResposta();
	// if ( sistema == null || sistema.getDcSistema() == null ||
	// sistema.getDcSistema().isEmpty() ) {
	// codigo = CodigoMensagem.RETORNO_19;
	// } else {
	// List<Perfil> listPerfil = perfilService.findBySistema( sistema );
	// if ( listPerfil == null || listPerfil.isEmpty() ) {
	// codigo = CodigoMensagem.RETORNO_21;
	// } else {
	// Perfil geral = new Perfil();
	// geral.setListaPerfil( listPerfil );
	// // for (Perfil ite : listPerfil) {
	// // Perfil i = new Perfil();
	// // i.setId(ite.getId());
	// // i.setDcPerfil(ite.getDcPerfil());
	// // geral.addPerfil(i);
	// // }
	// codigo = CodigoMensagem.RETORNO_20;
	// mgu.setPerfil( geral );
	// }
	// }
	// mgu.setRetorno( codigo.getCodigo() );
	// mgu.setDescricao( codigo.getDescricao() );
	// return mgu;
	// }
}
