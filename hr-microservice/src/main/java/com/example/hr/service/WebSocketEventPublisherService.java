package com.example.hr.service;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import com.example.hr.application.business.events.HrEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class WebSocketEventPublisherService implements WebSocketHandler {
	private final Map<String, WebSocketSession> sessions = new ConcurrentHashMap<>();
	private final ObjectMapper objectMapper;

	public WebSocketEventPublisherService(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}

	@EventListener
	public void publishEventToWebSocket(HrEvent event) {
		try {
			var eventAsJson = objectMapper.writeValueAsString(event);
			var textMessage = new TextMessage(eventAsJson);
			sessions.forEach((sessionId, session) -> {
				try {
					session.sendMessage(textMessage);
				} catch (IOException e) {
					System.err.println(
							"Error occured while sending the message to the session(%s)".formatted(session.getId()));
				}
			});
		} catch (JsonProcessingException e) {
			System.err.println("Error occured while converting to json: %s".formatted(e.getMessage()));
			throw new IllegalArgumentException(e.getMessage());
		}
	}

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		String sessionId = session.getId();
		sessions.put(sessionId, session);
		System.err.println("New session(%s) is open.".formatted(sessionId));
	}

	@Override
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
		System.err.println("New message has arrived: %s".formatted(message.getPayload()));
	}

	@Override
	public void handleTransportError(WebSocketSession session, Throwable e) throws Exception {
		System.err.println("Error occured while sending message: %s".formatted(e.getMessage()));
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
		String sessionId = session.getId();
		sessions.remove(sessionId);
		System.err.println("Session(%s) is closed".formatted(sessionId));
	}

	@Override
	public boolean supportsPartialMessages() {
		return false;
	}
}
