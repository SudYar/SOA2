package com.mastertheboss.jaxrs.domain.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.Objects;

@Getter
@RequiredArgsConstructor
public enum DragonType {
	WATER("water"),
	UNDERGROUND("underground"),
	AIR("air"),
	FIRE("fire");

	private final String value;

	public static DragonType fromValue(String value) {
		return Arrays.stream(DragonType.values())
				.filter(e -> Objects.equals(e.getValue(), value.toLowerCase()))
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