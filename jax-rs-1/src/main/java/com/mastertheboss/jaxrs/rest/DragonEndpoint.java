package com.mastertheboss.jaxrs.rest;

import com.mastertheboss.jaxrs.domain.entity.Dragon;
import com.mastertheboss.jaxrs.domain.repository.DragonRepository;

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

@Path("dragons")
@ApplicationScoped
@Produces("application/json")
@Consumes("application/json")
public class DragonEndpoint {

	@Inject
	DragonRepository dragonRepository;

	@GET
	public List<Dragon> getAll() {
		return dragonRepository.findAll();
	}

	@POST
	public Response create(Dragon dragon) {
		dragonRepository.createDragon(dragon);
		return Response.status(201).build();
	}

	@PUT
	public Response update(Dragon dragon) {
		dragonRepository.updateDragon(dragon);
		return Response.status(204).build();
	}

	@DELETE
	public Response delete(@QueryParam("id") Integer dragonId) {
		dragonRepository.deleteDragon(dragonId);
		return Response.status(204).build();
	}

}
