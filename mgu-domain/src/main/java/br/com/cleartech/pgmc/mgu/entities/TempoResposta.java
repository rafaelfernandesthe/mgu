package br.com.cleartech.pgmc.mgu.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
@Entity
@Table(name="tbl_tr_temporesposta")
public class TempoResposta implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID")
	@SequenceGenerator(name="SEQ_TR_TEMPO_RESPOSTA", sequenceName="SEQ_TR_TEMPO_RESPOSTA_ID", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_TR_TEMPO_RESPOSTA")
	private Integer id;

	// @NotNull(message="A data de inicio deve ser informada")
	@Column(name="INICIO", nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar inicio;

	// @NotNull(message="A data de fim deve ser informada")
	@Column(name="FIM", nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar fim;

	@ManyToOne
	@JoinColumn(name="funcionalidade_id", referencedColumnName="ID", nullable=false)
	private Funcionalidade funcionalidade;

	@OneToMany(mappedBy ="tempoResposta", cascade={CascadeType.ALL}, orphanRemoval=true)
	@LazyCollection(LazyCollectionOption.EXTRA)
	private List<FiltroTempoResposta> filtrosTempoResposta = new ArrayList<FiltroTempoResposta>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Calendar getInicio() {
		return inicio;
	}

	public void setInicio(Calendar inicio) {
		this.inicio = inicio;
	}

	public Calendar getFim() {
		return fim;
	}

	public void setFim(Calendar fim) {
		this.fim = fim;
	}

	public Funcionalidade getFuncionalidade() {
		return funcionalidade;
	}

	public void setFuncionalidade(Funcionalidade funcionalidade) {
		this.funcionalidade = funcionalidade;
	}

	public List<FiltroTempoResposta> getFiltrosTempoResposta() {
		return filtrosTempoResposta;
	}

	public void setFiltrosTempoResposta(
			List<FiltroTempoResposta> filtrosTempoResposta) {
		this.filtrosTempoResposta = filtrosTempoResposta;
	}

	public void addFiltrosTempoResposta(FiltroTempoResposta filtroTempoResposta) {
		this.filtrosTempoResposta.add(filtroTempoResposta);
	}

}
