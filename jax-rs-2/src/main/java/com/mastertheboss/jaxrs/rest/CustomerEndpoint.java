package com.mastertheboss.jaxrs.rest;


import com.mastertheboss.jaxrs.domain.entity.Customer;
import com.mastertheboss.jaxrs.domain.repository.CustomerRepository;

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


@Path("customers")
@ApplicationScoped
@Produces("application/json")
@Consumes("application/json")
public class CustomerEndpoint {

	@Inject
	CustomerRepository customerRepository;

	@GET
	public List<Customer> getAll() {
		return customerRepository.findAll();
	}

	@POST
	public Response create(Customer customer) {
		customerRepository.createCustomer(customer);
		return Response.status(201).build();
	}

	@PUT
	public Response update(Customer customer) {
		customerRepository.updateCustomer(customer);
		return Response.status(204).build();
	}

	@DELETE
	public Response delete(@QueryParam("id") Long customerId) {
		customerRepository.deleteCustomer(customerId);
		return Response.status(204).build();
	}

}