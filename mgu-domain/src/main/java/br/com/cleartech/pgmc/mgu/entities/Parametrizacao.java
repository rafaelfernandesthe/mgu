package br.com.cleartech.pgmc.mgu.entities;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * The persistent class for the PARAMETRIZACAO database table.
 * 
 */
@Entity
@Table(name = "PARAMETRIZACAO")
public class Parametrizacao implements Serializable {

	private static final long serialVersionUID = -5537285541687645242L;

	private Long cdParametro;
	private String dcParametro;
	private String vlParametro;

	public Parametrizacao() {
	}

	/**
	 * @param cdParametro
	 */
	public Parametrizacao(Long cdParametro) {
		this.cdParametro = cdParametro;
	}

	/**
	 * @param cdParametro
	 * @param dcParametro
	 * @param idTipoParametro
	 * @param vlParametro
	 */
	public Parametrizacao(Long cdParametro, String dcParametro,	String vlParametro) {
		this.cdParametro = cdParametro;
		this.dcParametro = dcParametro;
		this.vlParametro = vlParametro;
	}

	@Id
	@SequenceGenerator(name="PARAMETRIZACAO", sequenceName="SEQ_PARAMETRIZACAO")
	@Column(name = "CD_PARAMETRO", unique = true, nullable = false, precision = 22)
	public long getCdParametro() {
		return this.cdParametro;
	}

	public void setCdParametro(Long cdParametro) {
		this.cdParametro = cdParametro;
	}

	@Column(name = "DC_PARAMETRO", nullable = false, length = 100)
	public String getDcParametro() {
		return this.dcParametro;
	}

	public void setDcParametro(String dcParametro) {
		this.dcParametro = dcParametro;
	}

	@Column(name = "VL_PARAMETRO", nullable = false, length = 250)
	public String getVlParametro() {
		return this.vlParametro;
	}

	public void setVlParametro(String vlParametro) {
		this.vlParametro = vlParametro;
	}
}