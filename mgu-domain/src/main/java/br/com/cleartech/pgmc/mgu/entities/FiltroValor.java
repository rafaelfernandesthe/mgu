package br.com.cleartech.pgmc.mgu.entities;

import java.io.Serializable;

public class FiltroValor implements Serializable{

	private static final long serialVersionUID = -3285382799619656107L;

	public Integer filtroId;
	public String valor;

	public FiltroValor(){}

	public FiltroValor(Integer filtroId, String valor){
		this.filtroId = filtroId;
		this.valor = valor;
	}

	public Integer getFiltroId() {
		return filtroId;
	}
	public void setFiltroId(Integer filtroId) {
		this.filtroId = filtroId;
	}
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}



}
