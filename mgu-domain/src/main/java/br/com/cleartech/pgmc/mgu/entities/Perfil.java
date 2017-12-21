package br.com.cleartech.pgmc.mgu.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlElement;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

/**
 * The persistent class for the PERFIL database table.
 * 
 */
@Audited
@Entity
@Table( name = "PERFIL" )
@SequenceGenerator( name = "SEQ_PERFIL", sequenceName = "SEQ_PERFIL", initialValue = 1, allocationSize = 1 )
public class Perfil implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8303361761007768685L;

	@Id
	@GeneratedValue( generator = "SEQ_PERFIL" )
	@Column( name = "PK_ID_PERFIL" )
	@XmlElement( name = "id" )
	private Long id;

	@Column( name = "DC_PERFIL" )
	@XmlElement( name = "nome" )
	private String dcPerfil;

	@Column( name = "DC_DESCRICAO" )
	private String dcDescricao;

	@NotAudited
	@ManyToMany
	@JoinTable( name = "GRUPO_PERFIL_X_PERFIL", joinColumns = @JoinColumn( name = "PK_ID_PERFIL" ), inverseJoinColumns = @JoinColumn( name = "PK_ID_GRUPO_PERFIL" ) )
	private List<GrupoPerfil> grupoPerfis;

	// @NotAudited
	// @ManyToMany
	// @Fetch( FetchMode.SUBSELECT )
	// @JoinTable( name = "ACESSO_OPER", joinColumns = @JoinColumn( name =
	// "PK_ID_PERFIL" ), inverseJoinColumns = @JoinColumn( name =
	// "PK_ACESSO_OPER" ) )
	// private List<AcessoOperadora> acessosOperadora;

	@NotAudited
	@OneToMany( mappedBy = "perfil", cascade = CascadeType.ALL )
	@LazyCollection( LazyCollectionOption.FALSE )
	private List<PerfilAcessoOperadora> perfilAcessoOperadoras;

	@OrderBy
	@ManyToOne
	@JoinColumn( name = "FK_ID_SISTEMA" )
	private Sistema sistema;

	@NotAudited
	@OneToMany( mappedBy = "perfil", fetch = FetchType.LAZY )
	private List<Usuario> usuarios = new ArrayList<Usuario>();

	@Transient
	// Utilizado no POST /sistema
	@XmlElement( name = "perfil" )
	private List<Perfil> listaPerfil;

	public Long getId() {
		return this.id;
	}

	public void setId( Long id ) {
		this.id = id;
	}

	public String getDcDescricao() {
		return dcDescricao;
	}

	public void setDcDescricao( String dcDescricao ) {
		this.dcDescricao = dcDescricao;
	}

	public String getDcPerfil() {
		return this.dcPerfil;
	}

	public void setDcPerfil( String dcPerfil ) {
		this.dcPerfil = dcPerfil;
	}

	public Sistema getSistema() {
		return this.sistema;
	}

	public void setSistema( Sistema sistema ) {
		this.sistema = sistema;
	}

	public List<GrupoPerfil> getGrupoPerfis() {
		return grupoPerfis;
	}

	public void setGrupoPerfis( List<GrupoPerfil> grupoPerfis ) {
		this.grupoPerfis = grupoPerfis;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios( List<Usuario> usuarios ) {
		this.usuarios = usuarios;
	}

	public List<PerfilAcessoOperadora> getPerfilAcessoOperadoras() {
		if ( perfilAcessoOperadoras == null )
			perfilAcessoOperadoras = new ArrayList<PerfilAcessoOperadora>();
		return perfilAcessoOperadoras;
	}

	public void setPerfilAcessoOperadoras( List<PerfilAcessoOperadora> perfilAcessoOperadoras ) {
		this.perfilAcessoOperadoras = perfilAcessoOperadoras;
	}

	public List<Perfil> getListaPerfil() {
		return listaPerfil;
	}

	public void setListaPerfil( List<Perfil> listaPerfil ) {
		this.listaPerfil = listaPerfil;
	}

	public void addPerfil( Perfil p ) {
		this.listaPerfil.add( p );
	}

	// public List<AcessoOperadora> getAcessosOperadora() {
	// if ( acessosOperadora == null ) {
	// return new ArrayList<AcessoOperadora>();
	// }
	// return acessosOperadora;
	// }
	//
	// public void setAcessosOperadora( List<AcessoOperadora> acessosOperadora )
	// {
	// this.acessosOperadora = acessosOperadora;
	// }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ( ( id == null ) ? 0 : id.hashCode() );
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
		Perfil other = (Perfil) obj;
		if ( id == null ) {
			if ( other.id != null )
				return false;
		} else if ( !id.equals( other.id ) )
			return false;
		return true;
	}

}