package com.comarch.fiberBilling.model.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Order.class)
public abstract class Order_ {

	public static volatile SingularAttribute<Order, Date> orderEndDate;
	public static volatile SingularAttribute<Order, System> system;
	public static volatile SingularAttribute<Order, Date> orderStartDate;
	public static volatile SingularAttribute<Order, String> businessKey;
	public static volatile SingularAttribute<Order, String> orderStatus;
	public static volatile SingularAttribute<Order, Long> id;
	public static volatile SingularAttribute<Order, ClientData> clientData;

	public static final String ORDER_END_DATE = "orderEndDate";
	public static final String SYSTEM = "system";
	public static final String ORDER_START_DATE = "orderStartDate";
	public static final String BUSINESS_KEY = "businessKey";
	public static final String ORDER_STATUS = "orderStatus";
	public static final String ID = "id";
	public static final String CLIENT_DATA = "clientData";

}

