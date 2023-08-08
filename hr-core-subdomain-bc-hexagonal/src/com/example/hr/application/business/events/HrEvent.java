package com.example.hr.application.business.events;

import java.time.ZonedDateTime;
import java.util.UUID;

import com.example.ddd.DomainEvent;
import com.example.hr.domain.TcKimlikNo;

@DomainEvent
public abstract class HrEvent {
	private final String uuid = UUID.randomUUID().toString();
	private final ZonedDateTime createdAt = ZonedDateTime.now();
	private final TcKimlikNo identity;

	public HrEvent(TcKimlikNo identity) {
		this.identity = identity;
	}

	public String uuid() {
		return uuid;
	}

	public ZonedDateTime createdAt() {
		return createdAt;
	}

	public TcKimlikNo identity() {
		return identity;
	}

}
