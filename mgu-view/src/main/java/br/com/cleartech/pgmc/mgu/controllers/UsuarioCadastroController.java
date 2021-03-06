package br.com.cleartech.pgmc.mgu.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;

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

import br.com.cleartech.pgmc.mgu.entities.GrupoPerfil;
import br.com.cleartech.pgmc.mgu.entities.NivelEscalonamento;
import br.com.cleartech.pgmc.mgu.entities.Prestadora;
import br.com.cleartech.pgmc.mgu.entities.Usuario;
import br.com.cleartech.pgmc.mgu.services.GrupoPerfilService;
import br.com.cleartech.pgmc.mgu.services.LdapService;
import br.com.cleartech.pgmc.mgu.services.NivelEscalonamentoService;
import br.com.cleartech.pgmc.mgu.services.PrestadoraService;
import br.com.cleartech.pgmc.mgu.services.UsuarioService;
import br.com.cleartech.pgmc.mgu.utils.StringUtils;
import br.com.cleartech.pgmc.mgu.view.dtos.UsuarioCadastroDTO;
import br.com.cleartech.pgmc.mgu.view.utils.MappedViews;
import br.com.cleartech.pgmc.mgu.view.utils.MguUtils;
import br.com.cleartech.pgmc.mgu.view.utils.ValidaCPF;

@Controller
@RequestMapping( "/usuarioCadastro" )
public class UsuarioCadastroController {

	private static final Logger LOGGER = LoggerFactory.getLogger( UsuarioCadastroController.class );

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private GrupoPerfilService grupoPerfilService;

	@Autowired
	private NivelEscalonamentoService nivelEscalonamentoService;

	@Autowired
	private LdapService ldapService;

	@Autowired
	private PrestadoraService prestadoraService;

	private Usuario usuario;

	@GetMapping( { "", "/", "/salvar" } )
	public String init( Model model ) {
		List<GrupoPerfil> listaGrupoPerfilTotal = getGrupoPerfilList();
		model.addAttribute( "grupoPerfisSourceJSON", MguUtils.getVO2JSON( listaGrupoPerfilTotal, "id", "noGrupoPerfil" ) );
		model.addAttribute( "grupoPerfisTargetJSON", MguUtils.getJSON( new ArrayList<GrupoPerfil>() ) );
		model.addAttribute( "usuario", new UsuarioCadastroDTO() );

		return MappedViews.USUARIO_CADASTRO.getPath();
	}

	@PostMapping( "/salvar" )
	public String salvar( @Validated @ModelAttribute( "usuario" ) UsuarioCadastroDTO usuarioDto, BindingResult bindingResult, Model model ) {

		MguUtils.trim( usuarioDto );

		List<GrupoPerfil> groupSelecteds = new ArrayList<GrupoPerfil>();

		if ( usuarioDto.getGrupoPerfisIdList().isEmpty() ) {
			bindingResult.addError( new FieldError( "usuario", "grupoPerfisIdList", usuarioDto.getGrupoPerfisIdList(), false, null, null, "Grupo de Perfil(s) é obrigatório." ) );
		} else {
			// carregar grupos
			groupSelecteds = MguUtils.idListToGrupoPerfilList( usuarioDto.getGrupoPerfisIdList() );
			usuarioDto.setGrupoPerfis( groupSelecteds );
		}

		if ( StringUtils.isEmpty( usuarioDto.getNuCpf() ) ) {
			bindingResult.addError( new FieldError( "usuario", "nuCpf", usuarioDto.getNuCpf(), false, null, null, "CPF é obrigatório." ) );
		} else if ( !ValidaCPF.isCPF( usuarioDto.getNuCpf() ) ) {
			bindingResult.addError( new FieldError( "usuario", "nuCpf", usuarioDto.getNuCpf(), false, null, null, "CPF informado é inválido." ) );
		}

		if ( !StringUtils.isEmpty( usuarioDto.getDcUsername() ) ) {
			String msgUsuarioExiste = "Usuário de acesso informado já existe e não pode ser utilizado. Por favor, informe outro usuário de acesso.";

			if ( usuarioService.existsByUsernameIgnoreCase( usuarioDto.getDcUsername() ) ) {
				bindingResult.addError( new FieldError( "usuario", "dcUsername", usuarioDto.getDcUsername(), false, null, null, msgUsuarioExiste ) );
			} else if ( ldapService.existeUsuario( usuarioDto.getDcUsername() ) ) {
				bindingResult.addError( new FieldError( "usuario", "dcUsername", usuarioDto.getDcUsername(), false, null, null, "LDAP ERRO: " + msgUsuarioExiste ) );
			}

			if ( !usuarioDto.getDcUsername().replaceAll( "[\\w._-]", "" ).isEmpty() ) {
				bindingResult.addError( new FieldError( "usuario", "dcUsername", usuarioDto.getDcUsername(), false, null, null, "Usuário de Acesso deve conter apenas letras, números, ponto(.), underline(_) e traço(-)." ) );
			}
		} else {
			bindingResult.addError( new FieldError( "usuario", "dcUsername", usuarioDto.getDcUsername(), false, null, null, "Usuário de Acesso é obrigatório." ) );
		}

		Prestadora prestadoraLogada = prestadoraService.findById( MguUtils.getUsuarioLogado().getIdPrestadora() );
		if ( !bindingResult.hasErrors() ) {
			String mensagem = "Usuário salvo com sucesso!";
			try {
				LOGGER.info( "salvando: " + usuarioDto.toString() );
				// ao salvar um usuario ele deve se associar a todas as
				// prestadoras do grupo
				usuarioDto.setPrestadoraList( prestadoraService.buscaPrestadorasDoGrupo( prestadoraLogada.getGrupoPrestadora().getId() ) );

				usuarioService.salvar( usuarioDto.getUsuario(), false, prestadoraLogada );
				return "redirect:/usuarioConsulta/s" + MguUtils.adjustURL( null, String.format( MappedViews.SUCESSO_PARAMETRO.getPath(), mensagem ) );
			} catch ( MessagingException e ) {
				return "redirect:/usuarioConsulta/s" + MguUtils.adjustURL( null, String.format( MappedViews.SUCESSO_COM_ALERTA_EMAIL_PARAMETRO.getPath(), e.getMessage(), mensagem ) );
			} catch ( Exception e ) {
				bindingResult.addError( new ObjectError( "usuario", e.getMessage() ) );
			}
		}

		List<GrupoPerfil> listaGrupoPerfilTotal = getGrupoPerfilList();
		listaGrupoPerfilTotal.removeAll( groupSelecteds );
		model.addAttribute( "grupoPerfisSourceJSON", MguUtils.getVO2JSON( listaGrupoPerfilTotal, "id", "noGrupoPerfil" ) );

		groupSelecteds = grupoPerfilService.loadAllById( groupSelecteds );
		model.addAttribute( "grupoPerfisTargetJSON", MguUtils.getVO2JSON( groupSelecteds, "id", "noGrupoPerfil" ) );

		return MappedViews.USUARIO_CADASTRO.getPath();
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