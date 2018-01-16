package br.com.cleartech.pgmc.mgu.controllers;

import java.util.List;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import org.hibernate.validator.internal.constraintvalidators.hv.br.CPFValidator;
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
import br.com.cleartech.pgmc.mgu.entities.NivelEscalonamento;
import br.com.cleartech.pgmc.mgu.entities.Prestadora;
import br.com.cleartech.pgmc.mgu.entities.Usuario;
import br.com.cleartech.pgmc.mgu.services.GrupoPerfilService;
import br.com.cleartech.pgmc.mgu.services.NivelEscalonamentoService;
import br.com.cleartech.pgmc.mgu.services.PrestadoraService;
import br.com.cleartech.pgmc.mgu.services.UsuarioService;
import br.com.cleartech.pgmc.mgu.view.dtos.UsuarioCadastroDTO;
import br.com.cleartech.pgmc.mgu.view.utils.MappedViews;
import br.com.cleartech.pgmc.mgu.view.utils.MguUtils;

@Controller
@RequestMapping( "/usuarioEdicao" )
public class UsuarioEdicaoController {

	private static final Logger LOGGER = LoggerFactory.getLogger( UsuarioEdicaoController.class );

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private GrupoPerfilService grupoPerfilService;

	@Autowired
	private NivelEscalonamentoService nivelEscalonamentoService;

	@Autowired
	private PrestadoraService prestadoraService;

	private CPFValidator cpfValidator = new CPFValidator();

	private Usuario usuario;

	private Usuario usuarioDB;

	@GetMapping( "/{idUsuario}" )
	public String init( Model model, @PathVariable Long idUsuario, HttpServletRequest request ) {
		usuarioDB = usuarioService.find( idUsuario );
		UsuarioCadastroDTO usuarioDto = new UsuarioCadastroDTO( usuarioDB );

		String lastPage = request.getHeader( "Referer" );
		if ( lastPage != null ) {
			usuarioDto.setUrlConsulta( lastPage );
		}
		model.addAttribute( "usuario", usuarioDto );

		List<GrupoPerfil> groupSelecteds = grupoPerfilService.findByUsuario( usuarioDB.getId() );
		usuarioDto.setGrupoPerfis( groupSelecteds );

		List<GrupoPerfil> listaGrupoPerfilTotal = getGrupoPerfilList();
		listaGrupoPerfilTotal.removeAll( groupSelecteds );
		model.addAttribute( "grupoPerfisSourceJSON", MguUtils.getVO2JSON( listaGrupoPerfilTotal, "id", "noGrupoPerfil" ) );

		groupSelecteds = grupoPerfilService.loadAllById( groupSelecteds );
		model.addAttribute( "grupoPerfisTargetJSON", MguUtils.getVO2JSON( groupSelecteds, "id", "noGrupoPerfil" ) );

		if ( usuarioDto.getFlMaster() ) {
			Prestadora prestadora = prestadoraService.prestadoraPorUsername( MguUtils.getUsuarioLogado().getDcUsername() );
			List<Usuario> delegados = usuarioService.findUsuarioDelegadoDisponivel( usuarioDB.getId(), prestadora.getId() );
			model.addAttribute( "listaUsuarioDelegado", delegados );
		}

		cpfValidator.initialize( null );

		return MappedViews.USUARIO_EDICAO.getPath();
	}

	@PostMapping( "/salvar" )
	public String salvar( @Validated @ModelAttribute( "usuario" ) UsuarioCadastroDTO usuarioDto, BindingResult bindingResult, Model model ) {

		usuarioDB = usuarioService.find( usuarioDto.getId() );
		// carregar grupos
		List<GrupoPerfil> groupSelecteds = MguUtils.idListToGrupoPerfilList( usuarioDto.getGrupoPerfisIdList() );
		usuarioDto.setGrupoPerfis( groupSelecteds );

		if ( !bindingResult.hasErrors() ) {
			try {
				LOGGER.info( "salvando: " + usuarioDto.toString() );
				usuarioDto.setPrestadora( prestadoraService.prestadoraPorUsername( MguUtils.getUsuarioLogado().getDcUsername() ) );

				usuarioService.salvarEditar( usuarioDto.getUsuario(), usuarioDB );

				if ( usuarioDto.getUrlConsulta() != null ) {
					return "redirect:"+usuarioDto.getUrlConsulta();
				} else {
					return "redirect:/usuarioConsulta" + MappedViews.SUCESSO_PARAMETRO.getPath();
				}
			} catch ( MessagingException e ) {
				return "redirect:/usuarioConsulta" + String.format( MappedViews.SUCESSO_COM_ALERTA_EMAIL_PARAMETRO.getPath(), e.getMessage() );
			} catch ( Exception e ) {
				bindingResult.addError( new ObjectError( "usuario", e.getMessage() ) );
			}
		}

		List<GrupoPerfil> listaGrupoPerfilTotal = getGrupoPerfilList();
		listaGrupoPerfilTotal.removeAll( groupSelecteds );
		model.addAttribute( "grupoPerfisSourceJSON", MguUtils.getVO2JSON( listaGrupoPerfilTotal, "id", "noGrupoPerfil" ) );

		groupSelecteds = grupoPerfilService.loadAllById( groupSelecteds );
		model.addAttribute( "grupoPerfisTargetJSON", MguUtils.getVO2JSON( groupSelecteds, "id", "noGrupoPerfil" ) );

		usuarioDto.setDcUsername( usuarioDB.getDcUsername() );
		usuarioDto.setNuCpf( usuarioDB.getNuCpf() );
		usuarioDto.setFlMaster( usuarioDB.getFlMaster() );
		if ( usuarioDto.getFlMaster() ) {
			Prestadora prestadora = prestadoraService.prestadoraPorUsername( MguUtils.getUsuarioLogado().getDcUsername() );
			List<Usuario> delegados = usuarioService.findUsuarioDelegadoDisponivel( usuarioDB.getId(), prestadora.getId() );
			model.addAttribute( "listaUsuarioDelegado", delegados );
		}

		return MappedViews.USUARIO_EDICAO.getPath();
	}

	@ModelAttribute( "niveisEscalonamento" )
	public List<NivelEscalonamento> getTodosOsNiveisEscalonamento() {
		return nivelEscalonamentoService.findAll();
	}

	private List<GrupoPerfil> getGrupoPerfilList() {
		return grupoPerfilService.findByPrestadora( MguUtils.getUsuarioLogado().getIdPrestadora() );
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario( Usuario usuario ) {
		this.usuario = usuario;
	}

}