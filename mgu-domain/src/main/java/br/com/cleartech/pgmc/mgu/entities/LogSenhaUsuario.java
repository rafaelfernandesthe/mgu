package br.com.cleartech.pgmc.mgu.entities;


import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Table(name="LOG_TROCA_SENHA")
@Entity
@SequenceGenerator(name = "SEQ_LOG_TROCA_SENHA", sequenceName = "SEQ_LOG_TROCA_SENHA", initialValue=1, allocationSize=1)
public class LogSenhaUsuario  implements Serializable {


	private static final long serialVersionUID = -8227649237228353761L;
	
	@Id
	@GeneratedValue(generator = "SEQ_LOG_TROCA_SENHA")
	@Column(name="LOG_TROCA_SENHA")
	private Long id;

    @ManyToOne
	@JoinColumn(name="PK_ID_USUARIO")
	private Usuario usuario;
    
    @Column(name="DT_ALTERACAO")
    private Timestamp dataAlteracao;
   
	@Column(name="NM_USUARIO_ALT")
	private String nomeUsuarioAlteracao;
	
	@Column(name="IP_ALTERACAO")
	private String ipAlteracao;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Timestamp getDataAlteracao() {
		return dataAlteracao;
	}

	public void setDataAlteracao(Timestamp dataAlteracao) {
		this.dataAlteracao = dataAlteracao;
	}

	public String getNomeUsuarioAlteracao() {
		return nomeUsuarioAlteracao;
	}

	public void setNomeUsuarioAlteracao(String nomeUsuarioAlteracao) {
		this.nomeUsuarioAlteracao = nomeUsuarioAlteracao;
	}

	public String getIpAlteracao() {
		return ipAlteracao;
	}

	public void setIpAlteracao(String ipAlteracao) {
		this.ipAlteracao = ipAlteracao;
	}	
}
