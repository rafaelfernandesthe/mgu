package br.com.cleartech.pgmc.mgu.integration.ws.requests;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name="usuario")
@XmlType(name="dados",  propOrder = { "nome", "telefone", "email", "ipOrigem","grupoPrestadora","perfis" })
public class UsuarioBean {
	
	private String nome;
	private String telefone;
	private String email;
	private String ipOrigem;
	private GrupoPrestadoraBean  grupoPrestadora;
	private PerfisBean perfis;
	
	@XmlElement(name="nome")
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@XmlElement(name="telefone")
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	@XmlElement(name="email")
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@XmlElement(name="ip_origem")
	public String getIpOrigem() {
		return ipOrigem;
	}
	public void setIpOrigem(String iOrigem) {
		this.ipOrigem = iOrigem;
	}
	
	@XmlElement(name="grupo_prestadora")
	public GrupoPrestadoraBean getGrupoPrestadora() {
		return grupoPrestadora;
	}
	public void setGrupoPrestadora(GrupoPrestadoraBean grupoPrestadora) {
		this.grupoPrestadora = grupoPrestadora;
	}
	
	@XmlElement(name="perfis")
	public PerfisBean getPerfis() {
		return perfis;
	}
	public void setPerfis(PerfisBean perfis) {
		this.perfis = perfis;
	}
	
	


}
