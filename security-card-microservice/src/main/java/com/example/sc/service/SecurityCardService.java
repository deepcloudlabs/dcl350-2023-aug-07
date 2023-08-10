package com.example.sc.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class SecurityCardService {

	@KafkaListener(topics = "hr-events",groupId = "security-card")
	public void listenHrEvents(String event) {
		System.err.println("New event has arrived: %s".formatted(event));
	}
}
