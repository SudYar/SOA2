package com.mastertheboss.jaxrs.domain.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.Objects;

@Getter
@RequiredArgsConstructor
public enum DragonCharacter {
	WISE("wise"),
	CHAOTIC("chaotic"),
	CHAOTIC_EVIL("chaotic_evil"),
	FICKLE("wise");

	private final String value;

	public static DragonCharacter fromValue(String value) {
		return Arrays.stream(DragonCharacter.values())
				.filter(e -> Objects.equals(e.getValue(), value))
				.findFirst()
				.orElse(null);
	}

	@Override
	public String toString() {
		if (value == null) {
			return "";
		}
		return value;
	}
}