package br.com.cleartech.pgmc.mgu.integration.ws.requests;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import br.com.cleartech.pgmc.mgu.entities.Usuario;
import br.com.cleartech.pgmc.mgu.enums.SimNaoEnum;

@XmlRootElement( name = "usuario" )
@XmlType( name = "dados", propOrder = { "nome", "telefone", "telefone2", "email", "cpf", "cargo", "userName", "aprovado", "ipOrigem", "idGrupoPrestadora", "idPrestadora", "userNameAnterior" } )
public class UsuarioMasterRequest {

	private String nome;
	private String telefone;
	private String telefone2;
	private String email;
	private String cpf;
	private String cargo;
	private String userNameAnterior;
	private String userName;
	private boolean aprovado;
	private String ipOrigem;
	private Long idGrupoPrestadora;
	private Long idPrestadora;

	@XmlElement( name = "nome" )
	public String getNome() {
		return nome;
	}

	public void setNome( String nome ) {
		this.nome = nome;
	}

	@XmlElement( name = "telefone" )
	public String getTelefone() {
		return telefone;
	}

	public void setTelefone( String telefone ) {
		this.telefone = telefone;
	}

	@XmlElement( name = "telefone2" )
	public String getTelefone2() {
		return telefone2;
	}

	public void setTelefone2( String telefone2 ) {
		this.telefone2 = telefone2;
	}

	@XmlElement( name = "email" )
	public String getEmail() {
		return email;
	}

	public void setEmail( String email ) {
		this.email = email;
	}

	@XmlElement( name = "cpf" )
	public String getCpf() {
		return cpf;
	}

	public void setCpf( String cpf ) {
		this.cpf = cpf;
	}

	@XmlElement( name = "cargo" )
	public String getCargo() {
		return cargo;
	}

	public void setCargo( String cargo ) {
		this.cargo = cargo;
	}

	@XmlElement( name = "userName" )
	public String getUserName() {
		return userName;
	}

	public void setUserName( String userName ) {
		this.userName = userName;
	}

	@XmlElement( name = "aprovado" )
	public boolean isAprovado() {
		return aprovado;
	}

	public void setAprovado( boolean aprovado ) {
		this.aprovado = aprovado;
	}

	@XmlElement( name = "ipOrigem" )
	public String getIpOrigem() {
		return ipOrigem;
	}

	public void setIpOrigem( String iOrigem ) {
		this.ipOrigem = iOrigem;
	}

	@XmlElement( name = "idGrupoPrestadora" )
	public Long getIdGrupoPrestadora() {
		return idGrupoPrestadora;
	}

	public void setIdGrupoPrestadora( Long idGrupoPrestadora ) {
		this.idGrupoPrestadora = idGrupoPrestadora;
	}

	@XmlElement( name = "idPrestadora" )
	public Long getIdPrestadora() {
		return idPrestadora;
	}

	public void setIdPrestadora( Long idPrestadora ) {
		this.idPrestadora = idPrestadora;
	}

	@XmlElement( name = "userNameAnterior" )
	public String getUserNameAnterior() {
		return userNameAnterior;
	}

	public void setUserNameAnterior( String userNameAnterior ) {
		this.userNameAnterior = userNameAnterior;
	}

	public Usuario masterToUsuario() {
		Usuario usuario = new Usuario();
		usuario.setNmUsuario( this.nome.trim() );
		usuario.setDcCargo( this.cargo != null ? this.cargo.trim() : null );
		usuario.setNuCpf( this.cpf.trim().replaceAll( "[^\\d]", "" ) );
		usuario.setDcTelefone( this.telefone.trim() );
		usuario.setDcTelefoneFixo( this.telefone2 != null ? this.telefone2.trim() : null );
		usuario.setDcEmail( this.email.trim() );
		usuario.setDcIpOrigem( this.ipOrigem != null ? this.ipOrigem.trim() : null );
		usuario.setDcUsername( this.userName.trim() );
		usuario.setFlArovado( this.aprovado == true ? SimNaoEnum.SIM : SimNaoEnum.NAO );

		return usuario;
	}

}
