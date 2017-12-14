package br.com.cleartech.pgmc.mgu.dtos;

public class PerfilDTO {

	private Long idPerfil;
	private String dcPerfil;
	private Long idOperadora;
	private String noRazaoSocial;

	public PerfilDTO( Long idPerfil, String dcPerfil, Long idOperadora, String noRazaoSocial ) {
		super();
		this.idPerfil = idPerfil;
		this.dcPerfil = dcPerfil;
		this.idOperadora = idOperadora;
		this.noRazaoSocial = noRazaoSocial;
	}

	public Long getIdPerfil() {
		return idPerfil;
	}

	public void setIdPerfil( Long idPerfil ) {
		this.idPerfil = idPerfil;
	}

	public String getDcPerfil() {
		return dcPerfil;
	}

	public void setDcPErfil( String dcPErfil ) {
		this.dcPerfil = dcPErfil;
	}

	public Long getIdOperadora() {
		return idOperadora;
	}

	public void setIdOperadora( Long idOperadora ) {
		this.idOperadora = idOperadora;
	}

	public String getNoRazaoSocial() {
		return noRazaoSocial;
	}

	public void setNoRazaoSocial( String noRazaoSocial ) {
		this.noRazaoSocial = noRazaoSocial;
	}
}