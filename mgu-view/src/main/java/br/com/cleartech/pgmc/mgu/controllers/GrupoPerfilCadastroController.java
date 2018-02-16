package br.com.cleartech.pgmc.mgu.controllers;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.cleartech.pgmc.mgu.entities.Perfil;
import br.com.cleartech.pgmc.mgu.services.GrupoPerfilService;
import br.com.cleartech.pgmc.mgu.services.PerfilService;
import br.com.cleartech.pgmc.mgu.services.PrestadoraService;
import br.com.cleartech.pgmc.mgu.utils.StringUtils;
import br.com.cleartech.pgmc.mgu.view.dtos.GrupoPerfilCadastroDTO;
import br.com.cleartech.pgmc.mgu.view.utils.MappedViews;
import br.com.cleartech.pgmc.mgu.view.utils.MguUtils;

@Controller
@RequestMapping( "/grupoPerfilCadastro" )
public class GrupoPerfilCadastroController {

	private static final Logger LOGGER = LoggerFactory.getLogger( GrupoPerfilCadastroController.class );

	@Autowired
	private GrupoPerfilService grupoPerfilService;

	@Autowired
	private PerfilService perfilService;

	@Autowired
	private PrestadoraService prestadoraService;

	@GetMapping( { "", "/", "/salvar" } )
	public String init( Model model ) {
		List<Perfil> listaPerfilTotal = getPerfilList();
		model.addAttribute( "grupoPerfil", new GrupoPerfilCadastroDTO() );
		model.addAttribute( "perfisSourceJSON", MguUtils.preparePerfilToJson( listaPerfilTotal ) );
		model.addAttribute( "perfisTargetJSON", MguUtils.getJSON( new ArrayList<Perfil>() ) );
		return MappedViews.GRUPO_PERFIL_CADASTRO.getPath();
	}

	@PostMapping( "/salvar" )
	public String salvar( @Validated @ModelAttribute( "grupoPerfil" ) GrupoPerfilCadastroDTO grupoPerfilDto, BindingResult bindingResult, Model model ) {

		MguUtils.trim(grupoPerfilDto);
		
		// carregar grupos
		List<Perfil> perfisSelecionados = MguUtils.idListToPerfilList( grupoPerfilDto.getPerfisIdList() );
		grupoPerfilDto.setPerfis( perfisSelecionados );

		if ( !StringUtils.isEmpty( grupoPerfilDto.getNoGrupoPerfil() ) ) {
			if ( !grupoPerfilDto.getNoGrupoPerfil().replaceAll( "[\\w._-]", "" ).isEmpty() ) {
				bindingResult.addError( new FieldError( "grupoPerfil", "noGrupoPerfil", grupoPerfilDto.getNoGrupoPerfil(), false, null, null, "Nome do Grupo de Perfil deve conter apenas letras, números, ponto(.), underline(_) e traço(-)." ) );
			}

			if ( grupoPerfilService.existsByNoGrupoPerfil( grupoPerfilDto.getNoGrupoPerfil() ) ) {
				bindingResult.addError( new FieldError( "grupoPerfil", "noGrupoPerfil", grupoPerfilDto.getNoGrupoPerfil(), false, null, null, "Nome do Grupo de Perfil informado já está cadastrado." ) );
			}

		} else {
			bindingResult.addError( new FieldError( "grupoPerfil", "noGrupoPerfil", grupoPerfilDto.getNoGrupoPerfil(), false, null, null, "Nome do Grupo de Perfil é obrigatório." ) );
		}

		if ( !bindingResult.hasErrors() ) {
			try {
				LOGGER.info( "salvando: " + grupoPerfilDto.toString() );
				grupoPerfilDto.setPrestadora( prestadoraService.prestadoraPorUsername( MguUtils.getUsuarioLogado().getDcUsername() ) );
				grupoPerfilService.salvar( grupoPerfilDto.getGrupoPerfil() );
				return "redirect:/grupoPerfilConsulta/s" + MguUtils.adjustURL( null, String.format( MappedViews.SUCESSO_PARAMETRO.getPath(), "Grupo de Perfil Cadastrado com sucesso!" ) );
			} catch ( Exception e ) {
				bindingResult.addError( new ObjectError( "grupoPerfil", e.getMessage() ) );
			}
		}

		List<Perfil> listaGrupoPerfilTotal = getPerfilList();
		listaGrupoPerfilTotal.removeAll( perfisSelecionados );
		model.addAttribute( "perfisSourceJSON", MguUtils.preparePerfilToJson( listaGrupoPerfilTotal ) );

		perfisSelecionados = perfilService.loadAllById( perfisSelecionados );
		model.addAttribute( "perfisTargetJSON", MguUtils.preparePerfilToJson( perfisSelecionados ) );

		return MappedViews.GRUPO_PERFIL_CADASTRO.getPath();
	}

	private List<Perfil> getPerfilList() {
		return perfilService.findByPrestadora( MguUtils.getUsuarioLogado().getIdPrestadora(), MguUtils.getUsuarioLogado().getDcUsername() );
	}
}