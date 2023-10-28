package com.mastertheboss.jaxrs.domain.repository;

import com.mastertheboss.jaxrs.domain.dto.DragonDTO;
import com.mastertheboss.jaxrs.domain.entity.Dragon;
import com.mastertheboss.jaxrs.domain.entity.Person;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.ws.rs.WebApplicationException;
import java.time.LocalDateTime;
import java.util.List;


@ApplicationScoped
public class DragonRepository {

	@Inject
	PersonRepository personRepository;

	@PersistenceContext
	private EntityManager entityManager;

	public List<Dragon> findAll() {
		return entityManager.createNamedQuery("Dragons.findAll", Dragon.class)
				.getResultList();
	}

	public Dragon findDragonById(Integer id) {
		Dragon dragon = entityManager.find(Dragon.class, id);
		if (dragon == null) {
			throw new WebApplicationException("Dragon with id of " + id + " does not exist.", 404);
		}
		return dragon;
	}

	@Transactional
	public void updateDragon(DragonDTO dragonDTO) {
		Person killer = null;
		if (dragonDTO.getKiller() != null) {
			killer = personRepository.findPersonById(dragonDTO.getKiller());
		}
		Dragon dragonToUpdate = findDragonById(dragonDTO.getId());
		dragonToUpdate.setId(dragonDTO.getId());
		dragonToUpdate.setName(dragonDTO.getName());
		dragonToUpdate.setCoordinates(dragonDTO.getCoordinates());
		dragonToUpdate.setAge(dragonDTO.getAge());
		dragonToUpdate.setColor(dragonDTO.getColor());
		dragonToUpdate.setCharacter(dragonDTO.getCharacter());
		dragonToUpdate.setKiller(killer);
		entityManager.merge(dragonToUpdate);
	}

	@Transactional
	public void createDragon(Dragon dragon) {
		dragon.setCreationDate(LocalDateTime.now());
		entityManager.merge(dragon);
	}

	@Transactional
	public void deleteDragon(Integer dragonId) {
		Dragon dragon = findDragonById(dragonId);
		dragon.setKiller(null);
		entityManager.remove(dragon);
	}

}
