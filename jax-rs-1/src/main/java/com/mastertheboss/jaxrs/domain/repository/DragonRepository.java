package com.mastertheboss.jaxrs.domain.repository;

import com.mastertheboss.jaxrs.domain.entity.Dragon;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.ws.rs.WebApplicationException;
import java.time.LocalDateTime;
import java.util.List;


@ApplicationScoped
public class DragonRepository {

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
	public void updateDragon(Dragon dragon) {
		Dragon dragonToUpdate = findDragonById(dragon.getId());
		dragonToUpdate.setName(dragon.getName());
		dragonToUpdate.setCoordinates(dragon.getCoordinates());
		dragonToUpdate.setCreationDate(dragon.getCreationDate());
		dragonToUpdate.setAge(dragon.getAge());
		dragonToUpdate.setColor(dragon.getColor());
		dragonToUpdate.setCharacter(dragon.getCharacter());
		dragonToUpdate.setKiller(dragon.getKiller());
		entityManager.refresh(dragonToUpdate);
	}

	@Transactional
	public void createDragon(Dragon dragon) {
		dragon.setCreationDate(LocalDateTime.now());
		entityManager.merge(dragon);
	}

	@Transactional
	public void deleteDragon(Integer dragonId) {
		Dragon c = findDragonById(dragonId);
		entityManager.remove(c);
	}

}
