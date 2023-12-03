package com.mastertheboss.jaxrs.domain.repository;

import com.mastertheboss.jaxrs.domain.entity.Cave;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.ws.rs.WebApplicationException;
import java.util.List;


@ApplicationScoped
public class CaveRepository {

	@PersistenceContext
	private EntityManager entityManager;

	public List<Cave> findAll() {
		return entityManager.createNamedQuery("Caves.findAll", Cave.class)
				.getResultList();
	}

	public Cave findCaveById(Long id) {
		if (id == null) {
			return null;
		}
		Cave cave = entityManager.find(Cave.class, id);
		if (cave == null) {
			throw new WebApplicationException("Пещера с id " + id + " не найдена.", 404);
		}
		return cave;
	}

	@Transactional
	public void updateCave(Cave cave) {
		Cave caveToUpdate = findCaveById(cave.getId());
		caveToUpdate.setCoordinates(cave.getCoordinates());
		entityManager.refresh(caveToUpdate);
	}

	@Transactional
	public void createCave(Cave cave) {
		entityManager.merge(cave);
	}

	@Transactional
	public void deleteCave(Long caveId) {
		Cave c = findCaveById(caveId);
		entityManager.remove(c);
	}

	@Transactional
	public void deleteAll() {
		List<Cave> caves = this.findAll();
		for (Cave cave : caves) {
			entityManager.remove(cave);
		}
	}

}
