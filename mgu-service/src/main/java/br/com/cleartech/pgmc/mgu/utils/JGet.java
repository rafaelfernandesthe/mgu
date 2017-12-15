package br.com.cleartech.pgmc.mgu.utils;

/*
 * A simple Java class to provide functionality similar to Wget.
 *
 * Note: Could also strip out all of the html w/ jtidy.
 */

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class JGet {

	// milliseconds
	// 3s para cada aplicação(cred->mgu->ldap->dynamics)
	private static final int CONNECTION_TIMEOUT = 12000;
	private static final int READ_TIMEOUT = 12000;

	public static void tryUrl( String wsdlUrl ) throws Exception {

		URL u;
		InputStream is = null;
		DataInputStream dis;
		String s;

		try {
			System.out.println("trying connection to "+wsdlUrl);
			u = new URL( wsdlUrl );
			URLConnection con = u.openConnection();
			con.setConnectTimeout( CONNECTION_TIMEOUT );
			con.setReadTimeout( READ_TIMEOUT );
			is = con.getInputStream();
			dis = new DataInputStream( new BufferedInputStream( is ) );
			while ( ( s = dis.readLine() ) != null ) {
				System.out.println( s );
			}
		} catch ( MalformedURLException mue ) {
			System.err.println( "Ouch - a MalformedURLException happened." );
			mue.printStackTrace();
			throw mue;
		} catch ( IOException ioe ) {
			System.err.println( "Oops- an IOException happened." );
			ioe.printStackTrace();
			throw ioe;
		} finally {
			try {
				if ( is != null ) {
					is.close();
				}
			} catch ( IOException ioe ) {
				ioe.printStackTrace();
			}
		}

	}

}