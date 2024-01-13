package com.mastertheboss.jaxrs;

import com.mastertheboss.jaxrs.backend.domain.dto.DragonDTO;
import com.mastertheboss.jaxrs.backend.domain.entity.Dragon;
import com.mastertheboss.jaxrs.backend.domain.entity.Person;
import com.mastertheboss.jaxrs.backend.domain.repository.DragonRepository;
import com.mastertheboss.jaxrs.backend.domain.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DragonService {

	private final PersonRepository personRepository;
	private final DragonRepository dragonRepository;

	public ResponseEntity<?> updateDragon(Dragon dragon, DragonDTO dragonDTO) {
		Optional<Person> killer = Optional.empty();
		if (dragonDTO.getKiller() != null) {
			killer = personRepository.findById(dragonDTO.getKiller());
			if (!killer.isPresent()) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}
		dragon.setId(dragonDTO.getId());
		dragon.setName(dragonDTO.getName());
		dragon.setCoordinates(dragonDTO.getCoordinates());
		dragon.setAge(dragonDTO.getAge());
		dragon.setType(dragonDTO.getType());
		dragon.setColor(dragonDTO.getColor());
		dragon.setCharacter(dragonDTO.getCharacter());
		dragon.setKiller(killer.orElse(null));
		dragonRepository.save(dragon);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
