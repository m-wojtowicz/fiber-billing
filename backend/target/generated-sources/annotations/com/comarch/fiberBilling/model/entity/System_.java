package com.comarch.fiberBilling.model.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(System.class)
public abstract class System_ {

	public static volatile SingularAttribute<System, Boolean> isFiberExist;
	public static volatile SingularAttribute<System, Date> minimalActivationDate;
	public static volatile SingularAttribute<System, Boolean> ontExist;
	public static volatile SingularAttribute<System, Long> id;

	public static final String IS_FIBER_EXIST = "isFiberExist";
	public static final String MINIMAL_ACTIVATION_DATE = "minimalActivationDate";
	public static final String ONT_EXIST = "ontExist";
	public static final String ID = "id";

}

