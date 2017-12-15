package br.com.cleartech.pgmc.mgu.services.impl;

import java.util.List;

import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.SearchControls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.filter.AndFilter;
import org.springframework.ldap.filter.EqualsFilter;
import org.springframework.ldap.query.ContainerCriteria;
import org.springframework.ldap.query.LdapQueryBuilder;
import org.springframework.ldap.query.SearchScope;
import org.springframework.stereotype.Service;

import br.com.cleartech.pgmc.mgu.enums.ParametrizacaoEnum;
import br.com.cleartech.pgmc.mgu.exceptions.LdapException;
import br.com.cleartech.pgmc.mgu.services.LdapService;
import br.com.cleartech.pgmc.mgu.services.ParametrizacaoService;

@Service
public class LdapServiceImpl implements LdapService {

	@Autowired
	private LdapTemplate ldapTemplate;

	@Autowired
	private ParametrizacaoService parametrizacaoService;

	@Override
	public void alterarSenha( String usuario, String senha ) throws LdapException {
		// TODO Auto-generated method stub

	}

	@Override
	public void alterarUsuarioParaMaster( String usuario ) throws LdapException {
		// TODO Auto-generated method stub

	}

	@Override
	public void alterarMasterParaUsuario( String usuario ) throws LdapException {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteUser( String usuario, boolean isMaster ) throws LdapException {
		// TODO Auto-generated method stub

	}

	@Override
	public void createUser( String usuario, String password ) throws LdapException {
		// TODO Auto-generated method stub

	}

	@Override
	public void createMasterUser( String usuario, String password ) throws LdapException {
		// TODO Auto-generated method stub

	}

	@Override
	public List<String> consultarTodos() throws LdapException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean existeUsuario( String usuario ) throws LdapException {
		return null;
	}

	@Override
	public Boolean existeUsuario( String usuario, String senha ) throws LdapException {
		String userDnBase = parametrizacaoService.findByDcParametro( ParametrizacaoEnum.USER_DN_PATH_BASE.getDcParametro() );

		//@formatter:off
		ContainerCriteria query = LdapQueryBuilder.query()
				.base( userDnBase )
				.searchScope( SearchScope.SUBTREE )
				.attributes( "cn" )
				.where( "cn" ).is( usuario ).and( "userPassword" ).is( senha );
		

		//@formatter:on
		
		// AndFilter filter = new AndFilter();
		// String getAttrs[] = { "cn" };
		// filter.and( new EqualsFilter( "cn", usuario ) );
		// filter.and( new EqualsFilter( "userpassword", senha ) );
		// 25d55ad283aa400af464c76d713c07ad

		AttributesMapper attrs = new AttributesMapper() {
			@Override
			public Object mapFromAttributes( Attributes attrs ) throws NamingException {
				return attrs.get( "cn" ).get();
			}
		};

		List result = ldapTemplate.search( query, attrs );

		// List result = ldapTemplate.search( userDnBase, filter.encode(),
		// SearchControls.SUBTREE_SCOPE, getAttrs, attrs );
		if ( result.isEmpty() )
			return false;
		return true;
	}

	@Override
	public Boolean contemSenhaNoHistorico( String usuario, String senhaNova ) throws LdapException {
		// TODO Auto-generated method stub
		return null;
	}

}
