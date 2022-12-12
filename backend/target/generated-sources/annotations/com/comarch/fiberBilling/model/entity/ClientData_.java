package com.comarch.fiberBilling.model.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ClientData.class)
public abstract class ClientData_ {

	public static volatile SingularAttribute<ClientData, String> emailAddress;
	public static volatile SingularAttribute<ClientData, Address> address;
	public static volatile SingularAttribute<ClientData, ClientType> clientType;
	public static volatile SingularAttribute<ClientData, String> surname;
	public static volatile SingularAttribute<ClientData, String> name;
	public static volatile SingularAttribute<ClientData, Long> id;
	public static volatile SingularAttribute<ClientData, String> login;

	public static final String EMAIL_ADDRESS = "emailAddress";
	public static final String ADDRESS = "address";
	public static final String CLIENT_TYPE = "clientType";
	public static final String SURNAME = "surname";
	public static final String NAME = "name";
	public static final String ID = "id";
	public static final String LOGIN = "login";

}

