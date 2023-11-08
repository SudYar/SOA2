package com.mastertheboss.jaxrs.rest;

import com.mastertheboss.jaxrs.domain.dto.DragonDTO;
import com.mastertheboss.jaxrs.domain.dto.DragonTypeDTO;
import com.mastertheboss.jaxrs.domain.entity.Dragon;
import com.mastertheboss.jaxrs.domain.entity.DragonType;
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
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Objects;

@Path("dragons")
@ApplicationScoped
@Produces("application/json")
@Consumes("application/json")
public class DragonEndpoint {

	@Inject
	DragonRepository dragonRepository;

	@Inject
	PersonRepository personRepository;

	@GET
	public List<Dragon> getAll() {
		return dragonRepository.findAll();
	}

	@GET
	@Path("{id}")
	public Dragon getDragonById(@PathParam("id") Integer id) {
		return dragonRepository.findDragonById(id);
	}

	@POST
	public Response create(DragonDTO dragonDTO) {
		Person killer = null;
		if (dragonDTO.getKiller() != null) {
			killer = personRepository.findPersonById(dragonDTO.getKiller());
		}
		Dragon dragon = Dragon.builder()
				.name(dragonDTO.getName())
				.age(dragonDTO.getAge())
				.color(dragonDTO.getColor())
				.killer(killer)
				.coordinates(dragonDTO.getCoordinates())
				.type(dragonDTO.getType())
				.character(dragonDTO.getCharacter()).build();
		dragonRepository.createDragon(dragon);
		return Response.status(201).build();
	}

	@PUT
	public Response update(DragonDTO dragonDTO) {
		Dragon dragon;
		if (dragonDTO.getId() != null) {
			dragon = dragonRepository.findDragonById(dragonDTO.getId());
		} else {
			throw new WebApplicationException("ID is Empty", 400);
		}
		if (dragon == null) {
			throw new WebApplicationException("Wrong REQUEST", 400);
		}
		dragonRepository.updateDragon(dragonDTO);
		return Response.status(204).build();
	}

	@DELETE
	@Path("{id}")
	public Response delete(@PathParam("id") Integer dragonId) {
		dragonRepository.deleteDragon(dragonId);
		return Response.status(204).build();
	}

	@POST
	@Path("delete-killed")
	public Response deleteKilled(Person person) {
		Person killer;
		if (person.getId() != null) {
			killer = personRepository.findPersonById(person.getId());
		} else {
			throw new WebApplicationException("ID is Empty", 400);
		}
		if (killer == null) {
			throw new WebApplicationException("Wrong REQUEST", 400);
		}
		List<Dragon> allDragons = dragonRepository.findAll();
		for (Dragon dragon : allDragons) {
			if (dragon.getKiller() != null && Objects.equals(dragon.getKiller().getId(), person.getId())) {
				dragonRepository.deleteDragon(dragon.getId());
			}
		}
		return Response.status(204).build();
	}

	@POST
	@Path("delete-by-type")
	public Response deleteByType(DragonTypeDTO dragonTypeDTO) {
		List<Dragon> allDragons = dragonRepository.findAll();
		for (Dragon dragon : allDragons) {
			if (dragon.getType() != null &&
					Objects.equals(dragon.getType(), DragonType.fromValue(dragonTypeDTO.getValue()))) {
				dragonRepository.deleteDragon(dragon.getId());
			}
		}
		return Response.status(204).build();
	}

	@GET
	@Path("get-by-max-color")
	public Dragon getByMaxColor() {
		List<Dragon> allDragons = dragonRepository.findAll();
		Dragon maxColorDragon = null;
		for (Dragon dragon : allDragons) {
			if (maxColorDragon != null) {
				if (dragon.getColor().getPriority() > maxColorDragon.getColor().getPriority()) {
					maxColorDragon = dragon;
				}
			} else {
				maxColorDragon = dragon;
			}
		}
		if (maxColorDragon == null) {
			throw new WebApplicationException("Dragon not found", 404);
		}
		return maxColorDragon;
	}

}
