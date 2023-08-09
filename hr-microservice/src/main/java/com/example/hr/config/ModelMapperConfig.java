package com.example.hr.config;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.hr.domain.Department;
import com.example.hr.domain.Employee;
import com.example.hr.domain.FullName;
import com.example.hr.domain.TcKimlikNo;
import com.example.hr.dto.request.HireEmployeeRequest;
import com.example.hr.dto.response.EmployeeResponse;
import com.example.hr.entity.EmployeeEntity;

@Configuration
public class ModelMapperConfig {

	private static final Converter<Employee,EmployeeResponse> EMPLOYEE_TO_EMPLOYEE_RESPONSE_CONVERTER 
	= context -> {
		var employee = context.getSource();
		var employeeResponse = new EmployeeResponse();
		employeeResponse.setTcKimlikNo(employee.getTcKimlikNo().getValue());
		FullName fullName = employee.getFullName();
		employeeResponse.setFirstName(fullName.firstName());
		employeeResponse.setLastName(fullName.lastName());
		employeeResponse.setPhoto(employee.getPhoto().base64Values());
		employeeResponse.setSalary(employee.getSalary().value());
		employeeResponse.setCurrency(employee.getSalary().currency());
		employeeResponse.setIban(employee.getIban().getValue());
		employeeResponse.setDepartments(employee.getDepartments());
		employeeResponse.setJobStyle(employee.getJobStyle());
		employeeResponse.setBirthYear(employee.getBirthYear().value());
		return employeeResponse;
	};
	
	private static final Converter<HireEmployeeRequest,Employee> HIRE_EMPLOYEE_REQUEST_TO_EMPLOYEE_CONVERTER 
	= context -> {
		var request = context.getSource();
		return new Employee.Builder(TcKimlikNo.valueOf(request.getTcKimlikNo()))
				                   .fullName(request.getFirstName(), request.getLastName())
				                   .iban(request.getIban())
				                   .salary(request.getCurrency(), request.getSalary())
				                   .birthYear(request.getBirthYear())
				                   .photo(request.getPhoto())
				                   .departments(request.getDepartments().toArray(new Department[0]))
				                   .jobStyle(request.getJobStyle())
				                   .build();
	};
	
	private static final Converter<EmployeeEntity,Employee> EMPLOYEE_ENTITY_TO_EMPLOYEE_CONVERTER 
	= context -> {
		var employeeEntity = context.getSource();
		return new Employee.Builder(TcKimlikNo.valueOf(employeeEntity.getTcKimlikNo()))
				.fullName(employeeEntity.getFirstName(), employeeEntity.getLastName())
				.iban(employeeEntity.getIban())
				.salary(employeeEntity.getCurrency(), employeeEntity.getSalary())
				.birthYear(employeeEntity.getBirthYear())
				.photo(employeeEntity.getPhoto())
				.departments(employeeEntity.getDepartments().toArray(new Department[0]))
				.jobStyle(employeeEntity.getJobStyle())
				.build();
	};

	private static final Converter<Employee,EmployeeEntity> EMPLOYEE_TO_EMPLOYEE_ENTITY_CONVERTER 
	= context -> {
		var employee = context.getSource();
		var employeeEntity = new EmployeeEntity();
		employeeEntity.setTcKimlikNo(employee.getTcKimlikNo().getValue());
		FullName fullName = employee.getFullName();
		employeeEntity.setFirstName(fullName.firstName());
		employeeEntity.setLastName(fullName.lastName());
		employeeEntity.setPhoto(employee.getPhoto().values());
		employeeEntity.setSalary(employee.getSalary().value());
		employeeEntity.setCurrency(employee.getSalary().currency());
		employeeEntity.setIban(employee.getIban().getValue());
		employeeEntity.setDepartments(employee.getDepartments());
		employeeEntity.setJobStyle(employee.getJobStyle());
		employeeEntity.setBirthYear(employee.getBirthYear().value());
		return employeeEntity;
	};

	@Bean
	ModelMapper createModelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.addConverter(EMPLOYEE_TO_EMPLOYEE_RESPONSE_CONVERTER, Employee.class, EmployeeResponse.class);
		modelMapper.addConverter(HIRE_EMPLOYEE_REQUEST_TO_EMPLOYEE_CONVERTER, HireEmployeeRequest.class, Employee.class);
		modelMapper.addConverter(EMPLOYEE_ENTITY_TO_EMPLOYEE_CONVERTER, EmployeeEntity.class, Employee.class);
		modelMapper.addConverter(EMPLOYEE_TO_EMPLOYEE_ENTITY_CONVERTER, Employee.class, EmployeeEntity.class);
		return modelMapper;
	}
}
