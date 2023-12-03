package com.mastertheboss.jaxrs.ejb.service;

import com.mastertheboss.jaxrs.backend.domain.entity.Person;
import com.mastertheboss.jaxrs.ejb.client.PersonClient;
import org.jboss.ejb3.annotation.Pool;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
@Pool("slsb-strict-max-pool")
public class PersonServiceImpl implements PersonService {

	@EJB
	private PersonClient personClient;

	@Override
	public Person getPersonById(Long personId) {
		return personClient.getPersonById(personId);
	}

}
