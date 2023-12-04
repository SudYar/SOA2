package com.mastertheboss.jaxrs.backend.rest;

import com.mastertheboss.jaxrs.DragonService;
import com.mastertheboss.jaxrs.backend.domain.dto.DragonDTO;
import com.mastertheboss.jaxrs.backend.domain.dto.DragonTypeDTO;
import com.mastertheboss.jaxrs.backend.domain.entity.Dragon;
import com.mastertheboss.jaxrs.backend.domain.entity.DragonType;
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
import java.util.Optional;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "jax-rs-1/dragon/dragons", produces = MediaType.APPLICATION_JSON_VALUE)
public class DragonEndpoint {

	private final DragonRepository dragonRepository;
	private final PersonRepository personRepository;
	private final DragonService dragonService;


	@GetMapping(value = "")
	public List<Dragon> getAll() {
		return dragonRepository.findAll();
	}


	@GetMapping(value = "/{id}")
	public ResponseEntity<Dragon> getDragonById(@PathVariable("id") Integer id) {
		var dragon = dragonRepository.findById(id);
		return dragon.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
				.orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@PostMapping(value = "")
	public ResponseEntity<?> create(@RequestBody DragonDTO dragonDTO) {
		Optional<Person> killer = Optional.empty();
		if (dragonDTO.getKiller() != null) {
			killer = personRepository.findById(dragonDTO.getKiller());
			if (killer.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
		}
		Dragon dragon = Dragon.builder()
				.name(dragonDTO.getName())
				.age(dragonDTO.getAge())
				.color(dragonDTO.getColor())
				.killer(killer.orElse(null))
				.coordinates(dragonDTO.getCoordinates())
				.type(dragonDTO.getType())
				.character(dragonDTO.getCharacter()).build();
		dragonRepository.save(dragon);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@PutMapping(value = "")
	public ResponseEntity<?> update(@RequestBody DragonDTO dragonDTO) {
		Optional<Dragon> dragon;
		if (dragonDTO.getId() != null) {
			dragon = dragonRepository.findById(dragonDTO.getId());
			if (dragon.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		return dragonService.updateDragon(dragon.get(), dragonDTO);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Integer dragonId) {
		dragonRepository.deleteById(dragonId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@PostMapping(value = "/delete-killed")
	public ResponseEntity<?> deleteKilled(@RequestBody Person person) {
		if (person.getId() != null) {
			var personOpt = personRepository.findById(person.getId());
			if (personOpt.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		List<Dragon> allDragons = dragonRepository.findAll();
		for (Dragon dragon : allDragons) {
			if (dragon.getKiller() != null && Objects.equals(dragon.getKiller().getId(), person.getId())) {
				dragonRepository.deleteById(dragon.getId());
			}
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@PostMapping(value = "/delete-by-type")
	public ResponseEntity<?> deleteByType(@RequestBody DragonTypeDTO dragonTypeDTO) {
		List<Dragon> allDragons = dragonRepository.findAll();
		for (Dragon dragon : allDragons) {
			if (dragon.getType() != null &&
					Objects.equals(dragon.getType(), DragonType.fromValue(dragonTypeDTO.getValue()))) {
				dragonRepository.deleteById(dragon.getId());
			}
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping("/get-by-max-color")
	public ResponseEntity<Dragon> getByMaxColor() {
		List<Dragon> allDragons = dragonRepository.findAll();
		Dragon maxColorDragon = null;
		for (Dragon dragon : allDragons) {
			if (maxColorDragon != null) {
				if (dragon.getColor().getPriority() > maxColorDragon.getColor().getPriority()) {
					maxColorDragon = dragon;
				}
			} else {
				maxColorDragon = dragon;
			}
		}
		if (maxColorDragon == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(maxColorDragon, HttpStatus.OK);
	}

}
