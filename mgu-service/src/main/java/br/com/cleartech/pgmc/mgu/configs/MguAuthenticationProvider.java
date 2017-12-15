package br.com.cleartech.pgmc.mgu.configs;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import br.com.cleartech.pgmc.mgu.entities.Delegado;
import br.com.cleartech.pgmc.mgu.entities.Usuario;
import br.com.cleartech.pgmc.mgu.enums.BloqueioUsuario;
import br.com.cleartech.pgmc.mgu.exceptions.LdapException;
import br.com.cleartech.pgmc.mgu.exceptions.MguViewAuthenticationException;
import br.com.cleartech.pgmc.mgu.services.DelegadoService;
import br.com.cleartech.pgmc.mgu.services.LdapService;
import br.com.cleartech.pgmc.mgu.services.UsuarioService;

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
		logger.info( "MguAuthenticationProvider.retrieveUser()" );
		logger.info( "Tentativa de Login com {}", username );
		String passwEncoded = new Md5PasswordEncoder().encodePassword( authentication.getCredentials().toString(), null );
		try {
			boolean usuarioLdapValido = ldapService.existeUsuario( authentication.getName(), passwEncoded );
			if ( !usuarioLdapValido ) {
				throw new MguViewAuthenticationException( "Usuário ou senha inválidos" );
			}
		} catch ( LdapException e ) {
			e.printStackTrace();
			throw new MguViewAuthenticationException( "LDAP ERRO: Problemas na integração com o Ldap" );
		}

		try {
			Usuario usuario = usuarioService.findByUsername( username );

			if ( usuario.getFlMaster().equals( false ) ) {
				Delegado delegado = delegadoService.findByUsuarioComumDcUsername( usuario.getDcUsername() );
				usuario.getPrestadoras().clear();
				usuario.getPrestadoras().add( delegado.getPrestadora() );
			}

			if ( usuario.getFlAprovado().equals( false ) ) {
				throw new MguViewAuthenticationException( "Acesso Negado" );
			}

			if ( usuario.getFlBloqueio().equals( BloqueioUsuario.BLOQUEADO_PRIMEIROACESSO ) ) {
				throw new MguViewAuthenticationException( "Usuario deve trocar a senha no primeiro acesso" );
			}

			if ( usuario.getFlBloqueio().equals( BloqueioUsuario.BLOQUEADO_EXPIRADA ) ) {
				throw new MguViewAuthenticationException( "Senha expirada" );
			}

			if ( usuario.getFlBloqueio().equals( BloqueioUsuario.BLOQUEADO_TENTATIVA ) ) {
				throw new MguViewAuthenticationException( "Senha bloqueda por tentativas de acesso excedidas" );
			}

			MguUserDetails usuarioDaSessao = new MguUserDetails();
			usuarioDaSessao.setNmUsuario( usuario.getNmUsuario() );
			usuarioDaSessao.setDcUsername( usuario.getDcUsername() );
			usuarioDaSessao.setPrestadora( usuario.getPrestadoras().get( 0 ).getNoPrestadora() );
			usuarioDaSessao.setIdPrestadora( usuario.getPrestadoras().get( 0 ).getId() );
			usuarioDaSessao.setGrupoPrestadora( usuario.getPrestadoras().get( 0 ).getGrupoPrestadora().getNoGrupoPrestadora() );
			usuarioDaSessao.setIdGrupoPrestadora( usuario.getPrestadoras().get( 0 ).getGrupoPrestadora().getId() );

			logger.info( "Login realizado com sucesso" );
			return usuarioDaSessao;
		} catch ( Exception e ) {
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
		public Collection<? extends GrantedAuthority> getAuthorities() {
			if ( authorities == null ) {
				authorities = Arrays.asList( new SimpleGrantedAuthority( "ADMIN" ) );
			}
			return authorities;
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
