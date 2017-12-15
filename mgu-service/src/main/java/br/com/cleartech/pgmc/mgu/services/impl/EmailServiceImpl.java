package br.com.cleartech.pgmc.mgu.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import br.com.cleartech.pgmc.mgu.dtos.EmailDTO;
import br.com.cleartech.pgmc.mgu.entities.Usuario;
import br.com.cleartech.pgmc.mgu.enums.AssuntoEnum;
import br.com.cleartech.pgmc.mgu.services.EmailService;

@Service
public class EmailServiceImpl implements EmailService {

	private static final Logger logger = LoggerFactory.getLogger( EmailServiceImpl.class );

	@Autowired
	private JavaMailSender javaMailsSender;

	@Override
	public void enviaByUsuarioAndAssunto( Usuario usuario, AssuntoEnum assunto ) {
		StringBuilder corpoEmail = new StringBuilder( getEmailHeader() );
		String cabecalho = null;
		switch ( assunto ) {
			case CRIAR_USUARIO:
				cabecalho = "Credenciamento ESOA - Credenciais de acesso";
				corpoEmail.append( getEmailBody( usuario.getNmUsuario(), usuario.getDcUsername(), usuario.getSenhaSemMD5(), "Credenciamento ESOA &#45; Credenciais de acesso", "A cria&ccedil;&atilde;o do seu usu&aacute;rio foi um sucesso. Seguem os dados de acesso:" ) );
				break;
			default:
				break;
		}

		EmailDTO email = new EmailDTO( usuario.getDcEmail(), cabecalho, corpoEmail.toString() );
		// sendEmail( email );
	}

	// private void sendEmail( EmailDTO email ) throws MessagingException {
	// logger.debug( "Enviando email para {}", email.getDestinatario() );
	// MimeMessage message = new MimeMessage( mailSession );
	// Address[] to = new InternetAddress[] { new InternetAddress(
	// email.getDestinatario() ) };
	// message.setRecipients( RecipientType.TO, to );
	// message.setSubject( email.getCabecalho() );
	// message.setSentDate( new Date() );
	// message.setContent( email.getConteudo(), "text/html" );
	// Transport.send( message );
	// logger.debug( "Email enviado para {}", email.getDestinatario() );
	//
	// }

	private String getEmailHeader() {
		StringBuilder emailHeader = new StringBuilder();
		emailHeader.append( "<html>" );
		emailHeader.append( "	<head>" );
		emailHeader.append( "		<meta http-equiv=Content-Type content=\"text/html; charset=windows-1252\">" );
		emailHeader.append( "		<style>" );
		emailHeader.append( "			<!-- @font-face {font-family:Calibri; panose-1:2 15 5 2 2 2 4 3 2 4;} p.MsoNormal, li.MsoNormal, div.MsoNormal" );
		emailHeader.append( "			{margin-top:0cm; margin-right:0cm; margin-bottom:10.0pt; margin-left:0cm; line-height:115%; font-size:10.0pt; font-family:\"Calibri\",\"sans-serif\";}" );
		emailHeader.append( "			--> body {font-family: Calibri;} td {text-align: justify; font-size: 10pt;} th {font-size: 10pt;} #titulo {background-color: #17365D;  color: #FFFFFF; font-size: 16.0pt;  font-weight: bold;  width: 417; height: 64.75pt; padding: 0cm 9.9pt 0cm 9.9pt; border-right: solid #17365D 1.0pt;}" );
		emailHeader.append( "	    	#icone {background-color: #17365D; width: 159; height: 64.75pt; padding: 0cm 9.9pt 0cm 0cm; text-align:right; border-left: solid 1px #17365D;}" );
		emailHeader.append( "			#logo {background-color: #F2F2F2; width: 146; text-align:right; vertical-align: bottom; border-top:none; border-left:none; border-bottom:solid #17365D 1.0pt; border-right:solid #17365D 1.0pt; padding:9.9pt 9.9pt 9.9pt 0cm; height:41.45pt;}" );
		emailHeader.append( "			#assinatura {width:322.4pt;border-top:none;border-left:solid #17365D 1.0pt;border-bottom:solid #17365D 1.0pt;border-right:none;background:#F2F2F2;padding:9.9pt 0cm 9.9pt 9.9pt;height:41.45pt;}" );
		emailHeader.append( "			#corpo {width:432.2pt;border-top:none;border-left:solid #17365D 1.0pt;border-bottom:none;border-right:solid #17365D 1.0pt;background:#F2F2F2;padding:9.9pt 9.9pt 0cm 9.9pt; font-size: 10pt;}" );
		emailHeader.append( "		</style>" );
		emailHeader.append( "	</head>" );
		return emailHeader.toString();
	}

	private String getEmailBody( String nmUsuario, String username, String senha, String titulo, String msg ) {
		StringBuilder emailBody = new StringBuilder();

		emailBody.append( "<body lang=PT-BR>" );
		emailBody.append( "	<div align=center>" );
		emailBody.append( "		<table border=1 cellspacing=0 cellpadding=0 style='border-collapse:collapse;border:none'>" );
		emailBody.append( "			<tr style='height:64.75pt'>" );
		emailBody.append( "				<td id=\"titulo\"><p>" + titulo + "</p>" );
		emailBody.append( "				</td>" );
		emailBody.append( "				<td id=\"icone\" width=159 colspan=2>&nbsp;</p>" );
		emailBody.append( "				</td>" );
		emailBody.append( "			</tr>" );
		emailBody.append( "			<tr>" );
		emailBody.append( "				<td id=\"corpo\" colspan=\"3\">" );
		emailBody.append( "					Prezado(a), " + nmUsuario );
		emailBody.append( "				</td>" );
		emailBody.append( "			</tr>" );
		emailBody.append( "			<tr>" );
		emailBody.append( "				<td id=\"corpo\" colspan=\"3\"> " );
		emailBody.append( "					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + msg );
		emailBody.append( "				</td>" );
		emailBody.append( "			</tr>" );
		emailBody.append( "			<tr>" );
		emailBody.append( "				<td id=\"corpo\" colspan=\"3\"> " );
		emailBody.append( "				</td>" );
		emailBody.append( "			</tr>" );
		emailBody.append( "			<tr>" );
		emailBody.append( "				<td id=\"corpo\" colspan=\"3\"> " );
		emailBody.append( "				" );
		emailBody.append( "				</td>" );
		emailBody.append( "			</tr>" );
		emailBody.append( "			<tr>" );
		emailBody.append( "				<td id=\"corpo\" colspan=\"3\"> " );
		emailBody.append( "				</td>" );
		emailBody.append( "			</tr>" );
		emailBody.append( "			<tr>" );
		emailBody.append( "				<td id=\"corpo\" colspan=\"3\">" );
		emailBody.append( "					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Credenciais de acesso:" );
		emailBody.append( "				</td>" );
		emailBody.append( "			</tr>" );
		emailBody.append( "			<tr>" );
		emailBody.append( "				<td id=\"corpo\" colspan=\"3\">" );
		emailBody.append( "					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&bull; Usu&aacute;rio: <b>" + username + "</b>" );
		emailBody.append( "				</td>" );
		emailBody.append( "			</tr>" );
		emailBody.append( "			<tr>" );
		emailBody.append( "				<td id=\"corpo\" colspan=\"3\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&bull; Senha: <b>" + senha + "</b></td>" );
		emailBody.append( "			</tr>" );
		emailBody.append( "			<tr>" );
		emailBody.append( "				<td id=\"assinatura\" colspan=\"2\"><p class=MsoNormal style='margin-bottom:0cm;margin-bottom:.0001pt;text-align:justify;line-height:normal'>" );
		emailBody.append( "					Atenciosamente,<br><b>ABR Telecom<br>Entidade Supervisora de Ofertas de Atacado</b></p>" );
		emailBody.append( "				</td>" );
		emailBody.append( "				<td id=\"logo\">&nbsp;</td>" );
		emailBody.append( "			</tr>" );
		emailBody.append( "		</table>" );
		emailBody.append( "	</div>" );
		emailBody.append( "</body>" );
		emailBody.append( "</html>" );

		return emailBody.toString();
	}

	public static String getEmailSemDadosBody( String nmUsuario, String titulo, String msg ) {
		StringBuilder emailBody = new StringBuilder();

		emailBody.append( "<body lang=PT-BR>" );
		emailBody.append( "	<div align=center>" );
		emailBody.append( "		<table border=1 cellspacing=0 cellpadding=0 style='border-collapse:collapse;border:none'>" );
		emailBody.append( "			<tr style='height:64.75pt'>" );
		emailBody.append( "				<td id=\"titulo\"><p>" + titulo + "</p>" );
		emailBody.append( "				</td>" );
		emailBody.append( "				<td id=\"icone\" width=159 colspan=2>&nbsp;</p>" );
		emailBody.append( "				</td>" );
		emailBody.append( "			</tr>" );
		emailBody.append( "			<tr>" );
		emailBody.append( "				<td id=\"corpo\" colspan=\"3\">" );
		emailBody.append( "					Prezado(a), " + nmUsuario );
		emailBody.append( "				</td>" );
		emailBody.append( "			</tr>" );
		emailBody.append( "			<tr>" );
		emailBody.append( "				<td id=\"corpo\" colspan=\"3\"> " );
		emailBody.append( "					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + msg );
		emailBody.append( "				</td>" );
		emailBody.append( "			</tr>" );
		emailBody.append( "			<tr>" );
		emailBody.append( "				<td id=\"corpo\" colspan=\"3\"> " );
		emailBody.append( "				</td>" );
		emailBody.append( "			</tr>" );
		emailBody.append( "			<tr>" );
		emailBody.append( "				<td id=\"assinatura\" colspan=\"2\"><p class=MsoNormal style='margin-bottom:0cm;margin-bottom:.0001pt;text-align:justify;line-height:normal'>" );
		emailBody.append( "					Atenciosamente,<br><b>ABR Telecom<br>Entidade Supervisora de Ofertas de Atacado</b></p>" );
		emailBody.append( "				</td>" );
		emailBody.append( "				<td id=\"logo\">&nbsp;</td>" );
		emailBody.append( "			</tr>" );
		emailBody.append( "		</table>" );
		emailBody.append( "	</div>" );
		emailBody.append( "</body>" );
		emailBody.append( "</html>" );

		return emailBody.toString();
	}

}
