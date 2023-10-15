package com.mastertheboss.jaxrs.domain.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.Objects;

@Getter
@RequiredArgsConstructor
public enum Color {
	ORANGE("orange"),
	WHITE("white"),
	BROWN("brown");

	private final String value;

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
