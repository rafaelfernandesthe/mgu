package br.com.cleartech.pgmc.mgu.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
@Entity
@Table(name="tbl_tr_filtro")
public class Filtro implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID")
	@SequenceGenerator(name="SEQ_TR_FILTRO", sequenceName="SEQ_TR_FILTRO_ID", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_TR_FILTRO")
	private Integer id;
	
	@Column(name="nome", length=60, nullable=false)
	private String nome;

	@OneToMany(mappedBy ="filtro", cascade={CascadeType.ALL}, orphanRemoval=true)
	@LazyCollection(LazyCollectionOption.EXTRA)
	private List<FiltroTempoResposta> filtroFuncionalidades = new ArrayList<FiltroTempoResposta>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<FiltroTempoResposta> getFiltroFuncionalidades() {
		return filtroFuncionalidades;
	}

	public void setFiltroFuncionalidades(
			List<FiltroTempoResposta> filtroFuncionalidades) {
		this.filtroFuncionalidades = filtroFuncionalidades;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Filtro other = (Filtro) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Filtro [nome=" + nome + "]";
	}
	
	
}
