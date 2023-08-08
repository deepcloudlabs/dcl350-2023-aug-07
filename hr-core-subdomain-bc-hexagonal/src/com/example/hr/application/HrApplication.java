package com.example.hr.application;

import java.util.Optional;

import com.example.ddd.Application;
import com.example.hexagonal.Port;
import com.example.hexagonal.PortType;
import com.example.hr.application.business.ExistingEmployeeException;
import com.example.hr.domain.Employee;
import com.example.hr.domain.TcKimlikNo;

@Application
@Port(PortType.DRIVING_PORT)
public interface HrApplication {
	Optional<Employee> findEmployeeByIdentity(TcKimlikNo identity);

	Employee hireEmployee(Employee employee) throws ExistingEmployeeException;

	Optional<Employee> fireEmployee(TcKimlikNo identity);
}
