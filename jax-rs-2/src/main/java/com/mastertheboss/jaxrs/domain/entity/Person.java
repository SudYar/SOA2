package com.mastertheboss.jaxrs.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import java.util.Date;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Person {

	@Id
	@SequenceGenerator(
			name = "personSequence",
			sequenceName = "personId_seq",
			allocationSize = 1,
			initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "personSequence")
	private Long id;

	@Column(length = 40)
	private String name; //Поле не может быть null, Строка не может быть пустой

	@Column(length = 40)
	@JsonSerialize(using = DateSerializer.class)
	@JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
	private Date birthday; //Поле может быть null

	@Column(length = 40)
	private Long height; //Поле не может быть null, Значение поля должно быть больше 0

	@Column(length = 40)
	private String passportID; //Поле может быть null

	@Column(length = 40)
	private Color hairColor; //Поле не может быть null
}