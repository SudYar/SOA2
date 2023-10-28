package com.mastertheboss.jaxrs.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;


@Entity
@NamedQuery(name = "Customers.findAll",
		query = "SELECT c FROM Customer c ORDER BY c.id")
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