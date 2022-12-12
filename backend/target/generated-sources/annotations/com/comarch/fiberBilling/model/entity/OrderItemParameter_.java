package com.comarch.fiberBilling.model.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(OrderItemParameter.class)
public abstract class OrderItemParameter_ {

	public static volatile SingularAttribute<OrderItemParameter, ParameterDetail> parameterDetail;
	public static volatile SingularAttribute<OrderItemParameter, OrderItem> orderItem;
	public static volatile SingularAttribute<OrderItemParameter, Long> id;
	public static volatile SingularAttribute<OrderItemParameter, Boolean> payed;

	public static final String PARAMETER_DETAIL = "parameterDetail";
	public static final String ORDER_ITEM = "orderItem";
	public static final String ID = "id";
	public static final String PAYED = "payed";

}

