package com.mastertheboss.jaxrs.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import java.time.LocalDateTime;

@Data
@Entity
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@NamedQuery(name = "Dragons.findAll",
		query = "SELECT c FROM Dragon c ORDER BY c.id")
public class Dragon {

	@Id
	@SequenceGenerator(
			name = "dragonSequence",
			sequenceName = "dragonId_seq",
			allocationSize = 1,
			initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dragonSequence")
	private Integer id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически

	@Column(length = 40, nullable = false)
	private String name; //Поле не может быть null, Строка не может быть пустой

	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "coordinates_id", referencedColumnName = "id", nullable = false)
	private Coordinates coordinates; //Поле не может быть null

	@Column(length = 40)
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSSSS", shape = JsonFormat.Shape.STRING)
	private LocalDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически

	@Column(length = 40)
	private int age; //Значение поля должно быть больше 0

	@Column(length = 40)
	private Color color; //Поле может быть null

	@Column(length = 40)
	private DragonType type; //Поле может быть null

	@Column(length = 40)
	private DragonCharacter character; //Поле может быть null

	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "person_id", referencedColumnName = "id")
	private Person killer; //Поле может быть null
}
