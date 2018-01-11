package br.com.cleartech.pgmc.mgu.view.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

@Service
@RestController
public class RecaptchaService {

	private static final Logger logger = LoggerFactory.getLogger( RecaptchaService.class );

	@Value( "${google.recaptcha.secret}" )
	String recaptchaSecret;

	private static final String GOOGLE_RECAPTCHA_VERIFY_URL = "https://www.google.com/recaptcha/api/siteverify";

	@Autowired
	RestTemplateBuilder restTemplateBuilder;

	public String verifyRecaptcha( String ip, String recaptchaResponse ) {
		Map<String, String> body = new HashMap<>();
		body.put( "secret", "6LfgZz8UAAAAAC0rw4DREGCIvPnHDjN33Ck2jXP2" );
		body.put( "response", recaptchaResponse );
		body.put( "remoteip", ip );
		logger.info( "Request body for recaptcha: {}", body );
		ResponseEntity<Map> recaptchaResponseEntity = restTemplateBuilder.build().postForEntity( GOOGLE_RECAPTCHA_VERIFY_URL + "?secret={secret}&response={response}&remoteip={remoteip}", body, Map.class, body );

		logger.info( "Response from recaptcha: {}", recaptchaResponseEntity );
		Map<String, Object> responseBody = recaptchaResponseEntity.getBody();

		boolean recaptchaSucess = (Boolean) responseBody.get( "success" );
		if ( !recaptchaSucess ) {
			List<String> errorCodes = (List) responseBody.get( "error-codes" );

			// String errorMessage = errorCodes.stream().map( s ->
			// RecaptchaUtil.RECAPTCHA_ERROR_CODE.get( s ) ).collect(
			// Collectors.joining( ", " ) );
			if ( errorCodes != null && !errorCodes.isEmpty() )
				return "Valor informado no captcha invalido!";

			return "Erro na configuração do captcha. Por favor entrar em contato com a central de serviços.";
		} else {
			return org.apache.commons.lang.StringUtils.EMPTY;
		}
	}
}