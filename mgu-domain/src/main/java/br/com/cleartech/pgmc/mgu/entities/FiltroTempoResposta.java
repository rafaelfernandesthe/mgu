package br.com.cleartech.pgmc.mgu.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
@Entity
@Table(name="tbl_tr_filtro_tr")
public class FiltroTempoResposta implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID")
	@SequenceGenerator(name="SEQ_TR_FILTRO_TR", sequenceName="SEQ_TR_FILTRO_TR_ID", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_TR_FILTRO_TR")
	private Integer id;

	@Column(name="valor", length=60, nullable=false)
	private String valor;

	// @NotNull(message="O funcionario deve ser informado.")
	@ManyToOne
	@JoinColumn(name="filtro_id", referencedColumnName="ID", nullable=false)
	private Filtro filtro;

	// @NotNull(message="O projeto deve ser informado.")
	@ManyToOne
	@JoinColumn(name="tempoResposta_id", referencedColumnName="ID", nullable=false)
	private TempoResposta tempoResposta;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public Filtro getFiltro() {
		return filtro;
	}

	public void setFiltro(Filtro filtro) {
		this.filtro = filtro;
	}

	public TempoResposta getTempoResposta() {
		return tempoResposta;
	}

	public void setTempoResposta(TempoResposta tempoResposta) {
		this.tempoResposta = tempoResposta;
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
		FiltroTempoResposta other = (FiltroTempoResposta) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "FiltroFuncionalidade [valor=" + valor + "]";
	}



}
