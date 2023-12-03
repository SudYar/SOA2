package com.mastertheboss.jaxrs.domain.repository;

import com.mastertheboss.jaxrs.domain.entity.Dragon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DragonRepository extends JpaRepository<Dragon, Integer> {

}
