package br.com.cleartech.pgmc.mgu.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * The persistent class for the PRESTADORA database table.
 * 
 */
@Entity
@Table( name = "PRESTADORA" )
@XmlRootElement( name = "prestadora" )
public class Prestadora implements Serializable {

	private static final long serialVersionUID = 4039603391191663774L;

	@Id
	@Column( name = "ID_PRESTADORA" )
	private Long id;

	@Column( name = "NO_NOME_FANTASIA" )
	private String noPrestadora;

	// bi-directional many-to-one association to GrupoPrestadora
	@ManyToOne
	@JoinColumn( name = "ID_GRUPO_PRESTADORA" )
	private GrupoPrestadora grupoPrestadora;

	// bi-directional many-to-one association to PrestadoraXUsuario
	@ManyToMany
	@JoinTable( name = "USUARIO_PRESTADORA", joinColumns = @JoinColumn( name = "FK_ID_PRESTADORA" ), inverseJoinColumns = @JoinColumn( name = "FK_ID_USUARIO" ) )
	private List<Usuario> usuarios;

	@OneToMany( mappedBy = "prestadora" )
	private List<Delegado> delegados = new ArrayList<Delegado>();

	@ManyToMany
	@JoinTable( name = "PRESTADORA_MODULO", joinColumns = @JoinColumn( name = "ID_PRESTADORA" ), inverseJoinColumns = @JoinColumn( name = "ID_MODULO" ) )
	private List<Modulo> modulos;

	public Prestadora() {}

	public Prestadora( Long id ) {
		this.id = id;
	}

	@XmlElement( name = "id_prestadora" )
	public Long getId() {
		return this.id;
	}

	public void setId( Long id ) {
		this.id = id;
	}

	@XmlElement( name = "nome_prestadora" )
	public String getNoPrestadora() {
		return this.noPrestadora;
	}

	public void setNoPrestadora( String noPrestadora ) {
		this.noPrestadora = noPrestadora;
	}

	public GrupoPrestadora getGrupoPrestadora() {
		return this.grupoPrestadora;
	}

	public void setGrupoPrestadora( GrupoPrestadora grupoPrestadora ) {
		this.grupoPrestadora = grupoPrestadora;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios( List<Usuario> usuarios ) {
		this.usuarios = usuarios;
	}

	public List<Modulo> getModulos() {
		return modulos;
	}

	public void setModulos( List<Modulo> modulos ) {
		this.modulos = modulos;
	}

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
		Prestadora other = (Prestadora) obj;
		if ( id == null ) {
			if ( other.id != null )
				return false;
		} else if ( !id.equals( other.id ) )
			return false;
		return true;
	}

}