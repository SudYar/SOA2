package com.mastertheboss.jaxrs.backend.domain.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.Objects;

@Getter
@RequiredArgsConstructor
public enum Color {
	ORANGE("orange", 1),
	WHITE("white", 2),
	BROWN("brown", 3);

	private final String value;

	private final Integer priority;

	public static Color fromValue(String value) {
		return Arrays.stream(Color.values())
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
