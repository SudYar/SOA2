package com.mastertheboss.jaxrs.backend;


import com.mastertheboss.jaxrs.backend.soap.SoapWebService;

import javax.ws.rs.core.Application;
import javax.xml.ws.Endpoint;


public class SOApplicationSecond extends Application {

	public static void main(String[] args) {
		Endpoint.publish("http://localhost:21790/killer", new SoapWebService());
		System.out.println("SoapWebService Started!");
	}

}
