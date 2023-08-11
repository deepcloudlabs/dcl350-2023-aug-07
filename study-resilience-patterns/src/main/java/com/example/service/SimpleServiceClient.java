package com.example.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class SimpleServiceClient {
	private final BusinessService businessService;
	
	public SimpleServiceClient(BusinessService businessService) {
		this.businessService = businessService;
		System.err.println(this.businessService.getClass().getName());
	}

	//@Scheduled(fixedRate = 100)
	public void callService() {
		var result = businessService.fun();
		System.err.println("Result from business service: %s".formatted(result));
	}
	
	@Scheduled(fixedRate = 10_000)
	public void callAsyncService() {
		businessService.sun()
		               .thenAcceptAsync(result -> System.err.println("Result from business service: %s".formatted(result)));
	}
}
