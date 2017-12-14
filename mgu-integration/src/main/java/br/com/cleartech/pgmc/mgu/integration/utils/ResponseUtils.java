package br.com.cleartech.pgmc.mgu.integration.utils;

import br.com.cleartech.pgmc.mgu.enums.CodigoMensagem;
import br.com.cleartech.pgmc.mgu.integration.ws.responses.MguResponse;
import br.com.cleartech.pgmc.mgu.utils.XmlUtils;

public class ResponseUtils {


	public static Object mguResponse( CodigoMensagem codigo ) {
		return response( codigo, codigo.getDescricao() );
	}

	public static Object mguResponse( CodigoMensagem codigo, String complemento ) {
		return response( codigo, codigo.getDescricao() + "\n" + complemento );
	}

	private static Object response( CodigoMensagem codigo, Object descricao ) {
		MguResponse mguResposta = new MguResponse();
		mguResposta.setRetorno( codigo.getCodigo() );
		if ( descricao != null ) {
			mguResposta.setDescricao( XmlUtils.cdataWrapper( descricao ) );
		}
		return mguResposta;
	}
}
