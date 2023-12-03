package com.mastertheboss.jaxrs.backend.domain.exceptions;

import org.springframework.stereotype.Component;

@Component
public class CustomerException extends RuntimeException {

	public CustomerException() {
		super();
	}

	public CustomerException(String exc) {
		super(exc);
	}

}
