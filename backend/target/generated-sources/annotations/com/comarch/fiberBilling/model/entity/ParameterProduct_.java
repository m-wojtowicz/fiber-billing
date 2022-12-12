package com.comarch.fiberBilling.model.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ParameterProduct.class)
public abstract class ParameterProduct_ {

	public static volatile SingularAttribute<ParameterProduct, Product> product;
	public static volatile SingularAttribute<ParameterProduct, Parameter> parameter;
	public static volatile SingularAttribute<ParameterProduct, Long> id;

	public static final String PRODUCT = "product";
	public static final String PARAMETER = "parameter";
	public static final String ID = "id";

}

