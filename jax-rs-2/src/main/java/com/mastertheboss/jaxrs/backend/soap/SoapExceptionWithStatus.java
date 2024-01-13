package com.mastertheboss.jaxrs.backend.soap;

public class SoapExceptionWithStatus extends RuntimeException {

	public SoapExceptionWithStatus(final String message, final int status) {
		super(message, null);
	}

}
