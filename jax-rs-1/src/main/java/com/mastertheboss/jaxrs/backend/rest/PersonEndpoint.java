package com.mastertheboss.jaxrs.backend.rest;


import com.mastertheboss.jaxrs.backend.domain.entity.Dragon;
import com.mastertheboss.jaxrs.backend.domain.entity.Person;
import com.mastertheboss.jaxrs.backend.domain.repository.DragonRepository;
import com.mastertheboss.jaxrs.backend.domain.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "jax-rs-1/dragon/persons", produces = MediaType.APPLICATION_JSON_VALUE)
public class PersonEndpoint {

	private final PersonRepository personRepository;
	private final DragonRepository dragonRepository;

	@GetMapping(value = "")
	public List<Person> getAll() {
		return personRepository.findAll();
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<?> getPersonById(@PathVariable("id") Long id) {
		var person = personRepository.findById(id);
		return person.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
				.orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@PostMapping(value = "")
	public ResponseEntity<?> create(@RequestBody Person person) {
		personRepository.save(person);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@PutMapping(value = "")
	public ResponseEntity<?> update(@RequestBody Person person) {
		personRepository.save(person);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long personId) {
		List<Dragon> allDragons = dragonRepository.findAll();
		for (Dragon dragon : allDragons) {
			if (dragon.getKiller() != null && Objects.equals(dragon.getKiller().getId(), personId)) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
		}
		personRepository.deleteById(personId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}