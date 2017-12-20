package br.com.cleartech.pgmc.mgu.dtos;

import java.io.Serializable;
import java.util.List;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import br.com.cleartech.pgmc.mgu.entities.GrupoPerfil;
import br.com.cleartech.pgmc.mgu.entities.NivelEscalonamento;
import br.com.cleartech.pgmc.mgu.enums.BloqueioUsuario;

public class UsuarioCadastroDTO implements Serializable {

	private static final long serialVersionUID = -8589145118614631343L;

	private Long id;

	@NotBlank
	private String nmUsuario;

	@NotBlank
	private String dcUsername;

	@Email
	@NotBlank
	private String dcEmail;

	private String dcCargo;

	@NotBlank
	private String dcTelefone;

	private String dcTelefoneFixo;

	@NotBlank
	private String nuCpf;

	private NivelEscalonamento nivelEscalonamento;

	private List<GrupoPerfil> grupoPerfis;

	@NotEmpty
	private List<Integer> grupoPerfisIdList;

	private BloqueioUsuario flBloqueio = BloqueioUsuario.BLOQUEADO_PRIMEIROACESSO;

	private Boolean flEnvioEmail = false;

	public UsuarioCadastroDTO() {}

	public Long getId() {
		return id;
	}

	public void setId( Long id ) {
		this.id = id;
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

	public String getDcEmail() {
		return dcEmail;
	}

	public void setDcEmail( String dcEmail ) {
		this.dcEmail = dcEmail;
	}

	public String getDcCargo() {
		return dcCargo;
	}

	public void setDcCargo( String dcCargo ) {
		this.dcCargo = dcCargo;
	}

	public String getDcTelefone() {
		return dcTelefone;
	}

	public void setDcTelefone( String dcTelefone ) {
		this.dcTelefone = dcTelefone;
	}

	public String getDcTelefoneFixo() {
		return dcTelefoneFixo;
	}

	public void setDcTelefoneFixo( String dcTelefoneFixo ) {
		this.dcTelefoneFixo = dcTelefoneFixo;
	}

	public String getNuCpf() {
		return nuCpf;
	}

	public void setNuCpf( String nuCpf ) {
		this.nuCpf = nuCpf;
	}

	public NivelEscalonamento getNivelEscalonamento() {
		return nivelEscalonamento;
	}

	public void setNivelEscalonamento( NivelEscalonamento nivelEscalonamento ) {
		this.nivelEscalonamento = nivelEscalonamento;
	}

	public List<GrupoPerfil> getGrupoPerfis() {
		return grupoPerfis;
	}

	public void setGrupoPerfis( List<GrupoPerfil> grupoPerfis ) {
		this.grupoPerfis = grupoPerfis;
	}

	public List<Integer> getGrupoPerfisIdList() {
		return grupoPerfisIdList;
	}

	public void setGrupoPerfisIdList( List<Integer> grupoPerfisIdList ) {
		this.grupoPerfisIdList = grupoPerfisIdList;
	}

	public BloqueioUsuario getFlBloqueio() {
		return flBloqueio;
	}

	public void setFlBloqueio( BloqueioUsuario flBloqueio ) {
		this.flBloqueio = flBloqueio;
	}

	public Boolean getFlEnvioEmail() {
		return flEnvioEmail;
	}

	public void setFlEnvioEmail( Boolean flEnvioEmail ) {
		this.flEnvioEmail = flEnvioEmail;
	}

}
