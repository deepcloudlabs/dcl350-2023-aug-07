package com.example.crm.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.crm.document.CustomerDocument;
import com.example.crm.service.ReactiveCrmService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/customers")
@Validated
@CrossOrigin
public class CrmReactiveRestController {
	private final ReactiveCrmService reactiveCrmService;
	
	public CrmReactiveRestController(ReactiveCrmService reactiveCrmService) {
		this.reactiveCrmService = reactiveCrmService;
	}

	@GetMapping("/{identity}")
	public Mono<CustomerDocument> getCustomerByIdentity(@PathVariable String identity) {
		return reactiveCrmService.getCustomerById(identity);
	}
	
	@GetMapping(params={"pageNo","pageSize"})
	public Flux<CustomerDocument> getCustomersByPage(@RequestParam int pageNo,@RequestParam int pageSize) {
		return reactiveCrmService.getCustomers(pageNo,pageSize);
	}
	
	@PostMapping
	public Mono<CustomerDocument> acquireCustomer(@RequestBody CustomerDocument customer){
		return reactiveCrmService.acquire(customer);
	}
	
	@DeleteMapping("/{identity}")
	public Mono<CustomerDocument> releaseCustomer(@PathVariable String identity) {
		return reactiveCrmService.removeCustomerById(identity);
	}
}
