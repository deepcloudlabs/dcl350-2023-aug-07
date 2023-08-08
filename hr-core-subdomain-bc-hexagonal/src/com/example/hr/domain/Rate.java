package com.example.hr.domain;

import com.example.ddd.ValueObject;

@ValueObject
public final class Rate {
	private final double value;

	private Rate(double value) {
		this.value = value;
	}

	public static Rate of(double value) {
		if (value <= 0.0)
			throw new IllegalArgumentException("Rate must be positive");
		return new Rate(value);
	}

	public double value() {
		return value;
	}

}
