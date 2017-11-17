package br.com.cleartech.pgmc.mgu.entities;

import java.io.Serializable;
import java.util.ArrayList;
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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import br.com.cleartech.pgmc.mgu.enums.TipoOperadora;

@Audited
@Entity
@Table( name = "ACESSO_OPER" )
@SequenceGenerator( name = "SEQ_ACESSO_OPER", sequenceName = "SEQ_ACESSO_OPER", initialValue = 1, allocationSize = 1 )
public class AcessoOperadora implements Serializable {

	private static final long serialVersionUID = -8737270469349486935L;

	@Id
	@GeneratedValue( generator = "SEQ_ACESSO_OPER" )
	@Column( name = "PK_ACESSO_OPER" )
	private Long id;

	@Column( name = "TIPO_OPERADORA", precision = 1 )
	@Enumerated( EnumType.ORDINAL )
	private TipoOperadora tipoOperadora;

	@NotAudited
	@ManyToMany
	@JoinTable( name = "PERFIL_ACESSO_OPER", joinColumns = @JoinColumn( name = "PK_ACESSO_OPER" ), inverseJoinColumns = @JoinColumn( name = "PK_ID_PERFIL" ) )
	private List<Perfil> perfilAcessoOperadora;

	public AcessoOperadora() {}

	public Long getId() {
		return id;
	}

	public void setId( Long id ) {
		this.id = id;
	}

	public TipoOperadora getTipoOperadora() {
		return tipoOperadora;
	}

	public void setTipoOperadora( TipoOperadora tipoOperadora ) {
		this.tipoOperadora = tipoOperadora;
	}

	public List<Perfil> getPerfilAcessoOperadora() {
		if ( perfilAcessoOperadora == null )
			perfilAcessoOperadora = new ArrayList<Perfil>();
		return perfilAcessoOperadora;
	}

	public void setPerfilAcessoOperadora( List<Perfil> perfilAcessoOperadora ) {
		this.perfilAcessoOperadora = perfilAcessoOperadora;
	}

}
