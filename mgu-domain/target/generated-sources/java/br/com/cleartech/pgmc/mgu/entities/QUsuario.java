package br.com.cleartech.pgmc.mgu.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUsuario is a Querydsl query type for Usuario
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QUsuario extends EntityPathBase<Usuario> {

    private static final long serialVersionUID = -370832001L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUsuario usuario = new QUsuario("usuario");

    public final StringPath dcCargo = createString("dcCargo");

    public final StringPath dcEmail = createString("dcEmail");

    public final StringPath dcIpOrigem = createString("dcIpOrigem");

    public final StringPath dcTelefone = createString("dcTelefone");

    public final StringPath dcTelefoneFixo = createString("dcTelefoneFixo");

    public final StringPath dcUsername = createString("dcUsername");

    public final QUsuario delegado;

    public final ListPath<Delegado, QDelegado> delegadosComuns = this.<Delegado, QDelegado>createList("delegadosComuns", Delegado.class, QDelegado.class, PathInits.DIRECT2);

    public final ListPath<Delegado, QDelegado> delegadosMaster = this.<Delegado, QDelegado>createList("delegadosMaster", Delegado.class, QDelegado.class, PathInits.DIRECT2);

    public final EnumPath<br.com.cleartech.pgmc.mgu.enums.SimNaoEnum> flArovado = createEnum("flArovado", br.com.cleartech.pgmc.mgu.enums.SimNaoEnum.class);

    public final EnumPath<br.com.cleartech.pgmc.mgu.enums.BloqueioUsuario> flBloqueio = createEnum("flBloqueio", br.com.cleartech.pgmc.mgu.enums.BloqueioUsuario.class);

    public final EnumPath<br.com.cleartech.pgmc.mgu.enums.SimNaoEnum> flEnviarDynamics = createEnum("flEnviarDynamics", br.com.cleartech.pgmc.mgu.enums.SimNaoEnum.class);

    public final NumberPath<Integer> flEnvioEmail = createNumber("flEnvioEmail", Integer.class);

    public final EnumPath<br.com.cleartech.pgmc.mgu.enums.SimNaoEnum> flMaster = createEnum("flMaster", br.com.cleartech.pgmc.mgu.enums.SimNaoEnum.class);

    public final EnumPath<br.com.cleartech.pgmc.mgu.enums.SimNaoEnum> flPrimeiroAcesso = createEnum("flPrimeiroAcesso", br.com.cleartech.pgmc.mgu.enums.SimNaoEnum.class);

    public final EnumPath<br.com.cleartech.pgmc.mgu.enums.SimNaoEnum> flPrimeiroAcessoSNOA = createEnum("flPrimeiroAcessoSNOA", br.com.cleartech.pgmc.mgu.enums.SimNaoEnum.class);

    public final NumberPath<Integer> flUsuarioSistema = createNumber("flUsuarioSistema", Integer.class);

    public final ListPath<GrupoPerfil, QGrupoPerfil> grupoPerfis = this.<GrupoPerfil, QGrupoPerfil>createList("grupoPerfis", GrupoPerfil.class, QGrupoPerfil.class, PathInits.DIRECT2);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QNivelEscalonamento nivelEscalonamento;

    public final StringPath nmUsuario = createString("nmUsuario");

    public final StringPath nuCpf = createString("nuCpf");

    public final QPerfil perfil;

    public final ListPath<Prestadora, QPrestadora> prestadoras = this.<Prestadora, QPrestadora>createList("prestadoras", Prestadora.class, QPrestadora.class, PathInits.DIRECT2);

    public final NumberPath<Long> qtTentativaAcesso = createNumber("qtTentativaAcesso", Long.class);

    public final DateTimePath<java.util.Date> ultimaTrocaSenha = createDateTime("ultimaTrocaSenha", java.util.Date.class);

    public final DateTimePath<java.util.Date> ultimoAcesso = createDateTime("ultimoAcesso", java.util.Date.class);

    public final ListPath<Usuario, QUsuario> usuarios = this.<Usuario, QUsuario>createList("usuarios", Usuario.class, QUsuario.class, PathInits.DIRECT2);

    public QUsuario(String variable) {
        this(Usuario.class, forVariable(variable), INITS);
    }

    public QUsuario(Path<? extends Usuario> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUsuario(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUsuario(PathMetadata metadata, PathInits inits) {
        this(Usuario.class, metadata, inits);
    }

    public QUsuario(Class<? extends Usuario> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.delegado = inits.isInitialized("delegado") ? new QUsuario(forProperty("delegado"), inits.get("delegado")) : null;
        this.nivelEscalonamento = inits.isInitialized("nivelEscalonamento") ? new QNivelEscalonamento(forProperty("nivelEscalonamento")) : null;
        this.perfil = inits.isInitialized("perfil") ? new QPerfil(forProperty("perfil"), inits.get("perfil")) : null;
    }

}

