package br.com.cleartech.pgmc.mgu.view.dtos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import br.com.cleartech.pgmc.mgu.entities.GrupoPerfil;
import br.com.cleartech.pgmc.mgu.entities.NivelEscalonamento;
import br.com.cleartech.pgmc.mgu.entities.Prestadora;
import br.com.cleartech.pgmc.mgu.entities.Usuario;
import br.com.cleartech.pgmc.mgu.enums.BloqueioUsuario;

public class UsuarioCadastroDTO implements Serializable {

	private static final long serialVersionUID = -8589145118614631343L;

	private Long id;

	@NotBlank
	private String nmUsuario;

	// @NotBlank
	// retirado para a tela de editar
	private String dcUsername;

	@Email
	@NotBlank
	private String dcEmail;

	private String dcCargo;

	@NotBlank
	private String dcTelefone;

	private String dcTelefoneFixo;

	// NotBlank
	// retirado para a tela de editar
	private String nuCpf;

	private NivelEscalonamento nivelEscalonamento;

	private List<GrupoPerfil> grupoPerfis;

	private Prestadora prestadora;

	private List<Integer> grupoPerfisIdList;

	private BloqueioUsuario flBloqueio = BloqueioUsuario.BLOQUEADO_PRIMEIROACESSO;

	private Boolean flEnvioEmail = false;

	private Boolean flMaster;

	private Usuario delegado;

	private String senhaAtual;
	private String senhaNova;
	private String confirmarSenhaNova;

	private String urlConsulta;

	public UsuarioCadastroDTO() {}

	public UsuarioCadastroDTO( Usuario usuario ) {
		this.setId( usuario.getId() );
		this.setNmUsuario( usuario.getNmUsuario() );
		this.setDcUsername( usuario.getDcUsername() );
		this.setDcEmail( usuario.getDcEmail() );
		this.setDcCargo( usuario.getDcCargo() );
		this.setDcTelefone( usuario.getDcTelefone() );
		this.setDcTelefoneFixo( usuario.getDcTelefoneFixo() );
		this.setNuCpf( usuario.getNuCpf() );
		this.setNivelEscalonamento( usuario.getNivelEscalonamento() );
		this.setGrupoPerfis( usuario.getGrupoPerfis() );
		this.setFlBloqueio( usuario.getFlBloqueio() );
		this.setFlEnvioEmail( usuario.isFlEnvioEmail() );
		this.setFlMaster( usuario.getFlMaster() );
		this.setDelegado( usuario.getDelegado() );

		if ( !usuario.getPrestadoras().isEmpty() )
			this.setPrestadora( usuario.getPrestadoras().get( 0 ) );
	}

	public Usuario getUsuario() {
		Usuario usuario = new Usuario();
		usuario.setId( this.getId() );
		usuario.setNmUsuario( this.getNmUsuario() );
		usuario.setDcUsername( this.getDcUsername() );
		usuario.setDcEmail( this.getDcEmail() );
		usuario.setDcCargo( this.getDcCargo() );
		usuario.setDcTelefone( this.getDcTelefone() );
		usuario.setDcTelefoneFixo( this.getDcTelefoneFixo() );
		usuario.setNuCpf( this.getNuCpf() );
		usuario.setNivelEscalonamento( this.getNivelEscalonamento() );
		usuario.setGrupoPerfis( this.getGrupoPerfis() );
		usuario.setFlBloqueio( this.getFlBloqueio() );
		usuario.setFlEnvioEmail( this.getFlEnvioEmail() );
		usuario.setPrestadoras( Arrays.asList( this.getPrestadora() ) );
		usuario.setDelegado( this.getDelegado() );
		return usuario;
	}

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
		if ( grupoPerfisIdList == null ) {
			grupoPerfisIdList = new ArrayList<Integer>();
		}
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

	@Override
	public String toString() {
		return "UsuarioCadastroDTO [id=" + id + ", nmUsuario=" + nmUsuario + ", dcUsername=" + dcUsername + ", dcEmail=" + dcEmail + ", dcCargo=" + dcCargo + ", dcTelefone=" + dcTelefone + ", dcTelefoneFixo=" + dcTelefoneFixo + ", nuCpf=" + nuCpf + ", nivelEscalonamento=" + nivelEscalonamento + ", grupoPerfisIdList=" + grupoPerfisIdList + ", flBloqueio=" + flBloqueio + ", flEnvioEmail=" + flEnvioEmail + "]";
	}

	public Prestadora getPrestadora() {
		return prestadora;
	}

	public void setPrestadora( Prestadora prestadora ) {
		this.prestadora = prestadora;
	}

	public Boolean getFlMaster() {
		return flMaster;
	}

	public void setFlMaster( Boolean flMaster ) {
		this.flMaster = flMaster;
	}

	public Usuario getDelegado() {
		return delegado;
	}

	public void setDelegado( Usuario delegado ) {
		this.delegado = delegado;
	}

	public String getSenhaAtual() {
		return senhaAtual;
	}

	public void setSenhaAtual( String senhaAtual ) {
		this.senhaAtual = senhaAtual;
	}

	public String getSenhaNova() {
		return senhaNova;
	}

	public void setSenhaNova( String senhaNova ) {
		this.senhaNova = senhaNova;
	}

	public String getConfirmarSenhaNova() {
		return confirmarSenhaNova;
	}

	public void setConfirmarSenhaNova( String confirmarSenhaNova ) {
		this.confirmarSenhaNova = confirmarSenhaNova;
	}

	public String getUrlConsulta() {
		return urlConsulta;
	}

	public void setUrlConsulta( String urlConsulta ) {
		this.urlConsulta = urlConsulta;
	}

}
