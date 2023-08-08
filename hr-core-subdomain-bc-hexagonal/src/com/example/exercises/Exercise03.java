package com.example.exercises;

import com.example.hr.domain.FullName;

public class Exercise03 {

	public static void main(String[] args) {
		var jack1 = FullName.of("jack","bauer");
		var jack2 = FullName.of("jack","bauer");
		System.out.println(jack1);
		System.out.println(jack1.firstName());
		System.out.println(jack1.lastName());
		System.out.println(jack1.equals(jack2));

	}

}
