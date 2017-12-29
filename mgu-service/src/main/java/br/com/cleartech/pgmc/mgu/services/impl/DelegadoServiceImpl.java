package br.com.cleartech.pgmc.mgu.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cleartech.pgmc.mgu.entities.Delegado;
import br.com.cleartech.pgmc.mgu.entities.Usuario;
import br.com.cleartech.pgmc.mgu.repositories.DelegadoRepository;
import br.com.cleartech.pgmc.mgu.services.DelegadoService;

@Service
public class DelegadoServiceImpl implements DelegadoService {

	@Autowired
	private DelegadoRepository delegadoRepository;

	@Override
	public Delegado findByUsuarioComumDcUsernameAndPrestadoraId( String comumUsername, Long idPrestadora ) {
		return delegadoRepository.findByUsuarioComumDcUsernameAndPrestadoraId( comumUsername, idPrestadora );
	}

	@Override
	public Delegado findByUsuarioComumDcUsername( String comumUsername ) {
		return delegadoRepository.findByUsuarioComumDcUsername( comumUsername );
	}

	@Override
	public void removerDelegadoDeUsuarioMaster( Usuario usuarioMaster ) {
		Delegado delegado = delegadoRepository.findByUsuarioMasterIdAndUsuarioComumId( usuarioMaster.getId(), usuarioMaster.getDelegado().getId() );
		delegadoRepository.delete( delegado.getId() );
	}

	@Override
	public void salvar( Delegado delegado ) {
		delegadoRepository.save( delegado );
	}

}
