package entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;

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
			allocationSize = 1
	)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "coordinatesSequence")
	private Long id;

	@Column(length = 40)
	private Float x; //Поле не может быть null

	@Column(length = 40)
	private double y; //Значение поля должно быть больше -796
}
