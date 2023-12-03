package com.mastertheboss.jaxrs.domain.exceptions;

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
