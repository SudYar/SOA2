package com.mastertheboss.jaxrs.backend.rest;

import com.mastertheboss.jaxrs.backend.domain.entity.Cave;
import com.mastertheboss.jaxrs.backend.domain.entity.Team;
import com.mastertheboss.jaxrs.backend.domain.repository.CaveRepository;
import com.mastertheboss.jaxrs.backend.domain.repository.TeamRepository;

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
		for (Cave cave : caves) {
			try {
				this.delete(cave.getId());
			} catch (Exception e) {
				//nothing
			}
		}
		return Response.status(204).build();
	}

}
