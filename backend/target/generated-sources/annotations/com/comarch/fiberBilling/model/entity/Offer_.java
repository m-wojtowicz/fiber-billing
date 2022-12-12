package com.comarch.fiberBilling.model.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Offer.class)
public abstract class Offer_ {

	public static volatile SingularAttribute<Offer, Product> product;
	public static volatile SingularAttribute<Offer, String> offerName;
	public static volatile SingularAttribute<Offer, Long> id;

	public static final String PRODUCT = "product";
	public static final String OFFER_NAME = "offerName";
	public static final String ID = "id";

}

