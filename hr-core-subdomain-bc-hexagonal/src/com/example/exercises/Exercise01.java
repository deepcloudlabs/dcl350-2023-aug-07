package com.example.exercises;

@SuppressWarnings("unused")
public class Exercise01 {

	public static void main(String[] args) {
		int a = 42; // 4-byte
		int[] array = new int[]{1,2,3,4,5,6}; // 6 x 4-byte = 24-byte
		// Object Header: 12-Byte + Integer Value: 4-byte: 16-Byte
		Integer i = Integer.valueOf(42);
		Integer j = 42; // auto-boxing
		Integer u = 542;
		Integer v = 542;
		System.out.println("i==j ? "+ (i==j)); // true
		System.out.println("u==v ? "+ (u==v)); // false
	}

}
