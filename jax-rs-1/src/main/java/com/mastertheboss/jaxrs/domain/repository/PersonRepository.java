package com.mastertheboss.jaxrs.domain.repository;


import com.mastertheboss.jaxrs.domain.entity.Person;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.ws.rs.WebApplicationException;
import java.util.List;


@ApplicationScoped
public class PersonRepository {

	@PersistenceContext
	private EntityManager entityManager;

	public List<Person> findAll() {
		return entityManager.createNamedQuery("Persons.findAll", Person.class)
				.getResultList();
	}

	public Person findPersonById(Long id) {
		Person person = entityManager.find(Person.class, id);
		if (person == null) {
			throw new WebApplicationException("Customer with id of " + id + " does not exist.", 404);
		}
		return person;
	}

	@Transactional
	public void updatePerson(Person person) {
		Person personToUpdate = findPersonById(person.getId());
		personToUpdate.setName(person.getName());
		personToUpdate.setBirthday(person.getBirthday());
		personToUpdate.setHeight(person.getHeight());
		personToUpdate.setPassportID(person.getPassportID());
		personToUpdate.setHairColor(person.getHairColor());
	}

	@Transactional
	public void createPerson(Person person) {
		entityManager.persist(person);
	}

	@Transactional
	public void deleteCustomer(Long personId) {
		Person c = findPersonById(personId);
		entityManager.remove(c);
	}


}
