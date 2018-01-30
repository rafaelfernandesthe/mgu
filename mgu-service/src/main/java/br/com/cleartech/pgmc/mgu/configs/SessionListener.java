package br.com.cleartech.pgmc.mgu.configs;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionListener implements HttpSessionListener {

	@Override
	public void sessionCreated( HttpSessionEvent event ) {
		System.out.println( "session created" );
		event.getSession().setMaxInactiveInterval( 10 * 60 );
	}

	@Override
	public void sessionDestroyed( HttpSessionEvent event ) {
		System.out.println( "session destroyed" );
	}
}