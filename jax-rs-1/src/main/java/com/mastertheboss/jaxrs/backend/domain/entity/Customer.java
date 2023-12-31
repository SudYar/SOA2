package com.mastertheboss.jaxrs.backend.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;


@Entity
@Getter
@Setter
public class Customer {

	@Id
	@SequenceGenerator(
			name = "customerSequence",
			sequenceName = "customerId_seq",
			allocationSize = 1,
			initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customerSequence")
	private Long id;
	@Column(length = 40)
	private String name;
	@Column(length = 40)
	private String surname;

}