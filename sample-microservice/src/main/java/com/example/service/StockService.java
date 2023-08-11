package com.example.service;

import java.time.LocalDateTime;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;

import com.example.domain.Product;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.bulkhead.annotation.Bulkhead.Type;

@Service
public class StockService {

	@Bulkhead(name = "sku",type = Type.SEMAPHORE)
	public Product findProduct(String sku) throws InterruptedException {
		System.err.println("New request has arrived at %s".formatted(LocalDateTime.now()));
		TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(6));
		return new Product(sku,"sample product", 100.0);
	}

}
