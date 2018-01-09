package br.com.cleartech.pgmc.mgu.controllers;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.cleartech.pgmc.mgu.entities.Usuario;
import br.com.cleartech.pgmc.mgu.view.utils.MappedViews;
import br.com.cleartech.pgmc.mgu.view.utils.RecaptchaService;

@Controller
@RequestMapping( "/senha" )
@PropertySource( "classpath:version.properties" )
public class SenhaController {

	@Autowired
	RecaptchaService recaptchaService;

	@Value( "${version.label}" )
	private String versionLabel;

	private static final Logger logger = LoggerFactory.getLogger( SenhaController.class );

	@GetMapping( "/problemaSenha" )
	public String login( Model model, HttpServletRequest request, @ModelAttribute( "username" ) String dcUsername ) {
		request.getSession().setAttribute( "versionLabel", versionLabel );
		model.addAttribute( "username", dcUsername);
		return MappedViews.PROBLEMA_SENHA.getPath();
	}

	@PostMapping( "/recuperarSenha" )
	public String recuperarSenha( @RequestParam( name = "g-recaptcha-response" ) String recaptchaResponse, Model model, HttpServletRequest request ) {
		request.getSession().setAttribute( "versionLabel", versionLabel );

		String ip = request.getRemoteAddr();
		String captchaVerifyMessage = recaptchaService.verifyRecaptcha( ip, recaptchaResponse );

		if ( StringUtils.isNotEmpty( captchaVerifyMessage ) ) {
			model.addAttribute( "captchaError", captchaVerifyMessage );
			return MappedViews.PROBLEMA_SENHA.getPath();
		}

		return MappedViews.LOGIN.getPath();

	}

	@GetMapping( value = "/trocarSenha" )
	public String trocarSenha( Model model, @ModelAttribute( "dcUsername" ) String dcUsername ) {

		Usuario usuario = new Usuario();
		usuario.setDcUsername( dcUsername );
		model.addAttribute( "usuario", usuario );

		return "/trocarSenha";
	}

}