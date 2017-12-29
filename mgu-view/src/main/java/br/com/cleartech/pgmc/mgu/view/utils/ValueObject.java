package br.com.cleartech.pgmc.mgu.view.utils;

import java.io.Serializable;

public class ValueObject implements Serializable {

	private static final long serialVersionUID = -7352439148197277228L;

	private String value;

	private String label;

	public ValueObject() {}

	public ValueObject( String value, String label ) {
		this.value = value;
		this.label = label;
	}

	public String getValue() {
		return value;
	}

	public void setValue( String value ) {
		this.value = value;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel( String label ) {
		this.label = label;
	}

}
