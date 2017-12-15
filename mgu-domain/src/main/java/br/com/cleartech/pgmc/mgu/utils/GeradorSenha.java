package br.com.cleartech.pgmc.mgu.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Classe utilitaria para geracao de senha
 * @author mmarinho
 *
 */
public class GeradorSenha {

	private static final char[] ALL_CHARS = new char[62];
	private static final Random RANDOM = new Random();

	static {
		for (int i = 48, j = 0; i < 123; i++) {
			if (Character.isLetterOrDigit(i)) {
				ALL_CHARS[j] = (char) i;
				j++;
			}
		}
	}

	public static String getRandomPassword(final int length) {
		final char[] result = new char[length];
		for (int i = 0; i < length; i++) {
			result[i] = ALL_CHARS[RANDOM.nextInt(ALL_CHARS.length)];
		}
		return new String(result);
	}
	
	public static String md5(String senha){
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		BigInteger hash = new BigInteger(1, md.digest(senha.getBytes()));		
		return hash.toString(16);
	}	
	
	public static void main(String[] args) {
		List<String> lista = new ArrayList<String>();
//		for(int i = 0; i < 10 ; i++)
//			lista.add(getRandomPassword(8));
//		for(String ite : lista){
//			System.out.println(ite + " = "+md5(ite));
//		}	
		System.out.println(md5("Jhqibjtz"));
	}	
}