package br.com.cleartech.pgmc.mgu.services.impl;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.ws.soap.SOAPFaultException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Strings;

import br.com.cleartech.pgmc.mgu.entities.Prestadora;
import br.com.cleartech.pgmc.mgu.entities.Usuario;
import br.com.cleartech.pgmc.mgu.enums.ParametrizacaoEnum;
import br.com.cleartech.pgmc.mgu.services.DynamicsService;
import br.com.cleartech.pgmc.mgu.services.ParametrizacaoService;
import br.com.cleartech.pgmc.mgu.utils.FormatterUtils;
import br.com.cleartech.pgmc.mgu.utils.JGet;
import br.com.cleartech.pgmc.mgu.wsdl.dynamics.DadosRetorno;
import br.com.cleartech.pgmc.mgu.wsdl.dynamics.DadosUsuarioPgmc;
import br.com.cleartech.pgmc.mgu.wsdl.dynamics.IUsuarioPgmc;
import br.com.cleartech.pgmc.mgu.wsdl.dynamics.ObjectFactory;
import br.com.cleartech.pgmc.mgu.wsdl.dynamics.UsuarioPgmc;

@Service
public class DynamicsServiceImpl implements DynamicsService {

	private static final Logger logger = LoggerFactory.getLogger( DynamicsServiceImpl.class );

	@Autowired
	private ParametrizacaoService parametrizacaoService;

	@Override
	public DadosRetorno criarUsuario( Usuario usuario, Prestadora prestadoraLogada ) throws Exception {
		JGet.tryUrl( getEndpoint() );

		DadosUsuarioPgmc d = new DadosUsuarioPgmc();

		JAXBContext context = JAXBContext.newInstance( "br.com.cleartech.pgmc.mgu.wsdl.dynamics" );
		Marshaller marshaller = context.createMarshaller();
		JAXBElement<String> cpf = new ObjectFactory().createDadosUsuarioPgmcCpf( FormatterUtils.format( FormatterUtils.CPF, usuario.getNuCpf().replace( ".", "" ).replace( "-", "" ) ) );
		d.setCpf( cpf );
		marshaller.marshal( cpf, System.out );

		JAXBElement<String> login = new ObjectFactory().createDadosUsuarioPgmcLogin( usuario.getDcUsername() );
		marshaller.marshal( login, System.out );
		d.setLogin( login );

		JAXBElement<String> nomeUsuario = new ObjectFactory().createDadosUsuarioPgmcNome( usuario.getNmUsuario() );
		marshaller.marshal( nomeUsuario, System.out );
		d.setNome( nomeUsuario );

		JAXBElement<String> prest = new ObjectFactory().createDadosUsuarioPgmcNomePrestadora( prestadoraLogada.getNoPrestadora() );
		marshaller.marshal( prest, System.out );
		d.setNomePrestadora( prest );

		JAXBElement<String> tel = new ObjectFactory().createDadosUsuarioPgmcTelefoneCelular( usuario.getDcTelefone() );
		marshaller.marshal( tel, System.out );
		d.setTelefoneCelular( tel );

		JAXBElement<String> telC = null;
		if ( usuario.getDcTelefoneFixo() != null && !usuario.getDcTelefoneFixo().trim().equals( "" ) ) {
			telC = new ObjectFactory().createDadosUsuarioPgmcTelefoneComercial( usuario.getDcTelefoneFixo() );
			marshaller.marshal( telC, System.out );
			d.setTelefoneComercial( telC );
		} else {
			telC = new ObjectFactory().createDadosUsuarioPgmcTelefoneComercial( usuario.getDcTelefone() );
			marshaller.marshal( telC, System.out );
			d.setTelefoneComercial( telC );
		}

		JAXBElement<String> email = new ObjectFactory().createDadosUsuarioPgmcEmail( usuario.getDcEmail() );
		marshaller.marshal( email, System.out );
		d.setEmail( email );

		JAXBElement<String> spid = new ObjectFactory().createDadosUsuarioPgmcSpidPrestadora( String.valueOf( prestadoraLogada.getId() ) );
		marshaller.marshal( spid, System.out );
		d.setSpidPrestadora( spid );

		URL url = new URL( UsuarioPgmc.class.getResource( "." ), getEndpoint() );

		IUsuarioPgmc enviar = new UsuarioPgmc( url ).getBasicHttpBindingIUsuarioPgmc();

		DadosRetorno retorno = enviar.cadastrar( d );
		logger.info( "Retorno Dynamics: {}", retorno );
		return retorno;
	}

	@Override
	public void alterar( Usuario usuario, boolean removerAcesso, Prestadora prestadoraLogada ) throws JAXBException, Exception {
		JGet.tryUrl( getEndpoint() );
		try {
			DadosUsuarioPgmc d = new DadosUsuarioPgmc();

			JAXBContext context = JAXBContext.newInstance( "br.com.cleartech.pgmc.mgu.wsdl.dynamics" );
			Marshaller marshaller = context.createMarshaller();
			JAXBElement<String> cpf = new ObjectFactory().createDadosUsuarioPgmcCpf( FormatterUtils.format( FormatterUtils.CPF, usuario.getNuCpf().replace( ".", "" ).replace( "-", "" ) ) );
			d.setCpf( cpf );
			marshaller.marshal( cpf, System.out );

			JAXBElement<String> login = new ObjectFactory().createDadosUsuarioPgmcLogin( usuario.getDcUsername() );
			marshaller.marshal( login, System.out );
			d.setLogin( login );

			JAXBElement<String> nomeUsuario = new ObjectFactory().createDadosUsuarioPgmcNome( usuario.getNmUsuario() );
			marshaller.marshal( nomeUsuario, System.out );
			d.setNome( nomeUsuario );

			if ( prestadoraLogada == null ) {
				logger.warn( "PROBLEMAS NA INTEGACAO -> prestadora == null" );
				return;
			}

			JAXBElement<String> spid = new ObjectFactory().createDadosUsuarioPgmcSpidPrestadora( String.valueOf( prestadoraLogada.getId() ) );
			marshaller.marshal( spid, System.out );
			d.setSpidPrestadora( spid );

			if ( prestadoraLogada.getNoPrestadora() != null ) {
				JAXBElement<String> prest = new ObjectFactory().createDadosUsuarioPgmcNomePrestadora( prestadoraLogada.getNoPrestadora() );
				marshaller.marshal( prest, System.out );
				d.setNomePrestadora( prest );
			}

			JAXBElement<String> tel = new ObjectFactory().createDadosUsuarioPgmcTelefoneCelular( usuario.getDcTelefone() );
			marshaller.marshal( tel, System.out );
			d.setTelefoneCelular( tel );

			JAXBElement<String> telC = null;
			if ( usuario.getDcTelefoneFixo() != null && !usuario.getDcTelefoneFixo().trim().equals( "" ) ) {
				telC = new ObjectFactory().createDadosUsuarioPgmcTelefoneComercial( usuario.getDcTelefoneFixo() );
				marshaller.marshal( telC, System.out );
				d.setTelefoneComercial( telC );
			} else {
				telC = new ObjectFactory().createDadosUsuarioPgmcTelefoneComercial( usuario.getDcTelefone() );
				marshaller.marshal( telC, System.out );
				d.setTelefoneComercial( telC );
			}

			JAXBElement<String> email = new ObjectFactory().createDadosUsuarioPgmcEmail( usuario.getDcEmail() );
			marshaller.marshal( email, System.out );
			d.setEmail( email );

			URL url = new URL( UsuarioPgmc.class.getResource( "." ), getEndpoint() );
			IUsuarioPgmc enviar = new UsuarioPgmc( url ).getBasicHttpBindingIUsuarioPgmc();
			enviar.alterar( d, removerAcesso );
		} catch ( SOAPFaultException e ) {
			logger.warn( "WARN - Usuário não existe na base do dynamics CRM" );
			e.printStackTrace();
		} catch ( MalformedURLException e ) {
			e.printStackTrace();
		}

	}

	@Override
	public void desativarUsuario( String login, String email ) throws Exception {
		JGet.tryUrl( getEndpoint() );
		URL url = new URL( UsuarioPgmc.class.getResource( "." ), getEndpoint() );
		IUsuarioPgmc enviar = new UsuarioPgmc( url ).getBasicHttpBindingIUsuarioPgmc();
		enviar.desativar( login, email );
	}

	private String getEndpoint() {
		// adicionar <system-properties><property name="mgu.development"
		// value="true"/></system-properties> no servidor localhost
		String prop = System.getProperty( "mgu.development" );
		boolean isDevelopment = !Strings.isNullOrEmpty( prop ) ? Boolean.parseBoolean( prop ) : false;
		if ( isDevelopment ) {
			return "http://10.200.21.3:8091/UsuarioPgmc.svc?wsdl"; // LOCAL
		} else {
			return parametrizacaoService.findByDcParametro( ParametrizacaoEnum.DYNAMICS_URL.getDcParametro() );
		}
	}
}
