package br.com.cleartech.pgmc.mgu.entities;


import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;


/**
 * The persistent class for the GRUPO_PRESTADORA database table.
 * 
 */
@Entity
@Table(name="GRUPO_PRESTADORA")
public class GrupoPrestadora implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8064307623388211539L;

	@Id
	@Column(name="ID_GRUPO_PRESTADORA")
	private Long id;

	@Column(name="DC_GRUPO_PRESTADORA")
	private String noGrupoPrestadora;

	//bi-directional many-to-one association to Prestadora
	@OneToMany(mappedBy="grupoPrestadora")
	private List<Prestadora> prestadoras;

    public GrupoPrestadora() {
    }

    @XmlElement(name="id_grupo_prestadora")
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@XmlElement(name="nome_grupo_prestadora")
	public String getNoGrupoPrestadora() {
		return this.noGrupoPrestadora;
	}

	public void setNoGrupoPrestadora(String noGrupoPrestadora) {
		this.noGrupoPrestadora = noGrupoPrestadora;
	}

	@XmlElementWrapper(name="prestadoras")
	@XmlElement(name="prestadora")
	public List<Prestadora> getPrestadoras() {
		return this.prestadoras;
	}

	public void setPrestadoras(List<Prestadora> prestadoras) {
		this.prestadoras = prestadoras;
	}
	
}