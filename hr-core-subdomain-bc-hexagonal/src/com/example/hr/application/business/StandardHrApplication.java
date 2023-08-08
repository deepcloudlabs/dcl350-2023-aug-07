package com.example.hr.application.business;

import java.util.Optional;

import com.example.hr.application.HrApplication;
import com.example.hr.application.business.events.EmployeeFiredEvent;
import com.example.hr.application.business.events.EmployeeHiredEvent;
import com.example.hr.application.business.events.HrEvent;
import com.example.hr.domain.Employee;
import com.example.hr.domain.TcKimlikNo;
import com.example.hr.infra.EventPublisher;
import com.example.hr.repository.EmployeeRepository;

public class StandardHrApplication implements HrApplication {
	private final EmployeeRepository employeeRepository;
	private final EventPublisher<HrEvent> eventPublisher;

	public StandardHrApplication(EmployeeRepository employeeRepository, EventPublisher<HrEvent> eventPublisher) {
		this.employeeRepository = employeeRepository;
		this.eventPublisher = eventPublisher;
	}

	@Override
	public Optional<Employee> findEmployeeByIdentity(TcKimlikNo identity) {
		return employeeRepository.findEmployeeById(identity);
	}

	@Override
	public Employee hireEmployee(Employee employee) throws ExistingEmployeeException {
		var identity = employee.getTcKimlikNo();
		if (employeeRepository.existsById(identity))
			throw new ExistingEmployeeException("Employee already exists", identity);
		var persistedEmployee = employeeRepository.persist(employee);
		var domainEvent = new EmployeeHiredEvent(identity);
		eventPublisher.publishEvent(domainEvent);
		return persistedEmployee;
	}

	@Override
	public Optional<Employee> fireEmployee(TcKimlikNo identity) {
		Optional<Employee> removedEmployee = employeeRepository.remove(identity);
		removedEmployee.ifPresent( employee -> {
			var domainEvent = new EmployeeFiredEvent(employee);
			eventPublisher.publishEvent(domainEvent);			
		});
		return removedEmployee;
	}

}
