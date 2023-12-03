package com.mastertheboss.jaxrs;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.ws.rs.ApplicationPath;

@SpringBootApplication
@ApplicationPath("/dragon")
public class SOApplicationFirst {

	public static void main(String[] args) {
		SpringApplication.run(SOApplicationFirst.class, args);
	}

}
