package com.example.hr.client.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.hr.dto.response.EmployeeResponse;

@FeignClient(name = "hr")
public interface HrService {

	@GetMapping("/hr/api/v1/employees/{identity}")
	public EmployeeResponse getEmployeeByIdentity(@PathVariable String identity);
}
