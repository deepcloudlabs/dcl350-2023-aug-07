package com.example.crm.service;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.crm.document.CustomerDocument;
import com.example.crm.repository.CustomerDocumentReactiveRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ReactiveCrmService {
	private final CustomerDocumentReactiveRepository customerRepository;
	
	public ReactiveCrmService(CustomerDocumentReactiveRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	public Mono<CustomerDocument> getCustomerById(String identity) {
		return customerRepository.findById(identity);
	}

	public Flux<CustomerDocument> getCustomers(int pageNo, int pageSize) {
		return customerRepository.findAll(PageRequest.of(pageNo,pageSize));
	}

	public Mono<CustomerDocument> acquire(CustomerDocument customer) {		
		return customerRepository.insert(customer);
	}

	public Mono<CustomerDocument> removeCustomerById(String identity) {
		return customerRepository.findById(identity)
		                         .doOnNext( customer -> customerRepository.delete(customer).subscribe(cust -> System.err.println("Customer (%s) is released!".formatted(customer.getIdentity()))));
	}

}
