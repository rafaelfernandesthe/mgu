package br.com.cleartech.pgmc.mgu.integration.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cleartech.pgmc.mgu.dtos.PerfilDTO;
import br.com.cleartech.pgmc.mgu.entities.Delegado;
import br.com.cleartech.pgmc.mgu.entities.ParametroSistema;
import br.com.cleartech.pgmc.mgu.entities.Perfil;
import br.com.cleartech.pgmc.mgu.entities.Prestadora;
import br.com.cleartech.pgmc.mgu.entities.Usuario;
import br.com.cleartech.pgmc.mgu.enums.BloqueioUsuario;
import br.com.cleartech.pgmc.mgu.enums.CodigoMensagem;
import br.com.cleartech.pgmc.mgu.integration.utils.ResponseUtils;
import br.com.cleartech.pgmc.mgu.integration.ws.responses.DadosResponse;
import br.com.cleartech.pgmc.mgu.integration.ws.responses.GrupoPrestadoraResponse;
import br.com.cleartech.pgmc.mgu.integration.ws.responses.MguLoginResponse;
import br.com.cleartech.pgmc.mgu.integration.ws.responses.PerfilResponse;
import br.com.cleartech.pgmc.mgu.integration.ws.responses.PrestadoraResponse;
import br.com.cleartech.pgmc.mgu.integration.ws.responses.UsuarioResponse;
import br.com.cleartech.pgmc.mgu.services.AcessoSimultaneoService;
import br.com.cleartech.pgmc.mgu.services.DelegadoService;
import br.com.cleartech.pgmc.mgu.services.LdapService;
import br.com.cleartech.pgmc.mgu.services.ParametroSistemaService;
import br.com.cleartech.pgmc.mgu.services.PerfilService;
import br.com.cleartech.pgmc.mgu.services.PrestadoraPMSMercadoService;
import br.com.cleartech.pgmc.mgu.services.UsuarioService;
import br.com.cleartech.pgmc.mgu.utils.SistemaUtils;
import br.com.cleartech.pgmc.mgu.utils.XmlUtils;

@Component
public class LoginControllerService {

	private static final Logger logger = LoggerFactory.getLogger( LoginControllerService.class );

	private static final long ID_PMS_VIVO = 1;
	private static final long ID_PMS_TIM = 2;
	private static final long ID_PMS_OI = 3;
	private static final long ID_PMS_TELMEX = 4;
	private static final long ID_PMS_CTBC = 5;
	private static final long ID_PMS_SERCOMTEL = 6;
	private static final long ID_PMS_COPEL = 7;
	private static final long ID_PMS_ABRT = 8;

	private static final List<Long> IDS_PMS = Arrays.asList( ID_PMS_VIVO, ID_PMS_TIM, ID_PMS_OI, ID_PMS_TELMEX, ID_PMS_CTBC, ID_PMS_SERCOMTEL, ID_PMS_COPEL, ID_PMS_ABRT );

	@Autowired
	private PerfilService perfilService;

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private LdapService ldapService;

	@Autowired
	private ParametroSistemaService parametroSistemaService;

	@Autowired
	private AcessoSimultaneoService acessoSimultaneoService;

	@Autowired
	private PrestadoraPMSMercadoService prestadoraPMSMercadoService;

	@Autowired
	private DelegadoService delegadoService;

	@Autowired
	private LoginControllerService loginControllerService;

	public Object processarLoginIntegracao( Usuario usuarioRequest ) throws Exception {
		Boolean valido = ldapService.existeUsuario( usuarioRequest.getDcUsername(), usuarioRequest.getDcSenha() );
		String sistemaUsuario = usuarioRequest.getSistema();
		Usuario usuario = usuarioService.findByUsername( usuarioRequest.getDcUsername() );

		// USUARIO VALIDO LDAP
		if ( valido && usuario != null ) {

			Object mguResponse = responseValidaDynamicsECredenciamento( usuarioRequest, sistemaUsuario, usuario );
			if ( mguResponse != null ) {
				return mguResponse;
			}

			if ( usuario.getFlBloqueio().equals( BloqueioUsuario.BLOQUEADO_NAO ) ) {
				List<PerfilDTO> perfis = perfilService.findByUsernameAndSistema( usuario.getDcUsername(), sistemaUsuario );
				if ( perfis.isEmpty() ) {
					return ResponseUtils.mguResponse( CodigoMensagem.RETORNO_24 );
				}

				if ( usuario.getFlUsuarioSistema().equals( 1 ) ) {
					return loginAgrupadoResponse( usuario, perfis, sistemaUsuario, usuario.getUsuarioLogado() );
				}

				mguResponse = responseValidaParametroSistema( sistemaUsuario, usuario, perfis );
				if ( mguResponse != null ) {
					return mguResponse;
				}

				return loginResponse( usuario, perfis, sistemaUsuario, usuario.getUsuarioLogado() );

			} else {
				// USUARIO_BLOQUEADO
				return responseUsuarioBloqueado( usuario );
			}

		} else if ( !valido && usuario != null ) {
			Object mguResponse = responseUsuarioInvalidoLdap( usuario );
			if ( mguResponse != null ) {
				return mguResponse;
			}
		}

		return ResponseUtils.mguResponse( CodigoMensagem.RETORNO_1 );
	}

	public Object processarLoginIntegracaoPerfil( Usuario usuarioRequest ) throws Exception {
		Boolean valido = ldapService.existeUsuario( usuarioRequest.getDcUsername(), usuarioRequest.getDcSenha() );
		String sistemaUsuario = usuarioRequest.getSistema();
		Usuario usuario = usuarioService.findByUsername( usuarioRequest.getDcUsername() );

		// USUARIO VALIDO LDAP
		if ( valido && usuario != null ) {

			Object mguResponse = responseValidaDynamicsECredenciamento( usuarioRequest, sistemaUsuario, usuario );
			if ( mguResponse != null ) {
				return mguResponse;
			}

			if ( !usuario.getFlAprovado() && SistemaUtils.CREDENCIAMENTO.equalsIgnoreCase( sistemaUsuario ) ) {
				List<PerfilDTO> perfisUsuario = new ArrayList<PerfilDTO>();
				for ( Prestadora prestadora : usuario.getPrestadoras() ) {
					PerfilDTO perfil = new PerfilDTO( 666L, "SOLICITANTE", prestadora.getId(), prestadora.getNoPrestadora() );
					perfisUsuario.add( perfil );
				}

				return loginAgrupadoResponse( usuario, perfisUsuario, sistemaUsuario, usuario.getUsuarioLogado() );
			}

			if ( usuario.getFlBloqueio().equals( BloqueioUsuario.BLOQUEADO_NAO ) ) {

				List<PerfilDTO> perfis = new ArrayList<PerfilDTO>();
				if ( usuario.getFlMaster() && SistemaUtils.CREDENCIAMENTO.equalsIgnoreCase( sistemaUsuario ) ) {
					perfis = perfilService.findPerfisMasterByUsernameAndSistema( usuario.getDcUsername(), sistemaUsuario );
				} else {
					perfis = perfilService.findByUsernameAndSistema( usuario.getDcUsername(), sistemaUsuario );
				}

				if ( perfis.isEmpty() && usuario.getFlUsuarioSistema().equals( 0 ) ) {
					return ResponseUtils.mguResponse( CodigoMensagem.RETORNO_24 );
				}

				// VALIDACAO USUARIO MENSAGERIA SNOA
				if ( usuario.getFlUsuarioSistema().equals( 1 ) ) {
					Perfil administrador = usuario.getPerfil();
					Prestadora prestadora = usuario.getPrestadoras().get( 0 );
					perfis = new ArrayList<PerfilDTO>();
					perfis.add( new PerfilDTO( administrador.getId(), administrador.getDcPerfil(), prestadora.getId(), prestadora.getNoPrestadora() ) );
					return loginAgrupadoResponse( usuario, perfis, sistemaUsuario, usuario.getUsuarioLogado() );
				}

				mguResponse = responseValidaParametroSistema( sistemaUsuario, usuario, perfis );
				if ( mguResponse != null ) {
					return mguResponse;
				}

				return loginAgrupadoResponse( usuario, perfis, sistemaUsuario, usuario.getUsuarioLogado() );

			} else {
				// USUARIO_BLOQUEADO
				return responseUsuarioBloqueado( usuario );
			}

		}

		// USUARIO INVALIDO LDAP
		if ( !valido && usuario != null ) {
			Object mguResponse = responseUsuarioInvalidoLdap( usuario );
			if ( mguResponse != null ) {
				return mguResponse;
			}
		}

		return ResponseUtils.mguResponse( CodigoMensagem.RETORNO_1 );

	}

	private Object responseValidaDynamicsECredenciamento( Usuario usuarioRequest, String sistemaUsuario, Usuario usuario ) {
		if ( SistemaUtils.DYNAMICS.equalsIgnoreCase( sistemaUsuario ) && !usuario.getFlEnviarDynamics() ) {
			return ResponseUtils.mguResponse( CodigoMensagem.RETORNO_26 );
		} else if ( !SistemaUtils.CREDENCIAMENTO.equalsIgnoreCase( sistemaUsuario ) && !usuario.getFlAprovado() ) {
			return ResponseUtils.mguResponse( CodigoMensagem.RETORNO_8 );
		}
		return null;
	}

	private Object responseUsuarioInvalidoLdap( Usuario usuario ) {
		usuario.setQtTentativaAcesso( usuario.getQtTentativaAcesso() + 1 );

		ParametroSistema parametroSistema = parametroSistemaService.findByGrupoPrestadoraId( usuario.getPrestadoras().get( 0 ).getGrupoPrestadora().getId() );
		if ( parametroSistema != null && parametroSistema.getFlQtdErrarSenha().equals( 1 ) ) {
			if ( parametroSistema.getQtdErrarSenha() != null && usuario.getQtTentativaAcesso() != null && usuario.getQtTentativaAcesso() > ( parametroSistema.getQtdErrarSenha() ) ) {
				usuario.setFlBloqueio( BloqueioUsuario.BLOQUEADO_TENTATIVA );
				usuarioService.salvar( usuario );
				return ResponseUtils.mguResponse( CodigoMensagem.RETORNO_29 );
			}

			if ( parametroSistema.getQtdErrarSenha() != null && usuario.getQtTentativaAcesso() != null && usuario.getQtTentativaAcesso().intValue() + 1 > ( parametroSistema.getQtdErrarSenha() ) ) {
				usuarioService.salvar( usuario );
				return ResponseUtils.mguResponse( CodigoMensagem.RETORNO_28 );
			}
		}
		usuarioService.salvar( usuario );
		return null;
	}

	private Object responseValidaParametroSistema( String sistemaUsuario, Usuario usuario, List<PerfilDTO> perfis ) {
		ParametroSistema parametroSistema = parametroSistemaService.findByGrupoPrestadoraId( usuario.getPrestadoras().get( 0 ).getGrupoPrestadora().getId() );

		if ( parametroSistema != null ) {
			// Senha expirada
			if ( parametroSistema.getFlPrazoExpirarSenha().equals( 0 ) ) {
				if ( usuario.getUltimaTrocaSenha() == null ) {
					return ResponseUtils.mguResponse( CodigoMensagem.RETORNO_22 );
				} else {
					int diff = (int) ( ( new Date().getTime() - usuario.getUltimaTrocaSenha().getTime() ) / ( 1000 * 60 * 60 * 24 ) );
					if ( diff >= parametroSistema.getPrazoExpirarSenha() ) {
						return ResponseUtils.mguResponse( CodigoMensagem.RETORNO_22 );
					}
				}
			}

			logger.info( "Usuário bloqueado por inatividade? {}", parametroSistema.getFlBloquearInatividade().equals( 0 ) );
			// Bloqueado por inatividade
			if ( parametroSistema.getFlBloquearInatividade().equals( 0 ) ) {
				logger.info( "Último acesso: {}", usuario.getUltimoAcesso() );
				if ( usuario.getUltimoAcesso() == null ) {
					return ResponseUtils.mguResponse( CodigoMensagem.RETORNO_31 );
				} else {
					int diff = (int) ( ( new Date().getTime() - usuario.getUltimoAcesso().getTime() ) / ( 1000 * 60 * 60 * 24 ) );
					logger.info( "Dias de inatividade: {}", diff );
					if ( diff >= parametroSistema.getPrazoExpirarSenha() ) {
						return ResponseUtils.mguResponse( CodigoMensagem.RETORNO_31 );
					}
				}
			}

			try {
				if ( parametroSistema.getFlAcessoSimultaneo().equals( 0 ) ) {
					boolean usuarioLogado = acessoSimultaneoService.existsByUsername( usuario.getDcUsername() );
					usuario.setUsuarioLogado( usuarioLogado );
					acessoSimultaneoService.salvarByUsuario( usuario );
				}
			} catch ( Exception e ) {
				logger.error( "Erro ao salvar acesso simultâneo, usuário {} ", usuario.getDcUsername() );
			}
		}

		if ( SistemaUtils.SNOA.equalsIgnoreCase( sistemaUsuario ) ) {
			usuario.setFlPrimeiroAcessoSNOA( false );
		}

		usuario.setUltimoAcesso( new Date() );
		usuarioService.salvar( usuario );

		return null;
	}

	private Object responseUsuarioBloqueado( Usuario usuario ) {
		if ( usuario.getFlBloqueio().equals( BloqueioUsuario.BLOQUEADO_PRIMEIROACESSO ) || usuario.getFlBloqueio().equals( BloqueioUsuario.BLOQUEADO_EXPIRADA ) ) {
			return ResponseUtils.mguResponse( CodigoMensagem.RETORNO_22 );
		} else {
			return ResponseUtils.mguResponse( CodigoMensagem.RETORNO_4 );
		}
	}

	/*
	 * Adaptação para o snoa ate mudar o metodo
	 */
	private Object loginResponse( Usuario usuario, List<PerfilDTO> perfis, String sistemaUsuario, boolean usuarioLogado ) {
		List<Long> idPrestadoras = listaIdPrestadoras( perfis );

		MguLoginResponse loginResponse = montarDadosUsuarioEGrupoPrestadora( usuario, perfis, sistemaUsuario, usuarioLogado, idPrestadoras, false );

		for ( PerfilDTO perfil : perfis ) {
			PerfilResponse perfilResponse = new PerfilResponse();
			perfilResponse.setId( perfil.getIdPerfil() );
			if ( SistemaUtils.SCADA.equalsIgnoreCase( sistemaUsuario ) ) {
				if ( perfilService.checkIsPerfilABRTOrCTECH( perfil.getDcPerfil() ) ) {
					perfilResponse.setNome( XmlUtils.cdataWrapper( perfil.getDcPerfil() + "_" + perfil.getNoRazaoSocial() ) );
				} else {
					if ( !prestadoraPMSMercadoService.checkIsPrestadoraPMS( idPrestadoras ) ) {
						perfilResponse.setNome( XmlUtils.cdataWrapper( "PGMC_PRESTADORA" ) );
					} else {
						perfilResponse.setNome( XmlUtils.cdataWrapper( perfil.getDcPerfil() + "_" + perfil.getNoRazaoSocial() ) );
					}
				}
			} else {
				perfilResponse.setNome( XmlUtils.cdataWrapper( perfil.getDcPerfil() ) );
			}

			loginResponse.getDados().getPerfis().add( perfilResponse );

			if ( SistemaUtils.GED.equalsIgnoreCase( sistemaUsuario ) && perfil.getDcPerfil().equals( "GED_PMS" ) && loginResponse.getIsPMS() ) {
				PerfilResponse perfilResponseGed = new PerfilResponse();
				perfilResponseGed.setId( 0l );
				perfilResponseGed.setNome( loginResponse.getPerfilGed() );
				loginResponse.setIsPMS( false );

				loginResponse.getDados().getPerfis().add( perfilResponseGed );
			}

		}

		return loginResponse;
	}

	private Object loginAgrupadoResponse( Usuario usuario, List<PerfilDTO> perfis, String sistemaUsuario, boolean usuarioLogado ) {

		MguLoginResponse loginResponse = montarDadosUsuarioEGrupoPrestadora( usuario, perfis, sistemaUsuario, usuarioLogado, null, true );

		if ( !loginResponse.getSemPrestadora() ) {
			for ( PerfilDTO perfil : perfis ) {
				PerfilResponse perfilResponse = new PerfilResponse();
				perfilResponse.setId( perfil.getIdPerfil() );
				if ( SistemaUtils.SCADA.equalsIgnoreCase( sistemaUsuario ) ) {
					perfilResponse.setNome( XmlUtils.cdataWrapper( perfil.getDcPerfil() + "_" + perfil.getNoRazaoSocial() ) );
				} else {
					perfilResponse.setNome( XmlUtils.cdataWrapper( perfil.getDcPerfil() ) );
				}
				loginResponse.getDados().getUsuario().getPerfis().add( perfilResponse );
			}

		}

		return loginResponse;

	}

	private MguLoginResponse montarDadosUsuarioEGrupoPrestadora( Usuario usuario, List<PerfilDTO> perfis, String sistemaUsuario, boolean usuarioLogado, List<Long> idPrestadoras, boolean loginAgrupado ) {
		Boolean umaVez = Boolean.TRUE;

		MguLoginResponse loginResponse = new MguLoginResponse();
		DadosResponse dadosResponse = new DadosResponse();
		UsuarioResponse usuarioResponse = new UsuarioResponse();
		GrupoPrestadoraResponse grupoPrestadoraResponse = new GrupoPrestadoraResponse();

		usuarioResponse.setGrupoPrestadora( grupoPrestadoraResponse );
		dadosResponse.setUsuario( usuarioResponse );
		loginResponse.setDados( dadosResponse );

		if ( usuarioLogado && SistemaUtils.SNOA.equalsIgnoreCase( sistemaUsuario ) ) {
			loginResponse.setRetorno( CodigoMensagem.RETORNO_30.getCodigo() );
			loginResponse.setDescricao( XmlUtils.cdataWrapper( CodigoMensagem.RETORNO_30.getDescricao() ) );
		} else {
			loginResponse.setRetorno( CodigoMensagem.RETORNO_0.getCodigo() );
			loginResponse.setDescricao( XmlUtils.cdataWrapper( CodigoMensagem.RETORNO_0.getDescricao() ) );
		}

		usuarioResponse.setNomeUsuario( XmlUtils.cdataWrapper( usuario.getNmUsuario() ) );
		usuarioResponse.setTelefone( XmlUtils.cdataWrapper( usuario.getDcTelefone() ) );
		usuarioResponse.setEmail( XmlUtils.cdataWrapper( usuario.getDcEmail() ) );
		usuarioResponse.setIpOrigem( XmlUtils.cdataWrapper( usuario.getDcIpOrigem() != null ? usuario.getDcIpOrigem() : "nao cadastrado" ) );
		usuarioResponse.setSituacao( XmlUtils.cdataWrapper( "ATIVO" ) );

		if ( SistemaUtils.SNOA.equalsIgnoreCase( sistemaUsuario ) ) {
			usuarioResponse.setPrimeiroAcesso( usuario.getFlPrimeiroAcessoSNOA() ? 1 : 0 );
		}

		for ( Prestadora prestadora : usuario.getPrestadoras() ) {

			if ( umaVez ) {
				/*
				 * Ajuste de sistema para funcionar o SNOA como ANATEL
				 */
				if ( prestadora.getId().equals( 1L ) && ( SistemaUtils.SNOA.equalsIgnoreCase( sistemaUsuario ) || SistemaUtils.SOIA.equalsIgnoreCase( sistemaUsuario ) ) ) {
					loginResponse.setSemPrestadora( false );
					loginResponse.setPerfilGed( "PERFIL_GED_" + prestadora.getGrupoPrestadora().getNoGrupoPrestadora().toUpperCase().trim() );
					loginResponse.setIsPMS( true );
					break;
				} else {
					grupoPrestadoraResponse.setIdGrupoPrestadora( prestadora.getGrupoPrestadora().getId() );
					grupoPrestadoraResponse.setNomeGrupoPrestadora( XmlUtils.cdataWrapper( prestadora.getGrupoPrestadora().getNoGrupoPrestadora() ) );

					if ( IDS_PMS.contains( prestadora.getGrupoPrestadora().getId() ) ) {
						loginResponse.setIsPMS( true );
						if ( prestadora.getGrupoPrestadora().getId().equals( ID_PMS_VIVO ) ) {
							loginResponse.setPerfilGed( "PERFIL_GED_" + prestadora.getGrupoPrestadora().getNoGrupoPrestadora().toUpperCase().substring( 0, 4 ).trim() );
						} else {
							loginResponse.setPerfilGed( "PERFIL_GED_" + prestadora.getGrupoPrestadora().getNoGrupoPrestadora().toUpperCase().trim() );
						}
					}

				}
				umaVez = Boolean.FALSE;
			}

			if ( idPrestadoras != null && idPrestadoras.contains( prestadora.getId() ) ) {
				PrestadoraResponse prestadoraResponse = new PrestadoraResponse();
				prestadoraResponse.setId( prestadora.getId() );
				prestadoraResponse.setNome( XmlUtils.cdataWrapper( prestadora.getNoPrestadora() ) );
				grupoPrestadoraResponse.getPrestadoras().add( prestadoraResponse );
			}

			if ( loginAgrupado ) {
				boolean imprimePrestadora = Boolean.TRUE;

				for ( PerfilDTO perfil : perfis ) {
					if ( perfil.getIdOperadora().equals( prestadora.getId() ) ) {
						if ( imprimePrestadora ) {
							PrestadoraResponse prestadoraResponse = new PrestadoraResponse();
							prestadoraResponse.setId( prestadora.getId() );
							prestadoraResponse.setNome( XmlUtils.cdataWrapper( prestadora.getNoPrestadora() ) );
							if ( SistemaUtils.SNOA.equalsIgnoreCase( sistemaUsuario ) ) {
								if ( usuario.getFlMaster() == false ) {
									Delegado delegado = delegadoService.findByUsuarioComumDcUsernameAndPrestadoraId( usuario.getDcUsername(), prestadora.getId() );
									prestadoraResponse.setMaster( delegado == null ? 0 : 1 );
								} else {
									prestadoraResponse.setMaster( 1 );
								}
							}
							imprimePrestadora = Boolean.FALSE;
							grupoPrestadoraResponse.getPrestadoras().add( prestadoraResponse );
						}

						PerfilResponse perfilResponse = new PerfilResponse();
						perfilResponse.setId( perfil.getIdPerfil() );
						perfilResponse.setNome( perfil.getDcPerfil() );

						grupoPrestadoraResponse.getPrestadoras().get( 0 ).getPerfis().add( perfilResponse );
					}

					if ( SistemaUtils.GED.equalsIgnoreCase( sistemaUsuario ) && perfil.getDcPerfil().equals( "GED_PMS" ) && loginResponse.getIsPMS() ) {
						PerfilResponse perfilResponse = new PerfilResponse();
						perfilResponse.setId( 0l );
						perfilResponse.setNome( loginResponse.getPerfilGed() );
						loginResponse.setIsPMS( false );

						grupoPrestadoraResponse.getPrestadoras().get( 0 ).getPerfis().add( perfilResponse );
					}

				}
			}
		}

		return loginResponse;
	}

	private List<Long> listaIdPrestadoras( List<PerfilDTO> perfis ) {
		List<Long> idPrestadoras = new ArrayList<Long>();
		for ( PerfilDTO perfil : perfis ) {
			idPrestadoras.add( perfil.getIdOperadora() );
		}
		return idPrestadoras;
	}

}
