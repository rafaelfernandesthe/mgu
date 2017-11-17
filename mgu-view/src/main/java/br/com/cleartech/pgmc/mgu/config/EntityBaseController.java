package br.com.cleartech.pgmc.mgu.config;

import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.servlet.ModelAndView;

public abstract class EntityBaseController {

	protected String thisModel;

	@InitBinder
	public abstract void setThisModel();

	public ModelAndView getThisModel() {
		return thisModel != null ? new ModelAndView( thisModel ) : null;
	}

}