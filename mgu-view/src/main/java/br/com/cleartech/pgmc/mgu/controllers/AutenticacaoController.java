package br.com.cleartech.pgmc.mgu.controllers;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import br.com.cleartech.pgmc.mgu.entities.Prestadora;
import br.com.cleartech.pgmc.mgu.entities.Usuario;
import br.com.cleartech.pgmc.mgu.services.PrestadoraService;
import br.com.cleartech.pgmc.mgu.services.UsuarioService;

@SessionScope
@Component
public class AutenticacaoController implements Serializable {

	private static final long serialVersionUID = -2145378914330158661L;

	private static final Logger LOGGER = LoggerFactory.getLogger( AutenticacaoController.class );

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private PrestadoraService prestadoraService;

	private String nomeUsuario;

	private String dcUsername;

	private String prestadora;

	private Long idPrestadora;

	private String grupoPrestadora;

	private Long idGrupoPrestadora;

	private Usuario usuario;

	public AutenticacaoController() {
		LOGGER.info( "construtor-> " + AutenticacaoController.class.getName() );
		if ( usuario == null ) {
			String dcUsernameLogado = SecurityContextHolder.getContext().getAuthentication().getName();
			usuario = usuarioService.findByUsername( dcUsernameLogado );
			nomeUsuario = usuario.getNmUsuario();
			dcUsernameLogado = usuario.getDcUsername();
			Prestadora empresaUsuario = prestadoraService.prestadoraPorUsername( dcUsernameLogado );
			prestadora = empresaUsuario.getNoPrestadora();
			idPrestadora = empresaUsuario.getId();
			grupoPrestadora = empresaUsuario.getGrupoPrestadora().getNoGrupoPrestadora();
			idGrupoPrestadora = empresaUsuario.getGrupoPrestadora().getId();
		}
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario( String nomeUsuario ) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getDcUsername() {
		return dcUsername;
	}

	public void setDcUsername( String dcUsername ) {
		this.dcUsername = dcUsername;
	}

	public String getEmpresa() {
		return prestadora;
	}

	public void setEmpresa( String empresa ) {
		this.prestadora = empresa;
	}

	public String getPrestadora() {
		return prestadora;
	}

	public void setPrestadora( String prestadora ) {
		this.prestadora = prestadora;
	}

	public Long getIdPrestadora() {
		return idPrestadora;
	}

	public void setIdPrestadora( Long idPrestadora ) {
		this.idPrestadora = idPrestadora;
	}

	public String getGrupoPrestadora() {
		return grupoPrestadora;
	}

	public void setGrupoPrestadora( String grupoPrestadora ) {
		this.grupoPrestadora = grupoPrestadora;
	}

	public Long getIdGrupoPrestadora() {
		return idGrupoPrestadora;
	}

	public void setIdGrupoPrestadora( Long idGrupoPrestadora ) {
		this.idGrupoPrestadora = idGrupoPrestadora;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario( Usuario usuario ) {
		this.usuario = usuario;
	}

}