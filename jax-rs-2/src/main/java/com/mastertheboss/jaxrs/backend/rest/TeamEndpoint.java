package com.mastertheboss.jaxrs.backend.rest;


import com.mastertheboss.jaxrs.backend.conf.JNDIConfig;
import com.mastertheboss.jaxrs.backend.domain.entity.Cave;
import com.mastertheboss.jaxrs.backend.domain.entity.Person;
import com.mastertheboss.jaxrs.backend.domain.entity.Team;
import com.mastertheboss.jaxrs.backend.domain.repository.CaveRepository;
import com.mastertheboss.jaxrs.backend.domain.repository.TeamRepository;
import com.mastertheboss.jaxrs.ejb.client.PersonClient;
import com.mastertheboss.jaxrs.ejb.service.PersonService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;


@Path("teams")
@ApplicationScoped
@Produces("application/json")
@Consumes("application/json")
public class TeamEndpoint {

	@Inject
	TeamRepository teamRepository;

	@Inject
	CaveRepository caveRepository;

	PersonService personService = JNDIConfig.personService();

	@GET
	public List<Team> getAll() {
		return teamRepository.findAll();
	}

	@POST
	@Path("create/{team-id}/{team-name}/{team-size}/{start-cave-id}")
	public Response create(@PathParam("team-id") Long teamId, @PathParam("team-name") String teamName,
						   @PathParam("team-size") Short teamSize, @PathParam("start-cave-id") Long caveId,
						   Long[] personsId) {
		Cave cave = null;
		if (caveId!= null) {
			cave = caveRepository.findCaveById(caveId);
		}
		List<Person> personArrayList = new ArrayList<>();
		if (personsId.length > teamSize)
			throw new WebApplicationException("Людей больше чем максимальная величина группы", 401);
		for (Long aLong : personsId) {
			Person person = personService.getPersonById(aLong);
			if (person == null) throw new WebApplicationException("Человек с id " + aLong + " не найден", 404);
			personArrayList.add(person);
		}
		Team team = Team.builder()
				.id(teamId)
				.name(teamName)
				.size(teamSize)
				.cave(cave)
				.personList(personArrayList.isEmpty()? null:personArrayList)
				.build();
		teamRepository.createTeam(team);
		return Response.status(201).build();
	}

	@POST
	@Path("{team-id}/move-to-cave/{cave-id}")
	public Response move(@PathParam("team-id") Long teamId, @PathParam("cave-id") Long caveId) {
		teamRepository.changeCave(teamId, caveId);
		return Response.status(201).build();
	}

	@PUT
	public Response update(Person person) {
//		personRepository.updatePerson(person);
		return Response.status(204).build();
	}

	@DELETE
	public Response delete(@QueryParam("id") Long personId) {
//		personRepository.deleteTeam(personId);
		return Response.status(204).build();
	}

	@DELETE
	@Path("deleteAll")
	public Response deleteAll() {
		teamRepository.deleteAll();
		return Response.status(204).build();
	}

}