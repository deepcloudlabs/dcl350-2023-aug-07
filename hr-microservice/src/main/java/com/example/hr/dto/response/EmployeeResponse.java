package com.example.hr.dto.response;

import java.util.Set;

import com.example.hr.domain.Department;
import com.example.hr.domain.FiatCurrency;
import com.example.hr.domain.JobStyle;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class EmployeeResponse {
	private String tcKimlikNo;
	private String firstName;
	private String lastName;
	private double salary;
	private FiatCurrency currency;
	private String iban;
	private int birthYear;
	private String photo;
	private Set<Department> departments;
	private JobStyle jobStyle;
	
}
