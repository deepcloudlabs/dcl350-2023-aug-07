package com.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

import com.example.domain.Product;
import com.example.service.StockService;

@RestController
@RequestMapping("/products")
@RequestScope
public class SampleRestController {
	private final StockService stockService;
	
	public SampleRestController(StockService stockService) {
		this.stockService = stockService;
	}

	@GetMapping("/{sku}")
	public Product getProduct(@PathVariable String sku) throws InterruptedException {
		return stockService.findProduct(sku);
	}
}
