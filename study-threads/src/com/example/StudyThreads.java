package com.example;

@SuppressWarnings("unused")
public class StudyThreads {

	public static void main(String[] args) {
		var kernelThread = Thread.ofPlatform(); // parallel programming
		var virtualThread = Thread.ofVirtual(); // network io

	}

}
