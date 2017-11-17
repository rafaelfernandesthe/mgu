package br.com.cleartech.pgmc.mgu.entities;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 * The persistent class for the DELEGADO database table.
 * 
 */
//@Audited
@Entity
@Table(name="DELEGADO")
@SequenceGenerator(name = "SEQ_DELEGADO", sequenceName = "SEQ_DELEGADO", initialValue=1, allocationSize=1)
public class Delegado implements Serializable {

	private static final long serialVersionUID = 7021868911632085284L;

	@Id
	@GeneratedValue(generator = "SEQ_DELEGADO")
	@Column(name="PK_ID_DELEGADO")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="PK_ID_PRESTADORA_MASTER")
	private Prestadora prestadora;

    @ManyToOne
	@JoinColumn(name="PK_ID_USUARIO_MASTER")
	private Usuario usuarioMaster;

    @ManyToOne
	@JoinColumn(name="PK_ID_USUARIO_COMUM")
	private Usuario usuarioComum;

    public Delegado() {
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Prestadora getPrestadora() {
		return this.prestadora;
	}

	public void setPrestadora(Prestadora prestadora) {
		this.prestadora = prestadora;
	}

	public Usuario getUsuarioMaster() {
		return this.usuarioMaster;
	}

	public void setUsuarioMaster(Usuario usuarioMaster) {
		this.usuarioMaster = usuarioMaster;
	}
	
	public Usuario getUsuarioComum() {
		return this.usuarioComum;
	}

	public void setUsuarioComum(Usuario usuarioComum) {
		this.usuarioComum = usuarioComum;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (getId() == null) {
			if (other.getId() != null)
				return false;
		} else if (!getId().equals(other.getId()))
			return false;
		return true;
	}
	
}