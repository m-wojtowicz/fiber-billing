package com.comarch.fiberBilling.model.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(OrderItem.class)
public abstract class OrderItem_ {

	public static volatile SingularAttribute<OrderItem, String> orderItemName;
	public static volatile SingularAttribute<OrderItem, Long> id;
	public static volatile SingularAttribute<OrderItem, Date> activationDate;
	public static volatile SingularAttribute<OrderItem, Order> order;
	public static volatile SingularAttribute<OrderItem, String> status;

	public static final String ORDER_ITEM_NAME = "orderItemName";
	public static final String ID = "id";
	public static final String ACTIVATION_DATE = "activationDate";
	public static final String ORDER = "order";
	public static final String STATUS = "status";

}

