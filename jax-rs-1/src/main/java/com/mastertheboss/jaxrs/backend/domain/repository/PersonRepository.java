package com.mastertheboss.jaxrs.backend.domain.repository;


import com.mastertheboss.jaxrs.backend.domain.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

}
