package br.com.cleartech.pgmc.mgu.controllers;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.cleartech.pgmc.mgu.entities.FiltroValor;
import br.com.cleartech.pgmc.mgu.entities.GrupoPerfil;
import br.com.cleartech.pgmc.mgu.entities.Prestadora;
import br.com.cleartech.pgmc.mgu.entities.Usuario;
import br.com.cleartech.pgmc.mgu.enums.BloqueioUsuario;
import br.com.cleartech.pgmc.mgu.exceptions.LdapException;
import br.com.cleartech.pgmc.mgu.services.GrupoPerfilService;
import br.com.cleartech.pgmc.mgu.services.PrestadoraService;
import br.com.cleartech.pgmc.mgu.services.TempoRespostaService;
import br.com.cleartech.pgmc.mgu.services.UsuarioService;
import br.com.cleartech.pgmc.mgu.view.dtos.UsuarioConsultaDTO;
import br.com.cleartech.pgmc.mgu.view.utils.MappedViews;
import br.com.cleartech.pgmc.mgu.view.utils.MguUtils;

@Controller
@RequestMapping( "/usuarioConsulta" )
public class UsuarioConsultaController {

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private GrupoPerfilService grupoPerfilService;

	@Autowired
	private PrestadoraService prestadoraService;

	@Autowired
	private TempoRespostaService tempoRespostaService;

	private static final String erroReset = "Ocorreu um erro ao tentar realizar o reset da senha! ";
	private static final String erroExcluir = "Ocorreu um erro ao tentar excluir o Usuário! ";

	@GetMapping
	public String init( Model model, HttpServletRequest request ) {
		model.addAttribute( "usuario", new UsuarioConsultaDTO() );
		model.addAttribute( "grupoPerfisJSON", MguUtils.getVO2JSON( getGrupoPerfis(), "id", "noGrupoPerfil" ) );
		model.addAttribute( "usuariosJSON", MguUtils.getJSON( new ArrayList<Usuario>() ) );
		return MappedViews.USUARIO_CONSULTA.getPath();
	}

	@GetMapping( "/s" )
	public String lista( @ModelAttribute( "usuario" ) UsuarioConsultaDTO usuarioDTO, Model model, HttpServletRequest request ) {
		Calendar inicio = Calendar.getInstance();

		MguUtils.trim( usuarioDTO );
		model.addAttribute( "msgAlertaEmail", request.getParameter( "msgAlertaEmail" ) );
		List<Usuario> lista = usuarioService.find( usuarioDTO.getUsuario(), MguUtils.getUsuarioLogado().getIdPrestadora() );
		model.addAttribute( "usuariosJSON", MguUtils.getJSON( lista ) );

		logarTempoRespostaPesquisaUsuarios( usuarioDTO, inicio );

		return MappedViews.USUARIO_CONSULTA.getPath();
	}

	@ModelAttribute( "grupoPerfis" )
	public List<GrupoPerfil> getGrupoPerfis() {
		return grupoPerfilService.findByPrestadora( MguUtils.getUsuarioLogado().getIdPrestadora() );
	}

	@ModelAttribute( "todosStatus" )
	public BloqueioUsuario[] getStatus() {
		return BloqueioUsuario.values();
	}

	@GetMapping( "/grupoPerfis/{idUsuario}" )
	@ResponseBody
	public List<GrupoPerfil> carregaGrupos( @PathVariable Long idUsuario ) {
		return grupoPerfilService.findByUsuario( idUsuario , MguUtils.getUsuarioLogado().getIdPrestadora());
	}

	@RequestMapping( method = RequestMethod.DELETE, value = "/excluir/{idUsuario}" )
	@ResponseBody
	public String excluir( @PathVariable Long idUsuario ) {
		try {
			usuarioService.excluir( idUsuario );
		} catch ( Exception e ) {
			e.printStackTrace();
			return erroExcluir + e.getMessage();
		}

		return "Usuário deletado com sucesso!";
	}

	@RequestMapping( method = RequestMethod.PUT, value = "/resetar/{idUsuario}" )
	@ResponseBody
	public String resetar( @PathVariable Long idUsuario ) {

		try {
			usuarioService.resetar( idUsuario, MguUtils.getUsuarioLogado().getDcUsername(), true );
		} catch ( LdapException e ) {
			e.printStackTrace();
			return erroReset + "Usuário não existe no LDAP!";
		} catch ( Exception e ) {
			e.printStackTrace();
			return erroReset + e.getMessage();
		}

		return "Reset da senha realizado com sucesso!";
	}

	@RequestMapping( method = RequestMethod.PUT, value = "/bloquear/{idUsuario}" )
	@ResponseBody
	public String bloquear( @PathVariable Long idUsuario ) {
		try {
			usuarioService.bloquear( usuarioService.find( idUsuario ), false );
		} catch ( Exception e ) {
			e.printStackTrace();
			return "Ocorreu um erro ao tentar bloquear o Usuário! " + e.getMessage();
		}

		return "Usuário bloqueado com sucesso!";
	}

	@RequestMapping( method = RequestMethod.PUT, value = "/desbloquear/{idUsuario}" )
	@ResponseBody
	public String desbloquear( @PathVariable Long idUsuario ) {
		try {
			Prestadora prestadoraLogada = prestadoraService.findById( MguUtils.getUsuarioLogado().getIdPrestadora() );
			usuarioService.desbloquear( usuarioService.find( idUsuario ), prestadoraLogada );
		} catch ( Exception e ) {
			e.printStackTrace();
			return "Ocorreu um erro ao tentar desbloquear o Usuário! " + e.getMessage();
		}

		return "Usuário desbloqueado com sucesso!";
	}

	private void logarTempoRespostaPesquisaUsuarios( UsuarioConsultaDTO usuario, Calendar inicio ) {

		final Integer SISTEMA_MGU = 2;
		final Integer FUNCIONALIDADE_LISTA_USUARIOS = 3;
		final Integer FILTRO_NOME_USUARIO = 4;
		final Integer FILTRO_USUARIO_ACESSO = 5;
		final Integer FILTRO_EMAIL = 6;
		final Integer FILTRO_CARGO = 7;
		final Integer FILTRO_TELEFONE = 8;
		final Integer FILTRO_CPF = 9;
		final Integer FILTRO_GRUPO_PERFIL = 10;

		List<FiltroValor> filtroValores = new ArrayList<FiltroValor>();

		if ( usuario.getNmUsuario() != null && !"".equals( usuario.getNmUsuario() ) ) {
			FiltroValor filtroValor = new FiltroValor();
			filtroValor.setFiltroId( FILTRO_NOME_USUARIO );
			filtroValor.setValor( usuario.getNmUsuario() );
			filtroValores.add( filtroValor );
		}

		if ( usuario.getDcUsername() != null && !"".equals( usuario.getDcUsername() ) ) {
			FiltroValor filtroValor = new FiltroValor();
			filtroValor.setFiltroId( FILTRO_USUARIO_ACESSO );
			filtroValor.setValor( usuario.getDcUsername() );
			filtroValores.add( filtroValor );
		}

		if ( usuario.getDcEmail() != null && !"".equals( usuario.getDcEmail() ) ) {
			FiltroValor filtroValor = new FiltroValor();
			filtroValor.setFiltroId( FILTRO_EMAIL );
			filtroValor.setValor( usuario.getDcEmail() );
			filtroValores.add( filtroValor );
		}

		if ( usuario.getDcCargo() != null && !"".equals( usuario.getDcCargo() ) ) {
			FiltroValor filtroValor = new FiltroValor();
			filtroValor.setFiltroId( FILTRO_CARGO );
			filtroValor.setValor( usuario.getDcCargo() );
			filtroValores.add( filtroValor );
		}

		if ( usuario.getDcTelefone() != null && !"".equals( usuario.getDcTelefone() ) ) {
			FiltroValor filtroValor = new FiltroValor();
			filtroValor.setFiltroId( FILTRO_TELEFONE );
			filtroValor.setValor( usuario.getDcTelefone() );
			filtroValores.add( filtroValor );
		}

		if ( usuario.getNuCpf() != null && !"".equals( usuario.getNuCpf() ) ) {
			FiltroValor filtroValor = new FiltroValor();
			filtroValor.setFiltroId( FILTRO_CPF );
			filtroValor.setValor( usuario.getNuCpf() );
			filtroValores.add( filtroValor );
		}

		if ( usuario.getGrupoPerfilFiltro() != null && usuario.getGrupoPerfilFiltro() > 0 ) {
			FiltroValor filtroValor = new FiltroValor();
			filtroValor.setFiltroId( FILTRO_GRUPO_PERFIL );
			filtroValor.setValor( String.valueOf( usuario.getGrupoPerfilFiltro() ) );
			filtroValores.add( filtroValor );
		}

		Calendar fim = Calendar.getInstance();
		tempoRespostaService.logger( SISTEMA_MGU, FUNCIONALIDADE_LISTA_USUARIOS, inicio, fim, filtroValores );

	}

}
