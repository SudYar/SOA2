package com.mastertheboss.jaxrs.domain.dto;

import com.mastertheboss.jaxrs.domain.entity.Color;
import com.mastertheboss.jaxrs.domain.entity.Coordinates;
import com.mastertheboss.jaxrs.domain.entity.DragonCharacter;
import com.mastertheboss.jaxrs.domain.entity.DragonType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DragonDTO {

	private Integer id;

	private String name;

	private Coordinates coordinates;

	private int age;

	private Color color;

	private DragonType type;

	private DragonCharacter character;

	private Long killer;

}
