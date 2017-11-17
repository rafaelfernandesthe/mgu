package br.com.cleartech.pgmc.mgu.entities;


import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;


@Audited
@Entity
@Table(name="NIVEL_ESCALONAMENTO")
@SequenceGenerator(name = "SEQ_NIVEL_ESCALONAMENTO", sequenceName = "SEQ_NIVEL_ESCALONAMENTO", initialValue=1, allocationSize=1)
public class NivelEscalonamento implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -879065387746306070L;

	@Id
	@GeneratedValue(generator = "SEQ_NIVEL_ESCALONAMENTO")
	@Column(name="ID_NIVEL_ESCALONAMENTO")
	private Long id;

	@Column(name="DC_NIVEL_ESCALONAMENTO")
	private String dcNivelEscalonamento;

	@Column(name="NU_NIVEL_ESCALONAMENTO")
	private Long nuNivelEscalonamento;

	//bi-directional many-to-one association to Usuario
	@NotAudited
	@OneToMany(mappedBy="nivelEscalonamento")
	private Set<Usuario> usuarios;

    public NivelEscalonamento() {
    }

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDcNivelEscalonamento() {
		return this.dcNivelEscalonamento;
	}

	public void setDcNivelEscalonamento(String dcNivelEscalonamento) {
		this.dcNivelEscalonamento = dcNivelEscalonamento;
	}

	public Long getNuNivelEscalonamento() {
		return this.nuNivelEscalonamento;
	}

	public void setNuNivelEscalonamento(Long nuNivelEscalonamento) {
		this.nuNivelEscalonamento = nuNivelEscalonamento;
	}

	public Set<Usuario> getUsuarios() {
		return this.usuarios;
	}

	public void setUsuarios(Set<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	
}