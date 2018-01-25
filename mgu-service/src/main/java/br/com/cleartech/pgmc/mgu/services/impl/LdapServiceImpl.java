package br.com.cleartech.pgmc.mgu.services.impl;

import java.util.List;

import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.BasicAttributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.ModificationItem;
import javax.naming.ldap.LdapName;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.query.ContainerCriteria;
import org.springframework.ldap.query.LdapQueryBuilder;
import org.springframework.ldap.query.SearchScope;
import org.springframework.ldap.support.LdapNameBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.cleartech.pgmc.mgu.enums.ParametrizacaoEnum;
import br.com.cleartech.pgmc.mgu.exceptions.LdapException;
import br.com.cleartech.pgmc.mgu.services.LdapService;
import br.com.cleartech.pgmc.mgu.services.ParametrizacaoService;

@Service
@Transactional
public class LdapServiceImpl implements LdapService {

	@Autowired
	private LdapTemplate ldapTemplate;

	@Autowired
	private ParametrizacaoService parametrizacaoService;

	@Override
	public void alterarSenha( String usuario, String senha ) throws LdapException {
		try {
			LdapName dn = LdapNameBuilder.newInstance().add( getUserDn() ).add( "cn", usuario ).build();

			Attribute attr = new BasicAttribute( "userPassword", senha );
			ModificationItem item = new ModificationItem( DirContext.REPLACE_ATTRIBUTE, attr );
			ldapTemplate.modifyAttributes( dn, new ModificationItem[] { item } );
		} catch ( Exception e ) {
			e.printStackTrace();
			throw new LdapException( "Erro ao alterar usuario" );
		}
	}

	@Override
	public void alterarUsuarioParaMaster( String usuario ) throws LdapException {
		try {
			LdapName dn = LdapNameBuilder.newInstance().add( getUserDn() ).add( "cn", usuario ).build();

			//@formatter:off
			ContainerCriteria query = LdapQueryBuilder.query()
				.base( getGrupoDnMaster() )
				.searchScope( SearchScope.SUBTREE )
				.attributes( "uniqueMember" )
				.where( "uniqueMember" ).is( dn.toString() +","+ getLdapRoot());
			//@formatter:on

			AttributesMapper<?> attrs = new AttributesMapper<Object>() {
				@Override
				public Object mapFromAttributes( Attributes attrs ) throws NamingException {
					return attrs.get( "uniqueMember" ).get();
				}
			};

			List<?> result = ldapTemplate.search( query, attrs );

			// ja esta no grupo master
			if ( !result.isEmpty() )
				return;

			Attribute attr = new BasicAttribute( "uniqueMember", dn.toString() + "," + getLdapRoot() );

			ModificationItem item = new ModificationItem( DirContext.ADD_ATTRIBUTE, attr );
			ldapTemplate.modifyAttributes( getGrupoDnMaster(), new ModificationItem[] { item } );

		} catch ( Exception e ) {
			e.printStackTrace();
			throw new LdapException( "Erro ao alterarUsuarioParaMaster" );
		}
	}

	@Override
	public void alterarMasterParaUsuario( String usuario ) throws LdapException {
		try {
			LdapName dn = LdapNameBuilder.newInstance().add( getUserDn() ).add( "cn", usuario ).build();

			//@formatter:off
			ContainerCriteria query = LdapQueryBuilder.query()
				.base( getGrupoDnMaster() )
				.searchScope( SearchScope.SUBTREE )
				.attributes( "uniqueMember" )
				.where( "uniqueMember" ).is( dn.toString() +","+ getLdapRoot());
			//@formatter:on

			AttributesMapper<?> attrs = new AttributesMapper<Object>() {
				@Override
				public Object mapFromAttributes( Attributes attrs ) throws NamingException {
					return attrs.get( "uniqueMember" ).get();
				}
			};

			List<?> result = ldapTemplate.search( query, attrs );

			if ( result.isEmpty() )
				return;

			Attribute attr = new BasicAttribute( "uniqueMember", dn.toString() + "," + getLdapRoot() );

			ModificationItem item = new ModificationItem( DirContext.REMOVE_ATTRIBUTE, attr );
			ldapTemplate.modifyAttributes( getGrupoDnMaster(), new ModificationItem[] { item } );

		} catch ( Exception e ) {
			e.printStackTrace();
			throw new LdapException( "Erro ao alterarMasterParaUsuario" );
		}
	}

	@Override
	public void deleteUser( String usuario, boolean isMaster ) throws LdapException {
		try {
			LdapName dn = LdapNameBuilder.newInstance().add( getUserDn() ).add( "cn", usuario ).build();
			ldapTemplate.unbind( dn );

			Attribute attr = new BasicAttribute( "uniqueMember", dn.toString() + "," + getLdapRoot() );
			ModificationItem item = new ModificationItem( DirContext.REMOVE_ATTRIBUTE, attr );
			if ( isMaster ) {
				ldapTemplate.modifyAttributes( getGrupoDnMaster(), new ModificationItem[] { item } );
			} else {
				ldapTemplate.modifyAttributes( getGrupoDnUsuario(), new ModificationItem[] { item } );
			}
		} catch ( Exception e ) {
			e.printStackTrace();
			throw new LdapException( "Erro ao deletar usuario" );
		}
	}

	@Override
	public void createUser( String usuario, String password, boolean master ) throws LdapException {
		try {
			Attributes attrs = new BasicAttributes();
			attrs.put( "objectclass", "person" );
			attrs.put( "cn", usuario );
			attrs.put( "sn", usuario );
			attrs.put( "userpassword", password );
			LdapName dn = LdapNameBuilder.newInstance().add( getUserDn() ).add( "cn", usuario ).build();
			ldapTemplate.bind( dn, null, attrs );

			Attribute attr = new BasicAttribute( "uniqueMember", dn.toString() + "," + getLdapRoot() );
			ModificationItem item = new ModificationItem( DirContext.ADD_ATTRIBUTE, attr );
			if ( master ) {
				ldapTemplate.modifyAttributes( getGrupoDnMaster(), new ModificationItem[] { item } );
			} else {
				ldapTemplate.modifyAttributes( getGrupoDnUsuario(), new ModificationItem[] { item } );
			}
		} catch ( Exception e ) {
			e.printStackTrace();
			throw new LdapException();
		}
	}

	@Override
	public List<String> consultarTodos() throws LdapException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean existeUsuario( String usuario ) throws LdapException {
		//@formatter:off
		ContainerCriteria query = LdapQueryBuilder.query()
			.base( getUserDn() )
			.searchScope( SearchScope.SUBTREE )
			.attributes( "cn" )
			.where( "cn" ).is( usuario );
		//@formatter:on

		AttributesMapper<?> attrs = new AttributesMapper<Object>() {
			@Override
			public Object mapFromAttributes( Attributes attrs ) throws NamingException {
				return attrs.get( "cn" ).get();
			}
		};

		return !ldapTemplate.search( query, attrs ).isEmpty();
	}

	@Override
	public Boolean existeUsuario( String usuario, String senha ) throws LdapException {
		//@formatter:off
		ContainerCriteria query = LdapQueryBuilder.query()
			.base( getUserDn() )
			.searchScope( SearchScope.SUBTREE )
			.attributes( "cn" )
			.where( "cn" ).is( usuario ).and( "userPassword" ).is( senha );
		//@formatter:on

		// AndFilter filter = new AndFilter();
		// String getAttrs[] = { "cn" };
		// filter.and( new EqualsFilter( "cn", usuario ) );
		// filter.and( new EqualsFilter( "userpassword", senha ) );
		// 25d55ad283aa400af464c76d713c07ad

		AttributesMapper<?> attrs = new AttributesMapper<Object>() {
			@Override
			public Object mapFromAttributes( Attributes attrs ) throws NamingException {
				return attrs.get( "cn" ).get();
			}
		};

		List<?> result = ldapTemplate.search( query, attrs );

		// List result = ldapTemplate.search( userDnBase, filter.encode(),
		// SearchControls.SUBTREE_SCOPE, getAttrs, attrs );
		if ( result.isEmpty() )
			return false;
		return true;
	}

	@Override
	public Boolean existeUsuarioMaster( String usuario, String senha ) throws LdapException {
		LdapName dn = LdapNameBuilder.newInstance().add( getUserDn() ).add( "cn", usuario ).build();

		//@formatter:off
		ContainerCriteria query = LdapQueryBuilder.query()
			.base( getGrupoDnMaster() )
			.searchScope( SearchScope.SUBTREE )
			.attributes( "uniqueMember" )
			.where( "uniqueMember" ).is( dn.toString() +","+ getLdapRoot());
		//@formatter:on

		AttributesMapper<?> attrs = new AttributesMapper<Object>() {
			@Override
			public Object mapFromAttributes( Attributes attrs ) throws NamingException {
				return attrs.get( "uniqueMember" ).get();
			}
		};

		List<?> result = ldapTemplate.search( query, attrs );

		if ( result.size() == 1 )
			return existeUsuario( usuario, senha );

		return false;
	}

	@Override
	public Boolean contemSenhaNoHistorico( String usuario, String senhaNova ) throws LdapException {
		// TODO Auto-generated method stub
		return null;
	}

	private String getUserDn() {
		return parametrizacaoService.findByDcParametro( ParametrizacaoEnum.USER_DN_PATH.getDcParametro() );
	}

	private String getGrupoDnUsuario() {
		return parametrizacaoService.findByDcParametro( ParametrizacaoEnum.GRUPO_DN_USUARIO.getDcParametro() );
	}

	private String getGrupoDnMaster() {
		return parametrizacaoService.findByDcParametro( ParametrizacaoEnum.GRUPO_DN_MASTER.getDcParametro() );
	}

	private String getLdapRoot() {
		return parametrizacaoService.findByDcParametro( ParametrizacaoEnum.LDAP_ROOT.getDcParametro() );
	}

}
