package br.com.cleartech.pgmc.mgu.configs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.HibernateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.google.common.base.Strings;

import br.com.cleartech.pgmc.mgu.entities.Delegado;
import br.com.cleartech.pgmc.mgu.entities.Usuario;
import br.com.cleartech.pgmc.mgu.enums.BloqueioUsuario;
import br.com.cleartech.pgmc.mgu.exceptions.LdapException;
import br.com.cleartech.pgmc.mgu.exceptions.MguViewAuthenticationException;
import br.com.cleartech.pgmc.mgu.services.DelegadoService;
import br.com.cleartech.pgmc.mgu.services.LdapService;
import br.com.cleartech.pgmc.mgu.services.UsuarioService;
import br.com.cleartech.pgmc.mgu.utils.GeradorSenha;

@Component
public class MguAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

	private static final Logger logger = LoggerFactory.getLogger( MguAuthenticationProvider.class );

	@Autowired
	private LdapService ldapService;

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private DelegadoService delegadoService;

	@Override
	protected MguUserDetails retrieveUser( String username, UsernamePasswordAuthenticationToken authentication ) throws AuthenticationException {
		logger.info( "Tentativa de Login com {}", username );
		if ( Strings.isNullOrEmpty( authentication.getName() ) || Strings.isNullOrEmpty( authentication.getCredentials().toString() ) ) {
			throw new MguViewAuthenticationException( "Acesso Negado" );
		}
		
		String passwEncoded = GeradorSenha.md5( authentication.getCredentials().toString() );
		try {
			boolean usuarioLdapValido = ldapService.existeUsuarioMaster( authentication.getName(), passwEncoded );
			if ( !usuarioLdapValido ) {
				throw new MguViewAuthenticationException( "Usuário ou senha inválidos" );
			}
		} catch ( LdapException e ) {
			e.printStackTrace();
			throw new MguViewAuthenticationException( "LDAP ERRO: Problemas na integração com o Ldap" );
		}

		try {
			Usuario usuario = usuarioService.findByUsername( username );

			boolean isDelegado = false;
			if ( usuario.getFlMaster() == false ) {
				Delegado delegado = delegadoService.findByUsuarioComumDcUsername( usuario.getDcUsername() );
				if ( delegado != null ) {
					usuario.getPrestadoras().clear();
					usuario.getPrestadoras().add( delegado.getPrestadora() );
					isDelegado = true;
				}
			}

			if ( !usuario.getFlAprovado() || ( !usuario.getFlMaster() && !isDelegado ) || usuario.getFlBloqueio().equals( BloqueioUsuario.BLOQUEADO_ADM ) ) {
				throw new MguViewAuthenticationException( "Acesso Negado, usuário sem permissão de acesso" );
			}

			if ( usuario.getFlBloqueio().equals( BloqueioUsuario.BLOQUEADO_PRIMEIROACESSO ) || usuario.getFlBloqueio().equals( BloqueioUsuario.BLOQUEADO_EXPIRADA ) ) {
				throw new MguViewAuthenticationException( "Senha expirada ou temporária, a mesma deve ser trocada para ter acesso ao sistema." );
			}

			if ( usuario.getFlBloqueio().equals( BloqueioUsuario.BLOQUEADO_TENTATIVA ) ) {
				throw new MguViewAuthenticationException( "Senha bloqueda por tentativas de acesso excedidas" );
			}

			MguUserDetails usuarioDaSessao = new MguUserDetails();
			usuarioDaSessao.setNmUsuario( usuario.getNmUsuario() );
			usuarioDaSessao.setDcUsername( usuario.getDcUsername() );
			if ( usuario.getPrestadoras().isEmpty() ) {
				throw new MguViewAuthenticationException( "Usuário não possui prestadora associada" );
			}
			usuarioDaSessao.setPrestadora( usuario.getPrestadoras().get( 0 ).getNoPrestadora() );
			usuarioDaSessao.setIdPrestadora( usuario.getPrestadoras().get( 0 ).getId() );
			usuarioDaSessao.getAuthorities().add( new SimpleGrantedAuthority( "USUARIO_MASTER" ) );
			if ( usuarioDaSessao.getIdPrestadora() == 1 /* ESOA */ ) {
				usuarioDaSessao.getAuthorities().add( new SimpleGrantedAuthority( "USUARIO_MASTER_ESOA" ) );
			}
			usuarioDaSessao.setGrupoPrestadora( usuario.getPrestadoras().get( 0 ).getGrupoPrestadora().getNoGrupoPrestadora() );
			usuarioDaSessao.setIdGrupoPrestadora( usuario.getPrestadoras().get( 0 ).getGrupoPrestadora().getId() );

			logger.info( "Login realizado com sucesso" );
			return usuarioDaSessao;
		} catch ( HibernateException e ) {
			e.printStackTrace();
			throw new MguViewAuthenticationException( "Acesso Negado" );
		}

	}

	@Override
	protected void additionalAuthenticationChecks( UserDetails userDetails, UsernamePasswordAuthenticationToken authentication ) throws AuthenticationException {

	}

	public class MguUserDetails implements UserDetails {

		private static final long serialVersionUID = 3500947625072254656L;

		private String nmUsuario;
		private String dcUsername;
		private String prestadora;
		private Long idPrestadora;
		private String grupoPrestadora;
		private Long idGrupoPrestadora;

		public MguUserDetails() {}

		private List<SimpleGrantedAuthority> authorities;

		@Override
		public Collection<SimpleGrantedAuthority> getAuthorities() {
			if ( authorities == null )
				authorities = new ArrayList<SimpleGrantedAuthority>();

			return authorities;
		}

		public void setAuthorities( List<SimpleGrantedAuthority> authorities ) {
			this.authorities = authorities;
		}

		@Override
		public String getPassword() {
			return null;
		}

		@Override
		public String getUsername() {
			return getDcUsername();
		}

		@Override
		public boolean isAccountNonExpired() {
			return true;
		}

		@Override
		public boolean isAccountNonLocked() {
			return true;
		}

		@Override
		public boolean isCredentialsNonExpired() {
			return true;
		}

		@Override
		public boolean isEnabled() {
			return true;
		}

		public String getNmUsuario() {
			return nmUsuario;
		}

		public void setNmUsuario( String nmUsuario ) {
			this.nmUsuario = nmUsuario;
		}

		public String getDcUsername() {
			return dcUsername;
		}

		public void setDcUsername( String dcUsername ) {
			this.dcUsername = dcUsername;
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

	}

}
