package br.com.cleartech.pgmc.mgu.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import br.com.cleartech.pgmc.mgu.enums.BloqueioUsuario;

@Audited
@Entity
@Table( name = "USUARIO" )
@SequenceGenerator( name = "SEQ_USUARIO", sequenceName = "SEQ_USUARIO", initialValue = 1, allocationSize = 1 )
@XmlRootElement( name = "usuario" )
public class Usuario implements Serializable {

	private static final long serialVersionUID = -8589145118614631343L;

	@Id
	@GeneratedValue( generator = "SEQ_USUARIO" )
	@Column( name = "PK_ID_USUARIO" )
	private Long id;

	@Column( name = "DC_CARGO" )
	private String dcCargo;

	@Column( name = "DC_EMAIL" )
	private String dcEmail;

	@Column( name = "FL_DYNAMICS" )
	private boolean flEnviarDynamics;

	@Column( name = "DC_IP_ORIGEM" )
	private String dcIpOrigem;

	@Transient
	private String dcSenha;

	@Transient
	private String dcConfirmaSenha;

	@Transient
	private String dcSenhaAntiga;

	@Transient
	private String sistema;

	@Column( name = "DC_TELEFONE" )
	private String dcTelefone;

	@Column( name = "DC_TELEFONE_FIXO" )
	private String dcTelefoneFixo;

	@Column( name = "FL_MASTER" )
	private boolean flMaster;

	@Column( name = "FL_BLOQUEIO", precision = 1 )
	@Enumerated( EnumType.ORDINAL )
	private BloqueioUsuario flBloqueio = BloqueioUsuario.BLOQUEADO_PRIMEIROACESSO;

	@Column( name = "FL_PRIMEIRO_ACESSO" )
	private boolean flPrimeiroAcesso;

	@Column( name = "NM_USUARIO" )
	private String nmUsuario;

	@Column( name = "DC_USERNAME" )
	private String dcUsername;

	@Temporal( value = TemporalType.TIMESTAMP )
	@Column( name = "DT_ULTIMO_ACESSO" )
	private Date ultimoAcesso;

	@Column( name = "QT_TENTATIVA_ACESSO" )
	private Long qtTentativaAcesso;

	@Temporal( value = TemporalType.TIMESTAMP )
	@Column( name = "ULTIMA_TROCA_SENHA" )
	private Date ultimaTrocaSenha;

	@Column( name = "NU_CPF" )
	private String nuCpf;

	@Column( name = "FL_APROVADO" )
	private boolean flArovado;

	@Column( name = "FL_PRIMEIRO_ACESSO_SNOA" )
	private boolean flPrimeiroAcessoSNOA;

	// bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn( name = "PK_ID_DELEGADO" )
	private Usuario delegado;

	@NotAudited
	@ManyToOne
	@JoinColumn( name = "PK_ID_PERFIL" )
	private Perfil perfil;

	// bi-directional many-to-one association to Usuario
	@NotAudited
	@OneToMany( mappedBy = "delegado" )
	private List<Usuario> usuarios;

	// bi-directional many-to-one association to Delegado
	@NotAudited
	@OneToMany( mappedBy = "usuarioMaster" )
	private List<Delegado> delegadosMaster;

	@NotAudited
	@OneToMany( mappedBy = "usuarioComum" )
	private List<Delegado> delegadosComuns;

	@NotAudited
	@ManyToMany
	@JoinTable( name = "usuario_x_grupo_perfil", joinColumns = @JoinColumn( name = "pk_id_usuario" ), inverseJoinColumns = @JoinColumn( name = "pk_id_grupo_perfil" ) )
	private List<GrupoPerfil> grupoPerfis;

	@Transient
	private List<Integer> grupoPerfisIdList;

	@NotAudited
	@ManyToMany
	@JoinTable( name = "PRESTADORA_X_USUARIO", joinColumns = @JoinColumn( name = "PK_ID_USUARIO" ), inverseJoinColumns = @JoinColumn( name = "PK_ID_PRESTADORA" ) )
	private List<Prestadora> prestadoras;

	@ManyToOne
	@JoinColumn( name = "FK_NIVEL_ESCALONAMENTO" )
	private NivelEscalonamento nivelEscalonamento;

	@Column( name = "FL_ENVIO_EMAIL" )
	private boolean flEnvioEmail;

	@Column( name = "FL_USUARIO_SISTEMA" )
	private Integer flUsuarioSistema;

	public Usuario() {}

	public boolean getFlEnviarDynamics() {
		return flEnviarDynamics;
	}

	public void setFlEnviarDynamics( boolean flEnviarDynamics ) {
		this.flEnviarDynamics = flEnviarDynamics;
	}

	@XmlElement( name = "id" )
	public Long getId() {
		return id;
	}

	public void setId( Long id ) {
		this.id = id;
	}

	public Date getUltimaTrocaSenha() {
		return ultimaTrocaSenha;
	}

	public void setUltimaTrocaSenha( Date ultimaTrocaSenha ) {
		this.ultimaTrocaSenha = ultimaTrocaSenha;
	}

	@XmlElement( name = "cargo" )
	public String getDcCargo() {
		return this.dcCargo;
	}

	public void setDcCargo( String dcCargo ) {
		this.dcCargo = dcCargo;
	}

	@XmlElement( name = "email" )
	public String getDcEmail() {
		return this.dcEmail;
	}

	public void setDcEmail( String dcEmail ) {
		this.dcEmail = dcEmail;
	}

	@XmlElement( name = "iporigem" )
	public String getDcIpOrigem() {
		return this.dcIpOrigem;
	}

	public void setDcIpOrigem( String dcIpOrigem ) {
		this.dcIpOrigem = dcIpOrigem;
	}

	@XmlElement( name = "senha" )
	public String getDcSenha() {
		return this.dcSenha;
	}

	public void setDcSenha( String dcSenha ) {
		this.dcSenha = dcSenha;
	}

	public String getDcConfirmaSenha() {
		return dcConfirmaSenha;
	}

	public void setDcConfirmaSenha( String dcConfirmaSenha ) {
		this.dcConfirmaSenha = dcConfirmaSenha;
	}

	public String getDcSenhaAntiga() {
		return dcSenhaAntiga;
	}

	public void setDcSenhaAntiga( String dcSenhaAntiga ) {
		this.dcSenhaAntiga = dcSenhaAntiga;
	}

	@XmlElement( name = "telefone" )
	public String getDcTelefone() {
		return dcTelefone;
	}

	public void setDcTelefone( String dcTelefone ) {
		this.dcTelefone = dcTelefone;
	}

	@XmlElement( name = "telefone2" )
	public String getDcTelefoneFixo() {
		return dcTelefoneFixo;
	}

	public void setDcTelefoneFixo( String dcTelefoneFixo ) {
		this.dcTelefoneFixo = dcTelefoneFixo;
	}

	public boolean getFlMaster() {
		return flMaster;
	}

	public void setFlMaster( boolean flMaster ) {
		this.flMaster = flMaster;
	}

	@XmlElement( name = "bloqueado" )
	public BloqueioUsuario getFlBloqueio() {
		return flBloqueio;
	}

	public void setFlBloqueio( BloqueioUsuario flBloqueio ) {
		this.flBloqueio = flBloqueio;
	}

	@XmlElement( name = "nomeusuario" )
	public String getNmUsuario() {
		return this.nmUsuario;
	}

	public void setNmUsuario( String nmUsuario ) {
		this.nmUsuario = nmUsuario;
	}

	@XmlElement( name = "usuario" )
	public String getDcUsername() {
		return dcUsername;
	}

	public void setDcUsername( String dcUsername ) {
		this.dcUsername = dcUsername;
	}

	@XmlElement( name = "cpf" )
	public String getNuCpf() {
		return this.nuCpf.replaceAll( "[^\\d]", "" );
	}

	public String getNuCpfWithMask() {
		return applyMask( getNuCpf() );
	}

	public String applyMask( String cpf ) {
		if ( cpf.length() == 11 ) {
			return String.format( "%s.%s.%s-%s", cpf.substring( 0, 3 ), cpf.substring( 3, 6 ), cpf.substring( 6, 9 ), cpf.substring( 9, 11 ) );
		}
		return cpf;
	}

	public void setNuCpf( String nuCpf ) {
		this.nuCpf = nuCpf;
	}

	public boolean getFlArovado() {
		return flArovado;
	}

	public void setFlArovado( boolean flArovado ) {
		this.flArovado = flArovado;
	}

	public Usuario getDelegado() {
		return this.delegado;
	}

	public void setDelegado( Usuario usuario ) {
		this.delegado = usuario;
	}

	public List<Usuario> getUsuarios() {
		return this.usuarios;
	}

	public void setUsuarios( List<Usuario> usuarios ) {
		this.usuarios = usuarios;
	}

	public Date getUltimoAcesso() {
		return ultimoAcesso;
	}

	public void setUltimoAcesso( Date ultimoAcesso ) {
		this.ultimoAcesso = ultimoAcesso;
	}

	public Long getQtTentativaAcesso() {
		return qtTentativaAcesso;
	}

	public void setQtTentativaAcesso( Long qtTentativaAcesso ) {
		this.qtTentativaAcesso = qtTentativaAcesso;
	}

	public List<GrupoPerfil> getGrupoPerfis() {
		if ( grupoPerfis == null )
			grupoPerfis = new ArrayList<GrupoPerfil>();
		return grupoPerfis;
	}

	public void setGrupoPerfis( List<GrupoPerfil> grupoPerfis ) {
		this.grupoPerfis = grupoPerfis;
	}

	public List<Prestadora> getPrestadoras() {
		return prestadoras;
	}

	public void setPrestadoras( List<Prestadora> prestadoras ) {
		this.prestadoras = prestadoras;
	}

	public NivelEscalonamento getNivelEscalonamento() {
		return nivelEscalonamento;
	}

	public void setNivelEscalonamento( NivelEscalonamento nivelEscalonamento ) {
		this.nivelEscalonamento = nivelEscalonamento;
	}

	@XmlElement
	public String getSistema() {
		return sistema;
	}

	public void setSistema( String sistema ) {
		this.sistema = sistema;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ( ( getId() == null ) ? 0 : getId().hashCode() );
		return result;
	}

	@Override
	public boolean equals( Object obj ) {
		if ( this == obj )
			return true;
		if ( obj == null )
			return false;
		if ( getClass() != obj.getClass() )
			return false;
		Usuario other = (Usuario) obj;
		if ( getId() == null ) {
			if ( other.getId() != null )
				return false;
		} else if ( !getId().equals( other.getId() ) )
			return false;
		return true;
	}

	public Integer getFlUsuarioSistema() {
		return flUsuarioSistema;
	}

	public void setFlUsuarioSistema( Integer flUsuarioSistema ) {
		this.flUsuarioSistema = flUsuarioSistema;
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil( Perfil perfil ) {
		this.perfil = perfil;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", dcCargo=" + dcCargo + ", dcEmail=" + dcEmail + ", flEnviarDynamics=" + flEnviarDynamics + ", sistema=" + sistema + ", dcTelefone=" + dcTelefone + ", dcTelefoneFixo=" + dcTelefoneFixo + ", flMaster=" + flMaster + ", flBloqueio=" + flBloqueio + ", flPrimeiroAcesso=" + flPrimeiroAcesso + ", nmUsuario=" + nmUsuario + ", dcUsername=" + dcUsername + ", nuCpf=" + nuCpf + ", flArovado=" + flArovado + ", flPrimeiroAcessoSNOA=" + flPrimeiroAcessoSNOA + ", perfil=" + perfil + ", usuarios=" + usuarios + ", grupoPerfis=" + grupoPerfis + ", flEnvioEmail=" + flEnvioEmail + ", flUsuarioSistema=" + flUsuarioSistema + "]";
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

}
