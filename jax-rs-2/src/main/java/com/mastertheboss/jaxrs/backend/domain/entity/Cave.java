package com.mastertheboss.jaxrs.backend.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@NamedQuery(name = "Caves.findAll",
		query = "SELECT c FROM Cave c ORDER BY c.id")
@XmlRootElement(name = "cave")
public class Cave {

	@Id
	@SequenceGenerator(
			name = "cavesSequence",
			sequenceName = "cavesId_seq",
			allocationSize = 1,
			initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cavesSequence")
	private Long id;

	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "coordinates_id", referencedColumnName = "id", nullable = false)
	private Coordinates coordinates; //Поле не может быть null

}