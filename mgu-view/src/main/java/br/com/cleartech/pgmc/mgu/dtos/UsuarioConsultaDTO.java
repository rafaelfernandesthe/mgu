package br.com.cleartech.pgmc.mgu.dtos;

import java.util.ArrayList;
import java.util.List;

import br.com.cleartech.pgmc.mgu.entities.Usuario;
import br.com.cleartech.pgmc.mgu.utils.DTOSearchBase;

public class UsuarioConsultaDTO extends DTOSearchBase {

	private static final long serialVersionUID = -2539799703471445183L;

	private Long id;

	private String nmUsuario;

	private String dcUsername;

	private String dcEmail;

	private String dcCargo;

	private String dcTelefone;

	private String nuCpf;

	@Override
	public List<DTOSearchBase> getVO(List<?> list) {
		List<DTOSearchBase> result = new ArrayList<>();
		if (list != null)
			for (Usuario item : (List<Usuario>) list) {
				result.add(new UsuarioConsultaDTO(item.getId(), item.getNmUsuario(), item.getDcUsername(), item.getDcEmail(), item.getDcCargo(), item
						.getDcTelefone(), item.getNuCpf()));
			}
		return result;
	}

	public UsuarioConsultaDTO() {

	}

	public UsuarioConsultaDTO(Long id,String nmUsuario, String dcUsername, String dcEmail,String dcCargo, String dcTelefone, String nuCpf){
		this.id = id;
		this.nmUsuario = nmUsuario;
		this.dcUsername = dcUsername;
		this.dcEmail = dcEmail;
		this.dcCargo = dcCargo;
		this.dcTelefone = dcTelefone;
		this.nuCpf = nuCpf;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNmUsuario() {
		return nmUsuario;
	}

	public void setNmUsuario(String nmUsuario) {
		this.nmUsuario = nmUsuario;
	}

	public String getDcUsername() {
		return dcUsername;
	}

	public void setDcUsername(String dcUsername) {
		this.dcUsername = dcUsername;
	}

	public String getDcEmail() {
		return dcEmail;
	}

	public void setDcEmail(String dcEmail) {
		this.dcEmail = dcEmail;
	}

	public String getDcCargo() {
		return dcCargo;
	}

	public void setDcCargo(String dcCargo) {
		this.dcCargo = dcCargo;
	}

	public String getDcTelefone() {
		return dcTelefone;
	}

	public void setDcTelefone(String dcTelefone) {
		this.dcTelefone = dcTelefone;
	}

	public String getNuCpf() {
		return nuCpf;
	}

	public void setNuCpf(String nuCpf) {
		this.nuCpf = nuCpf;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}