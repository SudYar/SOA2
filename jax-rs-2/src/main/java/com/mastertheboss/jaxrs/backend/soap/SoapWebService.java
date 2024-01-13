package com.mastertheboss.jaxrs.backend.soap;

import com.mastertheboss.jaxrs.backend.domain.entity.Cave;
import com.mastertheboss.jaxrs.backend.domain.entity.Response;
import com.mastertheboss.jaxrs.backend.domain.entity.Team;
import com.mastertheboss.jaxrs.backend.domain.repository.CaveRepository;
import com.mastertheboss.jaxrs.backend.domain.repository.TeamRepository;
import com.mastertheboss.jaxrs.backend.rest.TeamEndpoint;

import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.List;
import java.util.Objects;

@WebService
public class SoapWebService {

	@Inject
	CaveRepository caveRepository;

	@Inject
	TeamRepository teamRepository;

	@Inject
	TeamEndpoint teamEndpoint;

	@WebMethod
	public Response getAllCaves() {
		Response response = new Response();
		response.setCaveList(caveRepository.findAll());
		return response;
	}

	@WebMethod
	public Response getCaveById(@WebParam(name = "cave-id") Long id) {
		Response response = new Response();
		response.setCave(caveRepository.findCaveById(id));
		return response;
	}

	@WebMethod
	public Response createCave(@WebParam(name = "cave") Cave cave) {
		caveRepository.createCave(cave);
		Response response = new Response();
		response.setStatus(201);
		return response;
	}

	@WebMethod
	public Response updateCave(@WebParam(name = "cave") Cave cave) {
		caveRepository.updateCave(cave);
		Response response = new Response();
		response.setStatus(204);
		return response;
	}

	@WebMethod
	public Response deleteCave(@WebParam(name = "cave-id") Long caveId) {
		deleteCaveById(caveId);
		Response response = new Response();
		response.setStatus(204);
		return response;
	}

	@WebMethod
	public Response deleteAllCaves() {
		List<Cave> caves = caveRepository.findAll();
		for (Cave cave : caves) {
			try {
				deleteCaveById(cave.getId());
			} catch (Exception e) {
				//nothing
			}
		}
		Response response = new Response();
		response.setStatus(204);
		return response;
	}

	private void deleteCaveById(@WebParam(name = "cave-id") Long caveId) {
		List<Team> teams = teamRepository.findAll();
		for (Team team : teams) {
			if (team.getCave() != null && Objects.equals(team.getCave().getId(), caveId)) {
				throw new SoapExceptionWithStatus("Wrong REQUEST, cave is used", 400);
			}
		}
		caveRepository.deleteCave(caveId);
	}

	@WebMethod
	public Response getAllTeams() {
		Response response = new Response();
		response.setTeamList(teamRepository.findAll());
		return response;
	}

	@WebMethod
	public Response createTeam(@WebParam(name = "team-id") Long teamId,
							   @WebParam(name = "team-name") String teamName,
							   @WebParam(name = "team-size") Short teamSize,
							   @WebParam(name = "start-cave-id") Long caveId,
							   @WebParam(name = "persons-ids") Long[] personsId) {
		teamEndpoint.create(teamId, teamName, teamSize, caveId, personsId);
		Response response = new Response();
		response.setStatus(201);
		return response;
	}

	@WebMethod
	public Response moveTeam(@WebParam(name = "team-id") Long teamId, @WebParam(name = "cave-id") Long caveId) {
		teamRepository.changeCave(teamId, caveId);
		Response response = new Response();
		response.setStatus(201);
		return response;
	}

	@WebMethod
	public Response deleteAllTeams() {
		teamRepository.deleteAll();
		Response response = new Response();
		response.setStatus(204);
		return response;
	}

}
