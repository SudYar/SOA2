package com.mastertheboss.jaxrs.backend.conf;

import com.mastertheboss.jaxrs.backend.domain.exceptions.Error;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.Instant;

@ApplicationScoped
public class ResponseUtils {

	public Response buildResponseWithMessage(Response.Status status, String message) {
		return Response
				.status(status)
				.entity(Error
						.builder()
						.message(message)
						.code(String.valueOf(status.getStatusCode()))
						.date(Instant.now().toString())
						.build()
				)
				.type(MediaType.APPLICATION_JSON)
				.build();
	}
}
