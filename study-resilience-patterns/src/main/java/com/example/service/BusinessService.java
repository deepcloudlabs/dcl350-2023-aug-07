package com.example.service;

import java.util.concurrent.CompletableFuture;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class BusinessService {

	// @Retry(name = "retryfun",fallbackMethod = "gun" )
	// @RateLimiter(name = "ratelimiterfun", fallbackMethod = "gun")
	@CircuitBreaker(name = "cbfun", fallbackMethod = "gun")
	public String fun() {
		var restTemplate = new RestTemplate();
		var response = restTemplate.getForEntity("http://localhost:5800/products/def", String.class).getBody();
		return response;
	}

	public String gun(Throwable t) {
		System.err.println("Cannot make a rest call: %s".formatted(t.getMessage()));
		return """
				{"sku":"def","title":"sample product","price":100.0}
								""";
	}
	
	// @TimeLimiter(name = "timelimitersun", fallbackMethod = "asyncGun")
	public CompletableFuture<String>  sun() {
		return CompletableFuture.supplyAsync(()->{
			var restTemplate = new RestTemplate();
			var response = restTemplate.getForEntity("http://localhost:5800/products/success", String.class).getBody();
			return response;			
		});
	}
	
	public CompletableFuture<String> asyncGun(Throwable t) {
		System.err.println("Cannot make a rest call: %s".formatted(t.getMessage()));
		return CompletableFuture.supplyAsync(()->"""
				{"sku":"failed","title":"sample product","price":100.0}
								""");
	}
}
