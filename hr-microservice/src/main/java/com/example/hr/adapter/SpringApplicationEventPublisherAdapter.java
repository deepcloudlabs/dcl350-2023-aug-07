package com.example.hr.adapter;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import com.example.ddd.Adapter;
import com.example.hr.application.business.events.HrEvent;
import com.example.hr.infra.EventPublisher;

@Service
@Adapter(port = EventPublisher.class)
public class SpringApplicationEventPublisherAdapter implements EventPublisher<HrEvent> {
	private final ApplicationEventPublisher eventPublisher;
	
	public SpringApplicationEventPublisherAdapter(ApplicationEventPublisher eventPublisher) {
		this.eventPublisher = eventPublisher;
	}

	@Override
	public void publishEvent(HrEvent event) {
		eventPublisher.publishEvent(event);
	}

}
