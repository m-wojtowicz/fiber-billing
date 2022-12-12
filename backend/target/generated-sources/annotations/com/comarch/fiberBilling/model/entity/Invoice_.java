package com.comarch.fiberBilling.model.entity;

import java.sql.Date;
import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Invoice.class)
public abstract class Invoice_ {

	public static volatile SingularAttribute<Invoice, Date> dueDate;
	public static volatile SingularAttribute<Invoice, Long> id;
	public static volatile SingularAttribute<Invoice, ProviderData> dataProvider;
	public static volatile SingularAttribute<Invoice, Date> paymentDate;
	public static volatile SingularAttribute<Invoice, Timestamp> invoiceDate;
	public static volatile SingularAttribute<Invoice, Order> order;

	public static final String DUE_DATE = "dueDate";
	public static final String ID = "id";
	public static final String DATA_PROVIDER = "dataProvider";
	public static final String PAYMENT_DATE = "paymentDate";
	public static final String INVOICE_DATE = "invoiceDate";
	public static final String ORDER = "order";

}

