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

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
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
	@XmlElement( name = "id" )
	private Long id;

	@Column( name = "DC_CARGO" )
	@XmlElement( name = "cargo" )
	private String dcCargo;

	@Column( name = "DC_EMAIL" )
	@XmlElement( name = "email" )
	private String dcEmail;

	@Column( name = "FL_DYNAMICS" )
	private boolean flEnviarDynamics;

	@Column( name = "DC_IP_ORIGEM" )
	@XmlElement( name = "iporigem" )
	private String dcIpOrigem;

	@Transient
	@XmlElement( name = "senha" )
	private String dcSenha;

	@Transient
	private String dcConfirmaSenha;

	@Transient
	private String dcSenhaAntiga;

	@Transient
	@XmlElement( name = "sistema" )
	private String sistema;

	@Column( name = "DC_TELEFONE" )
	@XmlElement( name = "telefone" )
	private String dcTelefone;

	@Column( name = "DC_TELEFONE_FIXO" )
	@XmlElement( name = "telefone2" )
	private String dcTelefoneFixo;

	@Column( name = "FL_MASTER" )
	private boolean flMaster;

	@Column( name = "FL_BLOQUEIO", precision = 1 )
	@Enumerated( EnumType.ORDINAL )
	@XmlElement( name = "bloqueado" )
	private BloqueioUsuario flBloqueio = BloqueioUsuario.BLOQUEADO_PRIMEIROACESSO;

	@Column( name = "FL_PRIMEIRO_ACESSO" )
	private boolean flPrimeiroAcesso;

	@Column( name = "NM_USUARIO" )
	@XmlElement( name = "nomeusuario" )
	private String nmUsuario;

	@Column( name = "DC_USERNAME" )
	@XmlElement( name = "usuario" )
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
	@XmlElement( name = "cpf" )
	private String nuCpf;

	@Column( name = "FL_APROVADO" )
	private boolean flAprovado;

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
	@JoinTable( name = "USUARIO_X_GRUPO_PERFIL_TEMP", joinColumns = @JoinColumn( name = "PK_ID_USUARIO" ), inverseJoinColumns = @JoinColumn( name = "PK_ID_GRUPO_PERFIL" ) )
	private List<GrupoPerfil> grupoPerfis;

	@Transient
	private List<Integer> grupoPerfisIdList;

	@NotAudited
	@ManyToMany
	@Fetch( FetchMode.JOIN )
	@JoinTable( name = "PRESTADORA_X_USUARIO_TEMP", joinColumns = @JoinColumn( name = "PK_ID_USUARIO" ), inverseJoinColumns = @JoinColumn( name = "PK_ID_PRESTADORA" ) )
	private List<Prestadora> prestadoras;

	@ManyToOne
	@JoinColumn( name = "FK_NIVEL_ESCALONAMENTO" )
	private NivelEscalonamento nivelEscalonamento;

	@Column( name = "FL_ENVIO_EMAIL" )
	private boolean flEnvioEmail;

	@Column( name = "FL_USUARIO_SISTEMA" )
	private Integer flUsuarioSistema;

	@Transient
	private List<Long> listaGrupoPerfil;

	@Transient
	@XmlElement( name = "grupo_prestadora" )
	private GrupoPrestadora grupos;

	@Transient
	// Utilizado no envio de e-mail
	private String senhaSemMD5;

	@Transient
	private Boolean usuarioLogado = false;

	public Usuario() {}

	public boolean getFlEnviarDynamics() {
		return flEnviarDynamics;
	}

	public void setFlEnviarDynamics( boolean flEnviarDynamics ) {
		this.flEnviarDynamics = flEnviarDynamics;
	}

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

	public String getDcCargo() {
		return this.dcCargo;
	}

	public void setDcCargo( String dcCargo ) {
		this.dcCargo = dcCargo;
	}

	public String getDcEmail() {
		return this.dcEmail;
	}

	public void setDcEmail( String dcEmail ) {
		this.dcEmail = dcEmail;
	}

	public String getDcIpOrigem() {
		return this.dcIpOrigem;
	}

	public void setDcIpOrigem( String dcIpOrigem ) {
		this.dcIpOrigem = dcIpOrigem;
	}

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

	public boolean getFlMaster() {
		return flMaster;
	}

	public void setFlMaster( boolean flMaster ) {
		this.flMaster = flMaster;
	}

	public BloqueioUsuario getFlBloqueio() {
		return flBloqueio;
	}

	public void setFlBloqueio( BloqueioUsuario flBloqueio ) {
		this.flBloqueio = flBloqueio;
	}

	public String getNmUsuario() {
		return this.nmUsuario;
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

	public String getNuCpf() {
		if ( this.nuCpf != null )
			return this.nuCpf.replaceAll( "[^\\d]", "" );

		else
			return this.nuCpf;
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

	public boolean getFlAprovado() {
		return flAprovado;
	}

	public void setFlAprovado( boolean flAprovado ) {
		this.flAprovado = flAprovado;
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
		if ( prestadoras == null )
			prestadoras = new ArrayList<Prestadora>();
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

	public String getSistema() {
		return sistema;
	}

	public void setSistema( String sistema ) {
		this.sistema = sistema;
	}

	public boolean getFlPrimeiroAcessoSNOA() {
		return flPrimeiroAcessoSNOA;
	}

	public void setFlPrimeiroAcessoSNOA( boolean flPrimeiroAcessoSNOA ) {
		this.flPrimeiroAcessoSNOA = flPrimeiroAcessoSNOA;
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

	public List<Long> getListaGrupoPerfil() {
		return listaGrupoPerfil;
	}

	public void setListaGrupoPerfil( List<Long> listaGrupoPerfil ) {
		this.listaGrupoPerfil = listaGrupoPerfil;
	}

	public String getSenhaSemMD5() {
		return senhaSemMD5;
	}

	public void setSenhaSemMD5( String senhaSemMD5 ) {
		this.senhaSemMD5 = senhaSemMD5;
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

	public GrupoPrestadora getGrupos() {
		return grupos;
	}

	public void setGrupos( GrupoPrestadora grupos ) {
		this.grupos = grupos;
	}

	public Boolean getUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado( Boolean usuarioLogado ) {
		this.usuarioLogado = usuarioLogado;
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

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", dcCargo=" + dcCargo + ", dcEmail=" + dcEmail + ", flEnviarDynamics=" + flEnviarDynamics + ", sistema=" + sistema + ", dcTelefone=" + dcTelefone + ", dcTelefoneFixo=" + dcTelefoneFixo + ", flMaster=" + flMaster + ", flBloqueio=" + flBloqueio + ", flPrimeiroAcesso=" + flPrimeiroAcesso + ", nmUsuario=" + nmUsuario + ", dcUsername=" + dcUsername + ", nuCpf=" + nuCpf + ", flArovado=" + flAprovado + ", flPrimeiroAcessoSNOA=" + getFlPrimeiroAcessoSNOA() + ", perfil=" + perfil + ", usuarios=" + usuarios + ", grupoPerfis=" + grupoPerfis + ", flEnvioEmail=" + flEnvioEmail + ", flUsuarioSistema=" + flUsuarioSistema + "]";
	}

}
