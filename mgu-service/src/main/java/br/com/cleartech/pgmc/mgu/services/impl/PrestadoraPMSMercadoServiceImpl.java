package br.com.cleartech.pgmc.mgu.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cleartech.pgmc.mgu.repositories.PrestadoraPMSMercadoRepository;
import br.com.cleartech.pgmc.mgu.services.PrestadoraPMSMercadoService;

@Service
public class PrestadoraPMSMercadoServiceImpl implements PrestadoraPMSMercadoService {

	@Autowired
	private PrestadoraPMSMercadoRepository prestadoraRepository;

	@Override
	public boolean checkIsPrestadoraPMS( List<Long> idPrestadoras ) {
		return prestadoraRepository.checkIsPrestadoraPMS( idPrestadoras );
	}

}
