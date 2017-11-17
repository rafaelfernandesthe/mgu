package br.com.cleartech.pgmc.mgu.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QHistRevisao is a Querydsl query type for HistRevisao
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QHistRevisao extends EntityPathBase<HistRevisao> {

    private static final long serialVersionUID = 150994506L;

    public static final QHistRevisao histRevisao = new QHistRevisao("histRevisao");

    public final NumberPath<Long> cdRevisao = createNumber("cdRevisao", Long.class);

    public final DateTimePath<java.util.Date> dtRevisao = createDateTime("dtRevisao", java.util.Date.class);

    public final StringPath userName = createString("userName");

    public QHistRevisao(String variable) {
        super(HistRevisao.class, forVariable(variable));
    }

    public QHistRevisao(Path<? extends HistRevisao> path) {
        super(path.getType(), path.getMetadata());
    }

    public QHistRevisao(PathMetadata metadata) {
        super(HistRevisao.class, metadata);
    }

}

