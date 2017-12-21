package br.com.cleartech.pgmc.mgu.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;


import br.com.cleartech.pgmc.mgu.entities.GrupoPerfil;
import br.com.cleartech.pgmc.mgu.entities.Usuario;
import br.com.cleartech.pgmc.mgu.services.GrupoPerfilService;
import br.com.cleartech.pgmc.mgu.services.NivelEscalonamentoService;
import br.com.cleartech.pgmc.mgu.services.UsuarioService;
import br.com.cleartech.pgmc.mgu.utils.MappedViews;
import br.com.cleartech.pgmc.mgu.utils.MguUtils;

@Controller
@RequestMapping("/usuarioConsulta")
public class UsuarioConsultaController {

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private GrupoPerfilService grupoPerfilService;

	@Autowired
	private NivelEscalonamentoService nivelEscalonamentoService;
	

	@GetMapping
	public String init(Usuario usuario, Model model) {
		List<GrupoPerfil> listaGrupoPerfilTotal = getGrupoPerfilList();
		model.addAttribute("grupoPerfisSourceJSON", MguUtils.getVO2JSON(listaGrupoPerfilTotal, "id", "noGrupoPerfil"));
		model.addAttribute("grupoPerfisJSON", new ArrayList<>());
		return MappedViews.USUARIO_CONSULTA.getPath();
	}
	
	@GetMapping( "/s" )
	public String lista(Usuario usuario, Model model ) {
		List<Usuario> usuarios = usuarioService.findByPrestadorasId(1630l);

		model.addAttribute( "usuarios", usuarios);
		return MappedViews.USUARIO_CONSULTA.getPath();
	}
	
//	@GetMapping( "/s" )
//	public String lista( GrupoPerfil grupoPerfil, Model model ) {
//		grupoPerfil.setPrestadora( new Prestadora( 1630l ) );
//		List<GrupoPerfil> lista = grupoPerfilService.findByPrestadoraAndNome( grupoPerfil );
//
//		model.addAttribute( "grupoPerfisJSON", new GrupoPerfilConsultaVO().getVOToJson( lista ) );
//		return MappedViews.GRUPO_PERFIL_CONSULTA.getPath();
//	}
	
	@ModelAttribute("grupoPerfis")
	private List<GrupoPerfil> getGrupoPerfilList() {
		return grupoPerfilService.findByPrestadora(1630l);
	}
	

}
