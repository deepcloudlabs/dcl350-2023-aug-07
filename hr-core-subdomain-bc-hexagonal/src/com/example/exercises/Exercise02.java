package com.example.exercises;

import com.example.hr.domain.TcKimlikNo;

public class Exercise02 {

	public static void main(String[] args) {
		var tcKimlikNo1 = TcKimlikNo.valueOf("11111111110");
		var tcKimlikNo2 = TcKimlikNo.valueOf("11111111110");
		System.err.println("tcKimlikNo1==tcKimlikNo2 ? "+(tcKimlikNo1==tcKimlikNo2));
	}

}
