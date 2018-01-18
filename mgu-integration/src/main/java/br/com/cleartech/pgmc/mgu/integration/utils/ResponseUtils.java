package br.com.cleartech.pgmc.mgu.integration.utils;

import br.com.cleartech.pgmc.mgu.enums.CodigoMensagem;
import br.com.cleartech.pgmc.mgu.integration.ws.responses.MguResponse;

public class ResponseUtils {

	public static Object mguResponse( CodigoMensagem codigo ) {
		return response( codigo, codigo.getDescricao(), null, null );
	}

	public static Object mguResponse( CodigoMensagem codigo, Long idPerfil ) {
		return response( codigo, null, codigo.getDescricao(), idPerfil );
	}

	public static Object mguResponse( CodigoMensagem codigo, String complemento ) {
		return response( codigo, codigo.getDescricao() + "\n" + complemento, null, null );
	}

	private static Object response( CodigoMensagem codigo, String descricao, String dados, Long idPerfil ) {
		MguResponse mguResponse = new MguResponse();
		mguResponse.setRetorno( codigo.getCodigo() );
		mguResponse.setDescricao( descricao );
		mguResponse.setDados( dados );
		mguResponse.setIdPerfil( idPerfil );
		return mguResponse;
	}
}
