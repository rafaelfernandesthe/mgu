package br.com.cleartech.pgmc.mgu.services.impl;

import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

import br.com.cleartech.pgmc.mgu.entities.Usuario;
import br.com.cleartech.pgmc.mgu.enums.AssuntoEnum;
import br.com.cleartech.pgmc.mgu.services.EmailService;

@Service
public class EmailServiceImpl implements EmailService {

	private static final Logger logger = LoggerFactory.getLogger( EmailServiceImpl.class );

	@Autowired
	private JavaMailSender javaMailsSender;

	@Override
	public void enviaByUsuarioAndAssunto( Usuario usuario, AssuntoEnum assunto, Usuario delegado ) throws MessagingException {
		StringBuilder corpoEmail = new StringBuilder( getEmailHeader() );
		String cabecalho = null;
		switch ( assunto ) {
			case CRIAR_USUARIO:
				cabecalho = "Credenciamento ESOA - Credenciais de acesso";
				corpoEmail.append( getEmailBody( usuario.getNmUsuario(), usuario.getDcUsername(), usuario.getSenhaSemMD5(), "Credenciamento ESOA - Credenciais de acesso", "A cria&ccedil;&atilde;o do seu usu&aacute;rio foi um sucesso. Seguem os dados de acesso:" ) );
				break;
			case BLOQUEAR_USUARIO:
				cabecalho = "Bloqueio de usuário";
				corpoEmail.append( getEmailSemDadosBody( usuario.getNmUsuario(), "Bloqueio de usuário", "O usu&aacute;rio <b>" + encode( usuario.getNmUsuario() ) + "</b> foi bloqueado pelo administrador do sistema" ) );
				break;
			case DESBLOQUEAR_USUARIO:
				cabecalho = "Usuário desbloqueado";
				corpoEmail.append( getEmailSemDadosBody( usuario.getNmUsuario(), "Usuário desbloqueado", "O usu&aacute;rio <b>" + encode( usuario.getNmUsuario() ) + "</b> foi desbloqueado pelo administrador do sistema" ) );
				break;
			case REMOVER_DELEGADO:
				cabecalho = "Permissão de acesso modificada";
				if ( delegado != null ) {
					corpoEmail.append( getEmailSemDadosBody( delegado.getNmUsuario(), "Acesso ao PGMC - Permissão de acesso modificada", "A sua permiss&atilde;o de acesso ao PGMC foi alterada com sucesso, voc&ecirc; foi removido do n&iacute;vel de delegado por <b>" + encode( usuario.getNmUsuario() ) + "</b> para acessar o PGMC." ) );
					sendEmail( delegado.getDcEmail(), cabecalho, corpoEmail.toString() );
				}
				return;
			case ADICIONAR_DELEGADO:
				cabecalho = "Permissão de acesso modificada";
				if ( delegado != null ) {
					corpoEmail.append( getEmailSemDadosBody( delegado.getNmUsuario(), "Acesso ao PGMC - Permissão de acesso modificada", "A sua permiss&atilde;o de acesso ao PGMC foi alterada com sucesso, voc&ecirc; foi delegado por <b>" + encode( usuario.getNmUsuario() ) + "</b> para acessar o PGMC." ) );
					sendEmail( delegado.getDcEmail(), cabecalho, corpoEmail.toString() );
				}
				return;
			case REMOVER_USUARIO:
				cabecalho = "Usuário removido do sistema";
				corpoEmail.append( getEmailSemDadosBody( usuario.getNmUsuario(), "Usuário removido do sistema", "O usu&aacute;rio <b>" + encode( usuario.getNmUsuario() ) + "</b> foi removido do sistema, a partir deste momento n&atilde;o possui mais acesso ao PGMC" ) );
				break;
			case REINICIAR_SENHA:
				cabecalho = "Senha reiniciada com sucesso";
				corpoEmail.append( getEmailBody( usuario.getNmUsuario(), usuario.getDcUsername(), usuario.getDcSenha(), "Acesso ao PGMC - alteração de dados cadastrais", "A senha de acesso ao PGMC foi reiniciada com sucesso. Seguem os novos dados de acesso:" ) );
				break;
			case ALTERAR_SENHA:
				cabecalho = "Senha alterada com sucesso";
				corpoEmail.append( getEmailSemDadosBody( usuario.getNmUsuario(), "Acesso ao PGMC - alteração de dados cadastrais", "A senha de acesso ao PGMC foi alterada com sucesso." ) );
				break;
			default:
				break;
		}

		sendEmail( usuario.getDcEmail(), cabecalho, corpoEmail.toString() );

	}

	private void sendEmail( String destinatario, String cabecalho, String conteudo ) throws MessagingException {
		logger.info( "Enviando e-mail de {} para {}", cabecalho, destinatario );

		MimeMessage email = javaMailsSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper( email );
		helper.setFrom( new InternetAddress( "no_reply@cleartech.com.br" ) );
		helper.setTo( destinatario );
		helper.setSubject( cabecalho );
		helper.setText( conteudo, true );
		helper.setSentDate( new Date() );
		javaMailsSender.send( email );

		logger.info( "E-mail enviado para {}", destinatario );
	}

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
		emailBody.append( "				<td id=\"titulo\"><p>" + encode( titulo ) + "</p>" );
		emailBody.append( "				</td>" );
		emailBody.append( "				<td id=\"icone\" width=159 colspan=2>&nbsp;</p>" );
		emailBody.append( "				</td>" );
		emailBody.append( "			</tr>" );
		emailBody.append( "			<tr>" );
		emailBody.append( "				<td id=\"corpo\" colspan=\"3\">" );
		emailBody.append( "					Prezado(a), " + encode( nmUsuario ) );
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
		emailBody.append( "					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&bull; Usu&aacute;rio: <b>" + encode( username ) + "</b>" );
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

	private String getEmailSemDadosBody( String nmUsuario, String titulo, String msg ) {
		StringBuilder emailBody = new StringBuilder();

		emailBody.append( "<body lang=PT-BR>" );
		emailBody.append( "	<div align=center>" );
		emailBody.append( "		<table border=1 cellspacing=0 cellpadding=0 style='border-collapse:collapse;border:none'>" );
		emailBody.append( "			<tr style='height:64.75pt'>" );
		emailBody.append( "				<td id=\"titulo\"><p>" + encode( titulo ) + "</p>" );
		emailBody.append( "				</td>" );
		emailBody.append( "				<td id=\"icone\" width=159 colspan=2>&nbsp;</p>" );
		emailBody.append( "				</td>" );
		emailBody.append( "			</tr>" );
		emailBody.append( "			<tr>" );
		emailBody.append( "				<td id=\"corpo\" colspan=\"3\">" );
		emailBody.append( "					Prezado(a), " + encode( nmUsuario ) );
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

	private String encode( String nmUsuario ) {
		return HtmlUtils.htmlEscape( nmUsuario );
	}

}
