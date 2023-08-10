package com.example.hr.client.service;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import javax.annotation.PostConstruct;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.hr.dto.response.EmployeeResponse;

@Service
public class HrClientServiceWithManualLB {
	private static final String url = "http://%s:%d/hr/api/v1/employees";
	private final DiscoveryClient discoveryClient;
	private final RestTemplate restTemplate;
	private List<ServiceInstance> instances;
	private final AtomicInteger counter= new AtomicInteger();
	private static final String tcKimlikNo = "70777100098";

	public HrClientServiceWithManualLB(DiscoveryClient discoveryClient, RestTemplate restTemplate) {
		this.discoveryClient = discoveryClient;
		this.restTemplate = restTemplate;
	}

	@PostConstruct
	public void init() {
		this.instances = discoveryClient.getInstances("hr");
		this.instances.forEach(instance -> System.err.println("Hr Instance\t: %s:%d".formatted(instance.getHost(),instance.getPort())));		
	}
	
	//@Scheduled(fixedRate=3_000)
	public void callHrService() {
		var instanceIndex = this.counter.getAndIncrement() % instances.size();
		var instance = this.instances.get(instanceIndex);
		var host = instance.getHost();
		var port = instance.getPort();
		var employeeResponse = restTemplate.getForEntity("%s/%s".formatted(url.formatted(host,port),tcKimlikNo), EmployeeResponse.class);
		System.out.println(employeeResponse.getBody());
	}
}
