package com.example.hr.repository;

import java.util.Optional;

import com.example.ddd.Repository;
import com.example.hexagonal.Port;
import com.example.hexagonal.PortType;
import com.example.hr.domain.Employee;
import com.example.hr.domain.TcKimlikNo;

@Repository(entity=Employee.class)
@Port(PortType.DRIVEN_PORT)
public interface EmployeeRepository  {

	Optional<Employee> findEmployeeById(TcKimlikNo identity);

	boolean existsById(TcKimlikNo identity);

	Employee persist(Employee employee);

	Optional<Employee> remove(TcKimlikNo identity);

}
