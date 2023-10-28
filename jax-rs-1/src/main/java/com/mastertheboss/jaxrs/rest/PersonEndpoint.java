package com.mastertheboss.jaxrs.rest;


import com.mastertheboss.jaxrs.domain.entity.Person;
import com.mastertheboss.jaxrs.domain.repository.PersonRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import java.util.List;


@Path("persons")
@ApplicationScoped
@Produces("application/json")
@Consumes("application/json")
public class PersonEndpoint {

	@Inject
	PersonRepository personRepository;

	@GET
	public List<Person> getAll() {
		return personRepository.findAll();
	}

	@POST
	public Response create(Person person) {
		personRepository.createPerson(person);
		return Response.status(201).build();
	}

	@PUT
	public Response update(Person person) {
		personRepository.updatePerson(person);
		return Response.status(204).build();
	}

	@DELETE
	public Response delete(@QueryParam("id") Long personId) {
		personRepository.deleteCustomer(personId);
		return Response.status(204).build();
	}

}