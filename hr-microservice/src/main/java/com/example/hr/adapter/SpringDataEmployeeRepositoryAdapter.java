package com.example.hr.adapter;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.ddd.Adapter;
import com.example.hr.domain.Employee;
import com.example.hr.domain.TcKimlikNo;
import com.example.hr.entity.EmployeeEntity;
import com.example.hr.repository.EmployeeEntityRepository;
import com.example.hr.repository.EmployeeRepository;

@Repository
@Adapter(port = EmployeeRepository.class)
public class SpringDataEmployeeRepositoryAdapter implements EmployeeRepository {
	private final EmployeeEntityRepository employeeEntityRepository;
	private final ModelMapper modelMapper;

	public SpringDataEmployeeRepositoryAdapter(EmployeeEntityRepository employeeEntityRepository,
			ModelMapper modelMapper) {
		this.employeeEntityRepository = employeeEntityRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public Optional<Employee> findEmployeeById(TcKimlikNo identity) {
		var employeeEntity = employeeEntityRepository.findById(identity.getValue());
		if (employeeEntity.isEmpty())
			return Optional.empty();
		return Optional.of(modelMapper.map(employeeEntity.get(), Employee.class));
	}

	@Override
	public boolean existsById(TcKimlikNo identity) {
		return employeeEntityRepository.existsById(identity.getValue());
	}

	@Override
	@Transactional
	public Employee persist(Employee employee) {
		var employeeEntity = modelMapper.map(employee, EmployeeEntity.class);
		return modelMapper.map(employeeEntityRepository.save(employeeEntity), Employee.class);
	}

	@Override
	@Transactional
	public Optional<Employee> remove(TcKimlikNo identity) {
		var removedEmployeeEntity = employeeEntityRepository.findById(identity.getValue());
		if (removedEmployeeEntity.isEmpty())
			return Optional.empty();
		EmployeeEntity entity = removedEmployeeEntity.get();
		employeeEntityRepository.delete(entity);
		return Optional.of(modelMapper.map(entity, Employee.class));
	}

}
