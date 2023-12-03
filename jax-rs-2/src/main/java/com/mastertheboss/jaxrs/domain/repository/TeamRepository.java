package com.mastertheboss.jaxrs.domain.repository;


import com.mastertheboss.jaxrs.domain.entity.Cave;
import com.mastertheboss.jaxrs.domain.entity.Team;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.ws.rs.WebApplicationException;
import java.util.List;


@ApplicationScoped
public class TeamRepository {

	@Inject
	CaveRepository caveRepository;
	@PersistenceContext
	private EntityManager entityManager;

	public List<Team> findAll() {
		return entityManager.createNamedQuery("Teams.findAll", Team.class)
				.getResultList();
	}

	public Team findTeamById(Long id) {
		if (id == null) {
			return null;
		}
		Team team = entityManager.find(Team.class, id);
		if (team == null) {
			throw new WebApplicationException("Группа с id " + id + " не найдена.", 404);
		}
		return team;
	}

	@Transactional
	public void updateTeam(Team team) {
		Team teamToUpdate = findTeamById(team.getId());
		teamToUpdate.setName(team.getName());
		teamToUpdate.setSize(team.getSize());
		teamToUpdate.setCave(team.getCave());
	}

	@Transactional
	public void changeCave(Long teamId, Long caveId) {
		Team team = findTeamById(teamId);
		if (team == null) {
			throw new WebApplicationException("Группа с id " + teamId + " не найдена.", 404);
		}
		Cave newCave = caveRepository.findCaveById(caveId);
		if (newCave == null) {
			throw new WebApplicationException("Пещера с id " + caveId + " не найдена.", 404);
		}
		team.setCave(newCave);
	}

	@Transactional
	public void createTeam(Team team) {
		entityManager.merge(team);
	}

	@Transactional
	public void deleteTeam(Long teamId) {
		Team team = findTeamById(teamId);
		entityManager.remove(team);
	}

	@Transactional
	public void deleteAll() {
		List<Team> allTeams = this.findAll();
		for (Team team : allTeams) {
			entityManager.remove(team);
		}
	}


}
