package br.com.cleartech.pgmc.mgu.enums;

public enum StatusDynamics {
	
	SUCESSO("0"),
	ERRO("1")
	;
	
	String value;
	
	StatusDynamics(String value){
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
}
