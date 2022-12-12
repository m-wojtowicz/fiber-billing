package com.comarch.fiberBilling.model.entity;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ProviderData.class)
public abstract class ProviderData_ {

	public static volatile SingularAttribute<ProviderData, BigDecimal> bankAccount;
	public static volatile SingularAttribute<ProviderData, String> address;
	public static volatile SingularAttribute<ProviderData, BigDecimal> taxId;
	public static volatile SingularAttribute<ProviderData, Long> id;
	public static volatile SingularAttribute<ProviderData, String> providerName;

	public static final String BANK_ACCOUNT = "bankAccount";
	public static final String ADDRESS = "address";
	public static final String TAX_ID = "taxId";
	public static final String ID = "id";
	public static final String PROVIDER_NAME = "providerName";

}

