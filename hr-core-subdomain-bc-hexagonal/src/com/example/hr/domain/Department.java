package com.example.hr.domain;

import com.example.ddd.ValueObject;

@ValueObject
public enum Department {
	IT(100), SALES(200), FINANCE(300), HR(400);
	private final int id;

	private Department(int id) {
		this.id = id;
	}

	public int id() {
		return id;
	}
	
}
