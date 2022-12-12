package com.comarch.fiberBilling.model.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ClientType.class)
public abstract class ClientType_ {

	public static volatile SingularAttribute<ClientType, Long> id;
	public static volatile SingularAttribute<ClientType, String> type;
	public static volatile SetAttribute<ClientType, ClientData> clientDataSet;

	public static final String ID = "id";
	public static final String TYPE = "type";
	public static final String CLIENT_DATA_SET = "clientDataSet";

}

