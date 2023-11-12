package com.mastertheboss.jaxrs.rest;

import com.mastertheboss.jaxrs.domain.entity.Cave;
import com.mastertheboss.jaxrs.domain.entity.Team;
import com.mastertheboss.jaxrs.domain.repository.CaveRepository;
import com.mastertheboss.jaxrs.domain.repository.TeamRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Objects;

@Path("caves")
@ApplicationScoped
@Produces("application/json")
@Consumes("application/json")
public class CaveEndpoint {

	@Inject
	CaveRepository caveRepository;

	@Inject
	TeamRepository teamRepository;

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
		List<Team> teams = teamRepository.findAll();
		for (Team team : teams) {
			if (team.getCave() != null && Objects.equals(team.getCave().getId(), caveId)) {
				throw new WebApplicationException("Wrong REQUEST, cave is used", 400);
			}
		}
		caveRepository.deleteCave(caveId);
		return Response.status(204).build();
	}

	@DELETE
	@Path("deleteAll")
	public Response deleteAll() {
		List<Cave> caves = caveRepository.findAll();
		for (Cave cave: caves) {
			try {
				this.delete(cave.getId());
			}catch (Exception e) {
				//nothing
			}
		}
		return Response.status(204).build();
	}

}
