package com.example.hr.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import com.example.hr.service.WebSocketEventPublisherService;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
	private final WebSocketEventPublisherService webSocketEventPublisherService;
	
	public WebSocketConfig(WebSocketEventPublisherService webSocketEventPublisherService) {
		this.webSocketEventPublisherService = webSocketEventPublisherService;
	}

	// WebSocket Endpoint URL: ws://localhost:8100/hr/api/v1/events
	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(webSocketEventPublisherService, "/events")
		        .setAllowedOrigins("*");		
	}

}
