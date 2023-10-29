package com.mastertheboss.jaxrs.rest;


import com.mastertheboss.jaxrs.domain.entity.Dragon;
import com.mastertheboss.jaxrs.domain.entity.Person;
import com.mastertheboss.jaxrs.domain.repository.DragonRepository;
import com.mastertheboss.jaxrs.domain.repository.PersonRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Objects;


@Path("persons")
@ApplicationScoped
@Produces("application/json")
@Consumes("application/json")
public class PersonEndpoint {

	@Inject
	PersonRepository personRepository;

	@Inject
	DragonRepository dragonRepository;

	@GET
	public List<Person> getAll() {
		return personRepository.findAll();
	}

	@GET
	@Path("{id}")
	public Person getPersonById(@PathParam("id") Long id) {
		return personRepository.findPersonById(id);
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
	@Path("{id}")
	public Response delete(@PathParam("id") Long personId) {
		List<Dragon> allDragons = dragonRepository.findAll();
		for (Dragon dragon : allDragons) {
			if (dragon.getKiller() != null && Objects.equals(dragon.getKiller().getId(), personId)) {
				throw new WebApplicationException("Wrong REQUEST, person is used", 400);
			}
		}
		personRepository.deleteCustomer(personId);
		return Response.status(204).build();
	}

}