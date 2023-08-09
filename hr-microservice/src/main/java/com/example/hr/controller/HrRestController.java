package com.example.hr.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

import com.example.ddd.Adapter;
import com.example.hr.application.HrApplication;
import com.example.hr.application.business.ExistingEmployeeException;
import com.example.hr.dto.request.HireEmployeeRequest;
import com.example.hr.dto.response.EmployeeResponse;
import com.example.hr.dto.response.HireEmployeeResponse;
import com.example.hr.service.HrService;

@RestController
@RequestMapping("/employees")
@RequestScope
@Validated
@CrossOrigin
@Adapter(port=HrApplication.class)
public class HrRestController {
	private final HrService hrService;
	
	public HrRestController(HrService hrService) {
		this.hrService = hrService;
	}

	@GetMapping("/{id}")
	public EmployeeResponse findEmployeeByIdentity(@PathVariable("id") String identity) {
		return hrService.findEmployeeByIdentity(identity);
	}

	@PostMapping
	public HireEmployeeResponse hireEmployee(@RequestBody HireEmployeeRequest request) throws ExistingEmployeeException{
		return hrService.hireEmployee(request);
	}

	@DeleteMapping("/{id}")
	public EmployeeResponse fireEmployee(@PathVariable("id") String identity) {
		return hrService.fireEmployee(identity);

	}
}
