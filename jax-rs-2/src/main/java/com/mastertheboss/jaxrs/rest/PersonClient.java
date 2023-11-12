package com.mastertheboss.jaxrs.rest;

import com.mastertheboss.jaxrs.domain.entity.Person;

import javax.ws.rs.ProcessingException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class PersonClient {
    private Client client;
    private final String firstServiceUrl = "https://se.ifmo.ru:21570/jax-rs-1/dragon";

    public Person getPersonById(Long id){
        String url = firstServiceUrl + "/persons/" + id.toString();
        try{
            client = ClientBuilder.newClient();

            Response response = client.target(url).request(MediaType.APPLICATION_JSON_TYPE).get();

            Person person = response.readEntity(Person.class);

            client.close();

            return person;
        }catch (ProcessingException e){
            return null;
        }

    }
}
