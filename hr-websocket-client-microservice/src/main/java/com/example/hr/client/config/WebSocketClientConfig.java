package com.example.hr.client.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;

@Configuration
public class WebSocketClientConfig {
	@Bean
	WebSocketClient createWebSocketClient() {
		return new StandardWebSocketClient();
	}
}
