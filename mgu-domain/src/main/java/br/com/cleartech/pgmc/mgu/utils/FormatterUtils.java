package br.com.cleartech.pgmc.mgu.utils;

import java.text.ParseException;

import javax.swing.text.MaskFormatter;

public class FormatterUtils {

	
	public static final String CPF = "###.###.###-##";
	public static final String CEP = "#####-###";
	public static final String CNPJ = "##.###.###/####-##";
	
	/*
	 * Exemplo formatar CPF 
	 * 		FormatterUtil.format(FormatterUtil.CPF,"33740931833");
	 */
	
	public static String format(String pattern, Object value) {        
		MaskFormatter mask;        
		try {           
			mask = new MaskFormatter(pattern);
			mask.setValueContainsLiteralCharacters(false);
			return mask.valueToString(value);       
		} 
		catch (ParseException e) {            
			throw new RuntimeException(e);        
		}    
	} 
}
