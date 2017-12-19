package br.com.cleartech.pgmc.mgu.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

import br.com.cleartech.pgmc.mgu.enums.TipoOperadora;

@Audited
@Entity
@Table( name = "ACESSO_OPER" )
@SequenceGenerator( name = "SEQ_ACESSO_OPER", sequenceName = "SEQ_ACESSO_OPER", initialValue = 1, allocationSize = 1 )
public class AcessoOperadora implements Serializable {

	private static final long serialVersionUID = -8737270469349486935L;

	@Id
	@GeneratedValue( generator = "SEQ_ACESSO_OPER" )
	@Column( name = "PK_ACESSO_OPER" )
	private Long id;

	@Column( name = "TIPO_OPERADORA", precision = 1 )
	@Enumerated( EnumType.ORDINAL )
	private TipoOperadora tipoOperadora;


	public AcessoOperadora() {}

	public Long getId() {
		return id;
	}

	public void setId( Long id ) {
		this.id = id;
	}

	public TipoOperadora getTipoOperadora() {
		return tipoOperadora;
	}

	public void setTipoOperadora( TipoOperadora tipoOperadora ) {
		this.tipoOperadora = tipoOperadora;
	}

}
