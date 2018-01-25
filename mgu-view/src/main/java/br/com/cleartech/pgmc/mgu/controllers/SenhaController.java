package br.com.cleartech.pgmc.mgu.controllers;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.MailSendException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.cleartech.pgmc.mgu.entities.Usuario;
import br.com.cleartech.pgmc.mgu.enums.BloqueioUsuario;
import br.com.cleartech.pgmc.mgu.exceptions.LdapException;
import br.com.cleartech.pgmc.mgu.services.LdapService;
import br.com.cleartech.pgmc.mgu.services.UsuarioService;
import br.com.cleartech.pgmc.mgu.utils.GeradorSenha;
import br.com.cleartech.pgmc.mgu.view.dtos.UsuarioCadastroDTO;
import br.com.cleartech.pgmc.mgu.view.utils.MappedViews;
import br.com.cleartech.pgmc.mgu.view.utils.RecaptchaService;

@Controller
@RequestMapping( "/senha" )
@PropertySource( "classpath:version.properties" )
public class SenhaController {

	@Autowired
	private RecaptchaService recaptchaService;

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private LdapService ldapService;

	@Value( "${version.label}" )
	private String versionLabel;

	private UsuarioCadastroDTO usuario;

	private static final Logger logger = LoggerFactory.getLogger( SenhaController.class );

	@GetMapping( { "/problemaSenha", "/recuperarSenha" } )
	public String login( Model model, HttpServletRequest request, @ModelAttribute( "username" ) String dcUsername ) {
		request.getSession().setAttribute( "versionLabel", versionLabel );
		UsuarioCadastroDTO usuario = new UsuarioCadastroDTO();
		usuario.setDcUsername( dcUsername );
		model.addAttribute( "usuario", usuario );
		return MappedViews.PROBLEMA_SENHA.getPath();
	}

	@PostMapping( "/recuperarSenha" )
	public String recuperarSenha( @RequestParam( "g-recaptcha-response" ) String recaptchaResponse, UsuarioCadastroDTO usuario, Model model, HttpServletRequest request ) {
		logger.info( SenhaController.class + "recuperarSenha: " + usuario.getDcUsername() );
		request.getSession().setAttribute( "versionLabel", versionLabel );
		model.addAttribute( "usuario", usuario );

		String ip = request.getRemoteAddr();
		String captchaVerifyMessage = recaptchaService.verifyRecaptcha( ip, recaptchaResponse );

		if ( StringUtils.isNotEmpty( captchaVerifyMessage ) ) {
			model.addAttribute( "msgError", captchaVerifyMessage );
			return MappedViews.PROBLEMA_SENHA.getPath();
		}

		if ( br.com.cleartech.pgmc.mgu.utils.StringUtils.isEmpty( usuario.getDcUsername() ) ) {
			model.addAttribute( "msgError", "Campo Usuário é obrigatório!" );
			return MappedViews.PROBLEMA_SENHA.getPath();
		}

		Usuario usuarioDB = usuarioService.findByUsername( usuario.getDcUsername() );
		if ( usuarioDB == null ) {
			model.addAttribute( "msgError", "Não foi possivel localizar o usuário informado!" );
			return MappedViews.PROBLEMA_SENHA.getPath();
		}

		if ( usuarioDB.getFlBloqueio().equals( BloqueioUsuario.BLOQUEADO_ADM ) ) {
			model.addAttribute( "msgError", "Usuario bloqueado pelo administrador, não pode resetar a senha." );
			return MappedViews.PROBLEMA_SENHA.getPath();
		}
		try {
			usuarioService.resetar( usuarioDB.getId(), "tela_resetar", false );
		} catch ( LdapException e ) {
			model.addAttribute( "msgError", "Ocorreu um erro ao consultar o usuario, entre em contato com o administrador do sistema." );
			return MappedViews.PROBLEMA_SENHA.getPath();
		} catch ( MessagingException e ) {
			model.addAttribute( "msgError", "Não foi possível enviar a nova senha, entre em contato com o administrador do sistema." );
			return MappedViews.PROBLEMA_SENHA.getPath();
		} catch ( MailSendException e ) {
			model.addAttribute( "msgError", "Não foi possível enviar a nova senha, entre em contato com o administrador do sistema." );
			return MappedViews.PROBLEMA_SENHA.getPath();
		} catch ( Exception e ) {
			e.printStackTrace();
			model.addAttribute( "msgError", "Não foi possível validar o captcha." );
			return MappedViews.PROBLEMA_SENHA.getPath();
		}

		logger.info( "Reset de senha realizado para:" + usuarioDB.getDcUsername() );
		model.addAttribute( "msgSucesso", "Reset da senha realizado com sucesso!" );
		return MappedViews.PROBLEMA_SENHA.getPath();

	}

	@GetMapping( { "/trocarSenha", "/salvarNovaSenha" } )
	public String trocarSenha( Model model, HttpServletRequest request, @ModelAttribute( "username" ) String dcUsername ) {
		request.getSession().setAttribute( "versionLabel", versionLabel );
		UsuarioCadastroDTO usuario = new UsuarioCadastroDTO();
		usuario.setDcUsername( dcUsername );
		model.addAttribute( "usuario", usuario );
		return MappedViews.TROCAR_SENHA.getPath();
	}

	@PostMapping( "/salvarNovaSenha" )
	public String salvarNovaSenha( @RequestParam( "g-recaptcha-response" ) String recaptchaResponse, UsuarioCadastroDTO usuario, Model model, HttpServletRequest request ) {
		logger.info( SenhaController.class + "salvarNovaSenha: " + usuario.getDcUsername() );
		request.getSession().setAttribute( "versionLabel", versionLabel );
		model.addAttribute( "usuario", usuario );

		String ip = request.getRemoteAddr();
		String captchaVerifyMessage = recaptchaService.verifyRecaptcha( ip, recaptchaResponse );

		if ( StringUtils.isNotEmpty( captchaVerifyMessage ) ) {
			model.addAttribute( "msgError", captchaVerifyMessage );
			return MappedViews.TROCAR_SENHA.getPath();
		}

		if ( br.com.cleartech.pgmc.mgu.utils.StringUtils.hasAnyEmpty( usuario.getDcUsername(), usuario.getSenhaAtual(), usuario.getSenhaNova(), usuario.getConfirmarSenhaNova() ) ) {
			model.addAttribute( "msgError", "Todos as informações devem ser preenchidas." );
			return MappedViews.TROCAR_SENHA.getPath();
		}

		if ( !usuario.getSenhaNova().equals( usuario.getConfirmarSenhaNova() ) ) {
			model.addAttribute( "msgError", "A nova senha deve ser igual a senha informada no campo \"Confirma Nova Senha\"" );
			return MappedViews.TROCAR_SENHA.getPath();
		}

		if ( usuario.getSenhaAtual().equals( usuario.getSenhaNova() ) ) {
			model.addAttribute( "msgError", "A nova senha deve ser diferente da senha atual" );
			return MappedViews.TROCAR_SENHA.getPath();
		}

		if ( !ldapService.existeUsuario( usuario.getDcUsername(), GeradorSenha.md5( usuario.getSenhaAtual() ) ) ) {
			model.addAttribute( "msgError", "Usuario ou senha invalido!" );
			return MappedViews.TROCAR_SENHA.getPath();
		}

		Usuario usuarioDB = usuarioService.findByUsername( usuario.getDcUsername() );
		if ( usuarioDB == null ) {
			model.addAttribute( "msgError", "Não foi possivel localizar o usuário informado!" );
			return MappedViews.TROCAR_SENHA.getPath();
		}

		if ( usuarioDB.getFlBloqueio().equals( BloqueioUsuario.BLOQUEADO_ADM ) ) {
			model.addAttribute( "msgError", "Usuário bloqueado pelo administrador do sistema, entre em contato com o mesmo" );
			return MappedViews.TROCAR_SENHA.getPath();
		}
		try {
			usuarioService.alterarSenha( usuarioDB.getId(), "tela_resetar", usuario.getSenhaNova() );
		} catch ( LdapException e ) {
			model.addAttribute( "msgError", "Ocorreu um erro ao consultar o usuario, entre em contato com o administrador do sistema." );
			return MappedViews.TROCAR_SENHA.getPath();
		} catch ( MessagingException e ) {
			model.addAttribute( "msgError", "Não foi possível enviar a nova senha, entre em contato com o administrador do sistema." );
			return MappedViews.TROCAR_SENHA.getPath();
		} catch ( MailSendException e ) {
			model.addAttribute( "msgError", "Não foi possível enviar a nova senha, entre em contato com o administrador do sistema." );
			return MappedViews.TROCAR_SENHA.getPath();
		} catch ( Exception e ) {
			e.printStackTrace();
			model.addAttribute( "msgError", "Não foi possível validar o captcha." );
			return MappedViews.TROCAR_SENHA.getPath();
		}

		logger.info( "senha alterada para:" + usuarioDB.getDcUsername() );
		model.addAttribute( "msgSucesso", "Senha alterada com sucesso!" );
		return MappedViews.PROBLEMA_SENHA.getPath();

	}

	public UsuarioCadastroDTO getUsuario() {
		return usuario;
	}

	public void setUsuario( UsuarioCadastroDTO usuario ) {
		this.usuario = usuario;
	}

}