package com.example.hr.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.hr.application.HrApplication;
import com.example.hr.application.business.ExistingEmployeeException;
import com.example.hr.domain.Employee;
import com.example.hr.domain.TcKimlikNo;
import com.example.hr.dto.request.HireEmployeeRequest;
import com.example.hr.dto.response.EmployeeResponse;
import com.example.hr.dto.response.HireEmployeeResponse;

@Service
public class HrService {
	private final HrApplication hrApplication;
	private final ModelMapper modelMapper;

	public HrService(HrApplication hrApplication, ModelMapper modelMapper) {
		this.hrApplication = hrApplication;
		this.modelMapper = modelMapper;
	}

	public EmployeeResponse findEmployeeByIdentity(String identity) {
		var employee = hrApplication.findEmployeeByIdentity(TcKimlikNo.valueOf(identity))
				.orElseThrow(() -> new IllegalArgumentException("Employee does not exist!"));
		return modelMapper.map(employee, EmployeeResponse.class);
	}

	@Transactional
	public HireEmployeeResponse hireEmployee(HireEmployeeRequest request) throws ExistingEmployeeException {
		hrApplication.hireEmployee(modelMapper.map(request, Employee.class));
		return new HireEmployeeResponse("success");
	}

	@Transactional
	public EmployeeResponse fireEmployee(String identity) {
		var firedEmployee = hrApplication.findEmployeeByIdentity(TcKimlikNo.valueOf(identity))
				.orElseThrow(() -> new IllegalArgumentException("Employee does not exist!"));
		return modelMapper.map(firedEmployee, EmployeeResponse.class);
	}

}
