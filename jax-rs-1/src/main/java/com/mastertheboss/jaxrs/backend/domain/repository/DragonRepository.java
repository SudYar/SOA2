package com.mastertheboss.jaxrs.backend.domain.repository;

import com.mastertheboss.jaxrs.backend.domain.entity.Dragon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DragonRepository extends JpaRepository<Dragon, Integer> {

}
