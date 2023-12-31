package com.example.hr.dto.request;

import java.util.Set;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.example.hr.domain.Department;
import com.example.hr.domain.FiatCurrency;
import com.example.hr.domain.JobStyle;
import com.example.validation.Iban;
import com.example.validation.TcKimlikNo;

import lombok.Data;

@Data
public class HireEmployeeRequest {
	@TcKimlikNo
	private String tcKimlikNo;
	@NotBlank
	private String firstName;
	@NotBlank
	private String lastName;
	@DecimalMin("10000")
	private double salary;
	@NotNull
	private FiatCurrency currency;
	@Iban
	private String iban;
	private int birthYear;
	private String photo;
	private Set<Department> departments;
	private JobStyle jobStyle;
}
