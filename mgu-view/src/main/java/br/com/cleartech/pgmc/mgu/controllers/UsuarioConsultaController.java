package br.com.cleartech.pgmc.mgu.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest; 

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
@RequestMapping( "/usuarioConsulta" )
public class UsuarioConsultaController {

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private GrupoPerfilService grupoPerfilService;

	@Autowired
	private NivelEscalonamentoService nivelEscalonamentoService;

	@GetMapping
	public String init( Model model, HttpServletRequest request ) {
		List<GrupoPerfil> listaGrupoPerfilTotal = getGrupoPerfilList();
		model.addAttribute( "grupoPerfisSourceJSON", MguUtils.getVO2JSON( listaGrupoPerfilTotal, "id", "noGrupoPerfil" ) );
		model.addAttribute( "grupoPerfisJSON", new ArrayList<>() );
		model.addAttribute( "usuario", new Usuario() );
		model.addAttribute( "msgAlertaEmail", request.getParameter( "msgAlertaEmail" ) );
		return MappedViews.USUARIO_CONSULTA.getPath();
	}

	@ModelAttribute( "grupoPerfis" )
	private List<GrupoPerfil> getGrupoPerfilList() {
		return grupoPerfilService.findByPrestadora( 1630l );
	}

}
