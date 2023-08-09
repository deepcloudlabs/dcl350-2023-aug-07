package com.example.hr.adapter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.example.ddd.Adapter;
import com.example.hr.application.business.events.HrEvent;
import com.example.hr.infra.EventPublisher;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
@Adapter(port = EventPublisher.class)
public class KafkaEventPublisherAdapter implements EventPublisher<HrEvent> {
	private final KafkaTemplate<String, String> kafkaTemplate;
	private final String topicName;
	private final ObjectMapper objectMapper;
	
	public KafkaEventPublisherAdapter(KafkaTemplate<String, String> kafkaTemplate, 
			@Value("${topicName}") String topicName, ObjectMapper objectMapper) {
		this.kafkaTemplate = kafkaTemplate;
		this.topicName = topicName;
		this.objectMapper = objectMapper;
	}

	@Override
	public void publishEvent(HrEvent event) {
		try {
			var eventAsJson = objectMapper.writeValueAsString(event);
			kafkaTemplate.send(topicName, eventAsJson);
		} catch (JsonProcessingException e) {
			System.err.println("Error occured while conveting to json: %s".formatted(e.getMessage()));
			throw new IllegalArgumentException(e.getMessage());
		}
	}

}
