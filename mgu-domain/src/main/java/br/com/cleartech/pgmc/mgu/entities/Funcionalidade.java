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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
@Entity
@Table(name="tbl_tr_funcionalidade")
public class Funcionalidade implements Serializable {

	private static final long serialVersionUID = 1L;


	@Id
	@Column(name="ID")
	@SequenceGenerator(name="SEQ_TR_FUNCIONALIDADE", sequenceName="SEQ_TR_FUNCIONALIDADE_ID", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_TR_FUNCIONALIDADE")
	private Integer id;

	@Column(name="nome", length=60, nullable=false)
	private String nome;
	
	@ManyToOne
	@JoinColumn(name="sistema_id", referencedColumnName="ID", nullable=false)
	private SistemaTR sistema;
	
	@OneToMany(mappedBy ="funcionalidade", cascade={CascadeType.ALL}, orphanRemoval=true)
	@LazyCollection(LazyCollectionOption.EXTRA)
	private List<TempoResposta> tempoRespostas = new ArrayList<TempoResposta>();
	
	public Funcionalidade() {
	}
	
	public Funcionalidade(Integer id) {
		this.id = id;
	}
	
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

	public SistemaTR getSistema() {
		return sistema;
	}

	public void setSistema(SistemaTR sistema) {
		this.sistema = sistema;
	}

	public List<TempoResposta> getTempoRespostas() {
		return tempoRespostas;
	}

	public void setTempoRespostas(List<TempoResposta> tempoRespostas) {
		this.tempoRespostas = tempoRespostas;
	}

	public void addTempoRespostas(TempoResposta tempoResposta) {
		this.tempoRespostas.add(tempoResposta);
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
		Funcionalidade other = (Funcionalidade) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Funcionalidade [nome=" + nome + "]";
	}

}
