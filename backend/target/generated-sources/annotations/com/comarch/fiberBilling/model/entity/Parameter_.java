package com.comarch.fiberBilling.model.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Parameter.class)
public abstract class Parameter_ {

	public static volatile ListAttribute<Parameter, ParameterDetail> parameterDetail;
	public static volatile SingularAttribute<Parameter, String> name;
	public static volatile SingularAttribute<Parameter, Long> id;

	public static final String PARAMETER_DETAIL = "parameterDetail";
	public static final String NAME = "name";
	public static final String ID = "id";

}

