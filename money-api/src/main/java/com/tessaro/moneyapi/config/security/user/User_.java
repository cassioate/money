package com.tessaro.moneyapi.config.security.user;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(User.class)
public abstract class User_ {

	public static volatile SetAttribute<User, Integer> perfis;
	public static volatile SingularAttribute<User, String> password;
	public static volatile SingularAttribute<User, Long> id;
	public static volatile SingularAttribute<User, String> email;

	public static final String PERFIS = "perfis";
	public static final String PASSWORD = "password";
	public static final String ID = "id";
	public static final String EMAIL = "email";

}

