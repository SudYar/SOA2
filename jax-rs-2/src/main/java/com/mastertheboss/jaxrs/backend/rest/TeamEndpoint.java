package com.mastertheboss.jaxrs.backend.rest;


import com.mastertheboss.jaxrs.backend.conf.JNDIConfig;
import com.mastertheboss.jaxrs.backend.domain.entity.Cave;
import com.mastertheboss.jaxrs.backend.domain.entity.Person;
import com.mastertheboss.jaxrs.backend.domain.entity.Team;
import com.mastertheboss.jaxrs.backend.domain.repository.CaveRepository;
import com.mastertheboss.jaxrs.backend.domain.repository.TeamRepository;
import com.mastertheboss.jaxrs.ejb.service.PersonService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class TeamEndpoint {

	@Inject
	TeamRepository teamRepository;

	@Inject
	CaveRepository caveRepository;

	PersonService personService = JNDIConfig.personService();

	public void create(Long teamId, String teamName, Short teamSize, Long caveId,
						   Long[] personsId) {
		Cave cave = null;
		if (caveId != null) {
			cave = caveRepository.findCaveById(caveId);
		}
		List<Person> personArrayList = new ArrayList<>();
		if (personsId.length > teamSize) {
			throw new WebApplicationException("Людей больше чем максимальная величина группы", 401);
		}
		for (Long aLong : personsId) {
			Person person = personService.getPersonById(aLong);
			if (person == null) {
				throw new WebApplicationException("Человек с id " + aLong + " не найден", 404);
			}
			personArrayList.add(person);
		}
		Team team = Team.builder()
				.id(teamId)
				.name(teamName)
				.size(teamSize)
				.cave(cave)
				.personList(personArrayList.isEmpty() ? null : personArrayList)
				.build();
		teamRepository.createTeam(team);
	}

}