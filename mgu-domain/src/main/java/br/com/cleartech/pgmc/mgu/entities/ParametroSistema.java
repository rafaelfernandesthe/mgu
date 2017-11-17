package br.com.cleartech.pgmc.mgu.entities;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "PARAMETRO_SISTEMA")
@SequenceGenerator(name = "SEQ_PARAMETRO_SISTEMA", sequenceName = "SEQ_PARAMETRO_SISTEMA", initialValue=1, allocationSize=1)
public class ParametroSistema implements Serializable {

	private static final long serialVersionUID = 8954997586706344602L;	
	
	@Id
	@GeneratedValue(generator = "SEQ_PARAMETRO_SISTEMA")
	@Column(name="PK_PARAMETRO_SISTEMA")
	private Long id;

	@Column(name="PRAZO_EXPIRAR_SENHA")
	private Integer prazoExpirarSenha;

	@Column(name="BLOQUEAR_INATIVIDADE")
	private Integer bloquearInatividade;
	
	@Column(name="QTD_ERRAR_SENHA")
	private Integer qtdErrarSenha;
	
	@Column(name="FL_PRAZO_EXPIRAR_SENHA", precision=1)
	private Integer flPrazoExpirarSenha = 1;
	
	@Column(name="FL_BLOQUEAR_INATIVIDADE", precision=1)
	private Integer flBloquearInatividade = 1;
	
	@Column(name="FL_QTD_ERRAR_SENHA", precision=1)
	private Integer flQtdErrarSenha = 1;
	
	@Column(name="FL_ACESSO_SIMULTANEO", precision=1)
	private Integer flAcessoSimultaneo = 1;
	
	@OneToOne(fetch=FetchType.EAGER)  
	@JoinColumn(name="ID_GRUPO_PRESTADORA")
	private GrupoPrestadora grupoPrestadora = new GrupoPrestadora();
	
	
	public ParametroSistema() {
		super();
	}

	public ParametroSistema(Integer prazoExpirarSenha,	Integer bloquearInatividade, GrupoPrestadora grupoPrestadora) {
		this.prazoExpirarSenha = prazoExpirarSenha;
		this.bloquearInatividade = bloquearInatividade;
		this.grupoPrestadora = grupoPrestadora;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getPrazoExpirarSenha() {
		return prazoExpirarSenha;
	}

	public void setPrazoExpirarSenha(Integer prazoExpirarSenha) {
		this.prazoExpirarSenha = prazoExpirarSenha;
	}

	public Integer getBloquearInatividade() {
		return bloquearInatividade;
	}

	public void setBloquearInatividade(Integer bloquearInatividade) {
		this.bloquearInatividade = bloquearInatividade;
	}

	public GrupoPrestadora getGrupoPrestadora() {
		return grupoPrestadora;
	}

	public void setGrupoPrestadora(GrupoPrestadora grupoPrestadora) {
		this.grupoPrestadora = grupoPrestadora;
	}

	public Integer getQtdErrarSenha() {
		return qtdErrarSenha;
	}

	public void setQtdErrarSenha(Integer qtdErrarSenha) {
		this.qtdErrarSenha = qtdErrarSenha;
	}

	public Integer getFlPrazoExpirarSenha() {
		return flPrazoExpirarSenha;
	}

	public void setFlPrazoExpirarSenha(Integer flPrazoExpirarSenha) {
		this.flPrazoExpirarSenha = flPrazoExpirarSenha;
	}

	public Integer getFlBloquearInatividade() {
		return flBloquearInatividade;
	}

	public void setFlBloquearInatividade(Integer flBloquearInatividade) {
		this.flBloquearInatividade = flBloquearInatividade;
	}

	public Integer getFlQtdErrarSenha() {
		return flQtdErrarSenha;
	}

	public void setFlQtdErrarSenha(Integer flQtdErrarSenha) {
		this.flQtdErrarSenha = flQtdErrarSenha;
	}

	public Integer getFlAcessoSimultaneo() {
		return flAcessoSimultaneo;
	}

	public void setFlAcessoSimultaneo(Integer flAcessoSimultaneo) {
		this.flAcessoSimultaneo = flAcessoSimultaneo;
	}
	
}