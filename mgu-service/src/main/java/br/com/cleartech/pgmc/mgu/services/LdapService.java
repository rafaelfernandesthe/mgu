package br.com.cleartech.pgmc.mgu.services;

import java.util.List;

import br.com.cleartech.pgmc.mgu.exceptions.LdapException;

public interface LdapService {

	/**
	 * Altera a senha do usuario no LDAP
	 * 
	 * @param usuario
	 * @param senha
	 * @throws LdapException
	 */
	public void alterarSenha( String usuario, String senha ) throws LdapException;

	/**
	 * Altera o grupo do usuario para master
	 * 
	 * @param usuario
	 * @param senha
	 * @throws LdapException
	 */
	public void alterarUsuarioParaMaster( String usuario ) throws LdapException;

	/**
	 * Altera o grupo do usuario para master
	 * 
	 * @param usuario
	 * @param senha
	 * @throws LdapException
	 */

	public void alterarMasterParaUsuario( String usuario ) throws LdapException;

	/**
	 * Deleta o usuario no LDAP
	 * 
	 * @param usuario
	 * @throws LdapException
	 */
	public void deleteUser( String usuario, boolean isMaster ) throws LdapException;

	/**
	 * Cria o usuario no LDAP
	 * 
	 * @param usuario
	 * @param password
	 * @throws LdapException
	 */
	public void createUser( String usuario, String password ) throws LdapException;

	/**
	 * Cria o usuario Master no LDAP
	 * 
	 * @param usuario
	 * @param password
	 * @throws LdapException
	 */
	public void createMasterUser( String usuario, String password ) throws LdapException;

	/**
	 * Retorna uma lista com todos os usuarios.
	 * 
	 * @return
	 * @throws LdapException
	 */
	public List<String> consultarTodos() throws LdapException;

	/**
	 * Verifica se existe o usuario cadastrado no LDAP.
	 * 
	 * @param usuario
	 * @return
	 * @throws LdapException
	 */
	public Boolean existeUsuario( String usuario ) throws LdapException;

	/**
	 * Verifica se existe o usuario cadastrado no LDAP.
	 * 
	 * @param usuario
	 * @return
	 * @throws LdapException
	 */
	public Boolean existeUsuario( String usuario, String senha ) throws LdapException;

	public Boolean contemSenhaNoHistorico( String usuario, String senhaNova ) throws LdapException;
}
