package br.com.cleartech.pgmc.mgu.listeners;

import java.util.Date;

import org.hibernate.envers.RevisionListener;
import org.springframework.security.core.context.SecurityContextHolder;

import br.com.cleartech.pgmc.mgu.entities.HistRevisao;

public class HistRevisaoListener implements RevisionListener {

	public void newRevision(Object objetoRevisao) {
		HistRevisao revisao = (HistRevisao) objetoRevisao;		
		if(SecurityContextHolder.getContext().getAuthentication()!= null && !"anonymousUser".equalsIgnoreCase(SecurityContextHolder.getContext().getAuthentication().getName()))
			revisao.setUserName(SecurityContextHolder.getContext().getAuthentication().getName());		
		else
			revisao.setUserName("REST");
		revisao.setDtRevisao(new Date());		
	}
}