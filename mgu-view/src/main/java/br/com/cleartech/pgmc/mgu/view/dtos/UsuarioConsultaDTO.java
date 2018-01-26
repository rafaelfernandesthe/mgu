package br.com.cleartech.pgmc.mgu.view.dtos;

import java.util.ArrayList;
import java.util.List;

import br.com.cleartech.pgmc.mgu.entities.GrupoPerfil;
import br.com.cleartech.pgmc.mgu.entities.Usuario;
import br.com.cleartech.pgmc.mgu.entities.UsuarioXGrupoPerfil;
import br.com.cleartech.pgmc.mgu.enums.BloqueioUsuario;

public class UsuarioConsultaDTO extends DTOSearchBase {

	private static final long serialVersionUID = -2539799703471445183L;

	private Long id;

	private String nmUsuario;

	private String dcUsername;

	private String dcEmail;

	private String dcCargo;

	private String dcTelefone;

	private String nuCpf;

	private Integer grupoPerfilFiltro;

	private Integer statusFiltro;

	private List<GrupoPerfil> grupoPerfis;

	@Override
	public List<DTOSearchBase> getVO( List<?> list ) {
		List<DTOSearchBase> result = new ArrayList<>();
		if ( list != null )
			for ( Usuario item : (List<Usuario>) list ) {
				result.add( new UsuarioConsultaDTO( item.getId(), item.getNmUsuario(), item.getDcUsername(), item.getDcEmail(), item.getDcCargo(), item.getDcTelefone(), item.getNuCpf() ) );
			}
		return result;
	}

	public UsuarioConsultaDTO() {

	}

	public UsuarioConsultaDTO( Long id, String nmUsuario, String dcUsername, String dcEmail, String dcCargo, String dcTelefone, String nuCpf ) {
		this.id = id;
		this.nmUsuario = nmUsuario;
		this.dcUsername = dcUsername;
		this.dcEmail = dcEmail;
		this.dcCargo = dcCargo;
		this.dcTelefone = dcTelefone;
		this.nuCpf = nuCpf;
	}

	public Usuario getUsuario() {
		Usuario usuario = new Usuario();
		usuario.setNmUsuario( this.getNmUsuario() );
		usuario.setDcUsername( this.getDcUsername() );
		usuario.setDcEmail( this.getDcEmail() );
		usuario.setDcCargo( this.getDcCargo() );
		usuario.setDcTelefone( this.getDcTelefone() );
		usuario.setNuCpf( this.getNuCpf() );
		if ( this.getGrupoPerfilFiltro() != null ) {
			usuario.getUsuarioXGrupoPerfils().add( new UsuarioXGrupoPerfil( new GrupoPerfil( grupoPerfilFiltro.longValue() ), usuario ) );
		}
		usuario.setFlBloqueio( BloqueioUsuario.getByInt( this.getStatusFiltro() ) );
		return usuario;
	}

	public Long getId() {
		return id;
	}

	public void setId( Long id ) {
		this.id = id;
	}

	public String getNmUsuario() {
		return nmUsuario;
	}

	public void setNmUsuario( String nmUsuario ) {
		this.nmUsuario = nmUsuario;
	}

	public String getDcUsername() {
		return dcUsername;
	}

	public void setDcUsername( String dcUsername ) {
		this.dcUsername = dcUsername;
	}

	public String getDcEmail() {
		return dcEmail;
	}

	public void setDcEmail( String dcEmail ) {
		this.dcEmail = dcEmail;
	}

	public String getDcCargo() {
		return dcCargo;
	}

	public void setDcCargo( String dcCargo ) {
		this.dcCargo = dcCargo;
	}

	public String getDcTelefone() {
		return dcTelefone;
	}

	public void setDcTelefone( String dcTelefone ) {
		this.dcTelefone = dcTelefone;
	}

	public String getNuCpf() {
		return nuCpf;
	}

	public void setNuCpf( String nuCpf ) {
		this.nuCpf = nuCpf;
	}

	public Integer getGrupoPerfilFiltro() {
		return grupoPerfilFiltro;
	}

	public void setGrupoPerfilFiltro( Integer grupoPerfilFiltro ) {
		this.grupoPerfilFiltro = grupoPerfilFiltro;
	}

	public Integer getStatusFiltro() {
		return statusFiltro;
	}

	public void setStatusFiltro( Integer statusFiltro ) {
		this.statusFiltro = statusFiltro;
	}

	public List<GrupoPerfil> getGrupoPerfis() {
		return grupoPerfis;
	}

	public void setGrupoPerfis( List<GrupoPerfil> grupoPerfis ) {
		this.grupoPerfis = grupoPerfis;
	}

}