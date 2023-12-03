package com.mastertheboss.jaxrs.ejb.service;

import com.mastertheboss.jaxrs.backend.domain.entity.Person;

import javax.ejb.Remote;

@Remote
public interface PersonService {

	Person getPersonById(Long personId);
}
