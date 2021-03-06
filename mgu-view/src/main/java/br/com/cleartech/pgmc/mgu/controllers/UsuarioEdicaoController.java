package br.com.cleartech.pgmc.mgu.controllers;

import java.util.List;

import javax.mail.MessagingException;
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

		Prestadora prestadoraLogada = prestadoraService.findById( MguUtils.getUsuarioLogado().getIdPrestadora() );
		List<GrupoPerfil> groupSelecteds = grupoPerfilService.findByUsuario( usuarioDB.getId(), prestadoraLogada.getId() );
		usuarioDto.setGrupoPerfis( groupSelecteds );

		List<GrupoPerfil> listaGrupoPerfilTotal = getGrupoPerfilList();
		listaGrupoPerfilTotal.removeAll( groupSelecteds );
		model.addAttribute( "grupoPerfisSourceJSON", MguUtils.getVO2JSON( listaGrupoPerfilTotal, "id", "noGrupoPerfil" ) );

		groupSelecteds = grupoPerfilService.loadAllById( groupSelecteds );
		model.addAttribute( "grupoPerfisTargetJSON", MguUtils.getVO2JSON( groupSelecteds, "id", "noGrupoPerfil" ) );

		if ( usuarioDto.getFlMaster() ) {
			prestadoraLogada = prestadoraService.prestadoraPorUsername( MguUtils.getUsuarioLogado().getDcUsername() );
			List<Usuario> delegados = usuarioService.findUsuarioDelegadoDisponivel( usuarioDB.getId(), prestadoraLogada.getId() );
			model.addAttribute( "listaUsuarioDelegado", delegados );
		}

		return MappedViews.USUARIO_EDICAO.getPath();
	}

	@PostMapping( "/salvar" )
	public String salvar( @Validated @ModelAttribute( "usuario" ) UsuarioCadastroDTO usuarioDto, BindingResult bindingResult, Model model ) {

		MguUtils.trim( usuarioDto );

		usuarioDB = usuarioService.find( usuarioDto.getId() );
		// carregar grupos
		List<GrupoPerfil> groupSelecteds = MguUtils.idListToGrupoPerfilList( usuarioDto.getGrupoPerfisIdList() );
		usuarioDto.setGrupoPerfis( groupSelecteds );

		Prestadora prestadoraLogada = prestadoraService.findById( MguUtils.getUsuarioLogado().getIdPrestadora() );
		if ( !bindingResult.hasErrors() ) {
			String mensagem = "Usuário alterado com sucesso!";
			try {
				LOGGER.info( "salvando: " + usuarioDto.toString() );

				if ( usuarioDB.getFlMaster() ) {
					prestadoraLogada = prestadoraService.prestadoraPorUsername( MguUtils.getUsuarioLogado().getDcUsername() );
					usuarioDto.setPrestadora( prestadoraLogada );
				}

				usuarioService.salvarEditar( usuarioDto.getUsuario(), usuarioDB, prestadoraLogada );

				if ( usuarioDto.getUrlConsulta() != null ) {
					return "redirect:" + MguUtils.adjustURL( usuarioDto.getUrlConsulta(), String.format( MappedViews.SUCESSO_PARAMETRO.getPath(), mensagem ) );
				} else {
					return "redirect:/usuarioConsulta" + MguUtils.adjustURL( null, String.format( MappedViews.SUCESSO_PARAMETRO.getPath(), mensagem ) );
				}
			} catch ( MessagingException e ) {
				return "redirect:/usuarioConsulta" + MguUtils.adjustURL( null, String.format( MappedViews.SUCESSO_COM_ALERTA_EMAIL_PARAMETRO.getPath(), e.getMessage(), mensagem ) );
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