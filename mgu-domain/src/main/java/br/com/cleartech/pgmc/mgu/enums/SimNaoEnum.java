package br.com.cleartech.pgmc.mgu.enums;

public enum SimNaoEnum {

	NAO(0),
	SIM(1); 

	private Integer value;

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	SimNaoEnum(Integer value) {
		this.value = value;
	}

	@Override
	public String toString() {
		switch (this) {
		case SIM:
			return "Sim";

		case NAO:
			return "Não";

		default:
			return "---";
		}
	}
	
	public String getI18n() {
		switch (this) {
		case SIM:
			return "sistema.simNaoEnum.sim";

		case NAO:
			return "sistema.simNaoEnum.nao";

		default:
			return "---";
		}
	}

	public static Integer toInt(SimNaoEnum enumObject) {
		if (enumObject == null) {
			return null;
		}
		return enumObject.value;
	}

	public Integer toInt() {
		return value;
	}

	public static SimNaoEnum fromInt(Integer value) {
		if (value == null) {
			return null;
		}

		switch (value) {
		case 1:
			return SIM;
		case 0:
			return NAO;
		default:
			return null;
		}
	}

	public static SimNaoEnum fromBoolean(Boolean value) {
		if (value == null)
			return null;

		if (value) {
			return SIM;
		} else {
			return NAO;
		}
	}

	public static SimNaoEnum fromString(String desc) {
		if (desc.equalsIgnoreCase("SIM")) {
			return SIM;
		} else if (desc.equalsIgnoreCase("NÃO")) {
			return NAO;
		}
		return null;
	}

	public static String fromShortString(String desc) {
		if (desc.equalsIgnoreCase("S")) {
			return "SIM";
		} else if (desc.equalsIgnoreCase("N")) {
			return "NÃO";
		}
		return null;
	}

	public Boolean toBoolean() {
		switch (this) {
		case SIM:
			return true;

		case NAO:
			return false;
		default:
			return null;
		}
	}
}
