package br.com.cleartech.pgmc.mgu.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
	@OneToMany( mappedBy = "prestadora" )
	private List<PrestadoraXUsuario> prestadoraXUsuarios;

	@OneToMany( mappedBy = "prestadora" )
	private List<Delegado> delegados;

	@OneToMany( mappedBy = "prestadora", cascade = CascadeType.ALL )
	private List<PrestadoraModulo> modulos;

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

	public List<PrestadoraXUsuario> getPrestadoraXUsuarios() {
		if ( prestadoraXUsuarios == null ) {
			prestadoraXUsuarios = new ArrayList<PrestadoraXUsuario>();
		}
		return prestadoraXUsuarios;
	}

	public void setPrestadoraXUsuarios( List<PrestadoraXUsuario> prestadoraXUsuarios ) {
		this.prestadoraXUsuarios = prestadoraXUsuarios;
	}

	public List<PrestadoraModulo> getModulos() {
		if ( modulos == null ) {
			modulos = new ArrayList<PrestadoraModulo>();
		}
		return modulos;
	}

	public void setModulos( List<PrestadoraModulo> modulos ) {
		this.modulos = modulos;
	}

	public List<Delegado> getDelegados() {
		return delegados;
	}

	public void setDelegados( List<Delegado> delegados ) {
		this.delegados = delegados;
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

	@Override
	public String toString() {
		return "Prestadora [id=" + id + ", noPrestadora=" + noPrestadora + "]";
	}

}