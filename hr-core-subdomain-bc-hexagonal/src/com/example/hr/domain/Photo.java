package com.example.hr.domain;

import java.util.Base64;
import java.util.Objects;

import com.example.ddd.ValueObject;

@ValueObject
public final class Photo {
	private final byte[] values;

	private Photo(byte[] values) {
		this.values = values;
	}

	public byte[] values() {
		return values;
	}
	
	public String base64Values() {
		return Base64.getEncoder().encodeToString(values);
	}
	
	public static Photo valueOf(byte[] values) {
		Objects.nonNull(values);
		return new Photo(values);
	}

	public static Photo valueOf(String base64EncodedValues) {
		Objects.nonNull(base64EncodedValues);
		return new Photo(Base64.getDecoder().decode(base64EncodedValues));
	}
	
}
