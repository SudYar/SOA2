package com.mastertheboss.jaxrs.rest;

import com.mastertheboss.jaxrs.domain.entity.Cave;
import com.mastertheboss.jaxrs.domain.repository.CaveRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("caves")
@ApplicationScoped
@Produces("application/json")
@Consumes("application/json")
public class CaveEndpoint {

	@Inject
	CaveRepository caveRepository;

	@GET
	public List<Cave> getAll() {
		return caveRepository.findAll();
	}
	@GET
	@Path("{id}")
	public Cave getCaveById(@PathParam("id") Long id) {
		return caveRepository.findCaveById(id);
	}

	@POST
	public Response create(Cave cave) {
		caveRepository.createCave(cave);
		return Response.status(201).build();
	}

	@PUT
	public Response update(Cave cave) {
		caveRepository.updateCave(cave);
		return Response.status(204).build();
	}

	@DELETE
	@Path("{id}")
	public Response delete(@PathParam("id") Long caveId) {
		caveRepository.deleteCave(caveId);
		return Response.status(204).build();
	}

}
