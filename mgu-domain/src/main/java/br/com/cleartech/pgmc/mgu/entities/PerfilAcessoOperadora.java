package br.com.cleartech.pgmc.mgu.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

@Audited
@Entity
@Table( name = "PERFIL_ACESSO_OPER" )
@SequenceGenerator( name = "SEQ_PERFIL_ACESSO_OPER", sequenceName = "SEQ_PERFIL_ACESSO_OPER", initialValue = 1, allocationSize = 1 )
public class PerfilAcessoOperadora implements Serializable {

	private static final long serialVersionUID = -26306740642687158L;

	@Id
	@GeneratedValue( generator = "SEQ_PERFIL_ACESSO_OPER" )
	@Column( name = "PK_PERFIL_ACESSO_OPER" )
	private Long id;

	@ManyToOne
	@JoinColumn( name = "PK_ID_PERFIL" )
	private Perfil perfil;

	@ManyToOne
	@JoinColumn( name = "PK_ACESSO_OPER" )
	private AcessoOperadora acessoOperadora;

	public Long getId() {
		return id;
	}

	public void setId( Long id ) {
		this.id = id;
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil( Perfil perfil ) {
		this.perfil = perfil;
	}

	public AcessoOperadora getAcessoOperadora() {
		return acessoOperadora;
	}

	public void setAcessoOperadora( AcessoOperadora acessoOperadora ) {
		this.acessoOperadora = acessoOperadora;
	}

}
