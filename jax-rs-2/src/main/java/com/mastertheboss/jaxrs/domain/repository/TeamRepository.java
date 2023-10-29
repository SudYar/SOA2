package com.mastertheboss.jaxrs.domain.repository;


import com.mastertheboss.jaxrs.domain.entity.Team;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.ws.rs.WebApplicationException;
import java.util.List;


@ApplicationScoped
public class TeamRepository {

	@PersistenceContext
	private EntityManager entityManager;

	public List<Team> findAll() {
		return entityManager.createNamedQuery("Teams.findAll", Team.class)
				.getResultList();
	}

	public Team findTeamById(Long id) {
		Team team = entityManager.find(Team.class, id);
		if (team == null) {
			throw new WebApplicationException("Team with id of " + id + " does not exist.", 404);
		}
		return team;
	}

	@Transactional
	public void updateTeam(Team team) {
		Team teamToUpdate = findTeamById(team.getId());
		teamToUpdate.setName(team.getName());
		teamToUpdate.setSize(team.getSize());
		teamToUpdate.setCave(team.getCave());
		entityManager.refresh(teamToUpdate);
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


}
