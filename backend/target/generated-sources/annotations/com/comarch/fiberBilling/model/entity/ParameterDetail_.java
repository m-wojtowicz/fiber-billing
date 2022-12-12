package com.comarch.fiberBilling.model.entity;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ParameterDetail.class)
public abstract class ParameterDetail_ {

	public static volatile SingularAttribute<ParameterDetail, BigDecimal> priceRegular;
	public static volatile SingularAttribute<ParameterDetail, Parameter> parameter;
	public static volatile SingularAttribute<ParameterDetail, BigDecimal> priceBusiness;
	public static volatile SingularAttribute<ParameterDetail, Boolean> monthly;
	public static volatile SingularAttribute<ParameterDetail, Date> activeTo;
	public static volatile SingularAttribute<ParameterDetail, Long> id;
	public static volatile SingularAttribute<ParameterDetail, String> value;
	public static volatile SingularAttribute<ParameterDetail, Date> activeFrom;

	public static final String PRICE_REGULAR = "priceRegular";
	public static final String PARAMETER = "parameter";
	public static final String PRICE_BUSINESS = "priceBusiness";
	public static final String MONTHLY = "monthly";
	public static final String ACTIVE_TO = "activeTo";
	public static final String ID = "id";
	public static final String VALUE = "value";
	public static final String ACTIVE_FROM = "activeFrom";

}

