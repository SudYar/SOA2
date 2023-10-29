package com.mastertheboss.jaxrs.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@NamedQuery(name = "Coordinates.findAll",
		query = "SELECT c FROM Coordinates c ORDER BY c.id")
public class Coordinates {

	@Id
	@SequenceGenerator(
			name = "coordinatesSequence",
			sequenceName = "coordinatesId_seq",
			allocationSize = 1,
			initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "coordinatesSequence")
	private Long id;

	@Column(length = 40)
	private Float x; //Поле не может быть null

	@Column(length = 40)
	private Long y; //Значение поля должно быть больше -796
}
