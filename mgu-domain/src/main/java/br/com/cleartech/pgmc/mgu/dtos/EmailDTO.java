package br.com.cleartech.pgmc.mgu.dtos;

public class EmailDTO {

	private String destinatario;
	private String cabecalho;
	private String conteudo;

	public EmailDTO( String destinatario, String cabecalho, String conteudo ) {
		this.destinatario = destinatario;
		this.cabecalho = cabecalho;
		this.conteudo = conteudo;
	}

	public String getDestinatario() {
		return destinatario;
	}

	public void setDestinatario( String destinatario ) {
		this.destinatario = destinatario;
	}

	public String getCabecalho() {
		return cabecalho;
	}

	public void setCabecalho( String cabecalho ) {
		this.cabecalho = cabecalho;
	}

	public String getConteudo() {
		return conteudo;
	}

	public void setConteudo( String conteudo ) {
		this.conteudo = conteudo;
	}

}
