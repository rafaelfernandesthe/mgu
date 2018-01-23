package br.com.cleartech.pgmc.mgu.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.cleartech.pgmc.mgu.entities.GrupoPerfil;
import br.com.cleartech.pgmc.mgu.entities.Perfil;
import br.com.cleartech.pgmc.mgu.services.GrupoPerfilService;
import br.com.cleartech.pgmc.mgu.services.PerfilService;
import br.com.cleartech.pgmc.mgu.view.dtos.GrupoPerfilCadastroDTO;
import br.com.cleartech.pgmc.mgu.view.utils.MappedViews;
import br.com.cleartech.pgmc.mgu.view.utils.MguUtils;

@Controller
@RequestMapping( "/grupoPerfilEdicao" )
public class GrupoPerfilEdicaoController {

	private static final Logger LOGGER = LoggerFactory.getLogger( GrupoPerfilEdicaoController.class );

	@Autowired
	private GrupoPerfilService grupoPerfilService;

	@Autowired
	private PerfilService perfilService;

	@GetMapping( "/{idGrupoPerfil}" )
	public String init( Model model, @PathVariable Long idGrupoPerfil, HttpServletRequest request ) {
		GrupoPerfil grupoPerfilDB = grupoPerfilService.find( idGrupoPerfil );

		GrupoPerfilCadastroDTO grupoPerfilDto = new GrupoPerfilCadastroDTO( grupoPerfilDB );
		String lastPage = request.getHeader( "Referer" );
		if ( lastPage != null ) {
			grupoPerfilDto.setUrlConsulta( lastPage );
		}
		model.addAttribute( "grupoPerfil", grupoPerfilDto );

		List<Perfil> perfisSelecionados = perfilService.findByGrupoPerfil( grupoPerfilDto.getId() );
		grupoPerfilDto.setPerfis( perfisSelecionados );

		List<Perfil> listaPerfilTotal = getPerfilList();
		listaPerfilTotal.removeAll( perfisSelecionados );
		model.addAttribute( "perfisSourceJSON", MguUtils.preparePerfilToJson( listaPerfilTotal ) );

		model.addAttribute( "perfisTargetJSON", MguUtils.preparePerfilToJson( perfisSelecionados ) );

		return MappedViews.GRUPO_PERFIL_EDICAO.getPath();
	}

	@PostMapping( "/salvar" )
	public String salvar( @Validated @ModelAttribute( "grupoPerfil" ) GrupoPerfilCadastroDTO grupoPerfilDto, BindingResult bindingResult, Model model ) {

		GrupoPerfil grupoPerfilDB = grupoPerfilService.find( grupoPerfilDto.getId() );

		// carregar perfis
		List<Perfil> perfisSelecionados = MguUtils.idListToPerfilList( grupoPerfilDto.getPerfisIdList() );
		grupoPerfilDto.setPerfis( perfisSelecionados );

		if ( !bindingResult.hasErrors() ) {
			try {
				String mensagem = "Grupo de Perfil Alterado com sucesso!";
				LOGGER.info( "salvando: " + grupoPerfilDto.toString() );
				grupoPerfilService.salvarEditar( grupoPerfilDto.getGrupoPerfil(), grupoPerfilDB );
				if ( grupoPerfilDto.getUrlConsulta() != null ) {
					return "redirect:" + grupoPerfilDto.getUrlConsulta() + String.format( MappedViews.SUCESSO_PARAMETRO_COMPEMENTO.getPath(), mensagem );
				} else {
					return "redirect:/grupoPerfilConsulta" + String.format( MappedViews.SUCESSO_PARAMETRO_NOVO.getPath(), mensagem );
				}
			} catch ( Exception e ) {
				bindingResult.addError( new ObjectError( "grupoPerfil", e.getMessage() ) );
			}
		}

		List<Perfil> listaGrupoPerfilTotal = getPerfilList();
		listaGrupoPerfilTotal.removeAll( perfisSelecionados );
		model.addAttribute( "perfisSourceJSON", MguUtils.preparePerfilToJson( listaGrupoPerfilTotal ) );

		perfisSelecionados = perfilService.loadAllById( perfisSelecionados );
		model.addAttribute( "perfisTargetJSON", MguUtils.preparePerfilToJson( perfisSelecionados ) );

		grupoPerfilDto.setNoGrupoPerfil( grupoPerfilDB.getNoGrupoPerfil() );

		return MappedViews.GRUPO_PERFIL_EDICAO.getPath();
	}

	private List<Perfil> getPerfilList() {
		return perfilService.findByPrestadora( MguUtils.getUsuarioLogado().getIdPrestadora(), MguUtils.getUsuarioLogado().getDcUsername() );
	}
}