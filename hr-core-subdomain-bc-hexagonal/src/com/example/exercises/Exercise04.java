package com.example.exercises;

import com.example.hr.domain.Department;

public class Exercise04 {

	public static void main(String[] args) {
		var department = Department.valueOf("IT");
		System.out.println(department.name());
		System.out.println(department.ordinal());
		System.out.println(department.id());

	}

}
