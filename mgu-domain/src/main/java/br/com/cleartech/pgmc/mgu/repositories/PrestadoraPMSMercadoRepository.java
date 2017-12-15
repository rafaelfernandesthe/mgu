package br.com.cleartech.pgmc.mgu.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import br.com.cleartech.pgmc.mgu.entities.PrestadoraPMSMercado;

@NoRepositoryBean
public interface PrestadoraPMSMercadoRepository extends CrudRepository<PrestadoraPMSMercado, Long> {

	boolean checkIsPrestadoraPMS( List<Long> idPrestadoras );
}
