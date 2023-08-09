package com.example.hr.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.example.hr.domain.Department;
import com.example.hr.domain.FiatCurrency;
import com.example.hr.domain.JobStyle;
import com.example.validation.Iban;
import com.example.validation.TcKimlikNo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Table(name = "employees")
@Data
@EqualsAndHashCode(of = "tcKimlikNo")
@ToString(exclude = "photo")
public class EmployeeEntity {
	@TcKimlikNo
	@Id
	@Column(name = "identity")
	private String tcKimlikNo;
	@NotBlank
	@Column(name = "fname")
	private String firstName;
	@NotBlank
	@Column(name = "lname")
	private String lastName;
	@DecimalMin("10000")
	@Column(name = "maas")
	private double salary;
	@NotNull
	@Enumerated(EnumType.ORDINAL)
	private FiatCurrency currency;
	@Iban
	private String iban;
	@Column(name = "byear")
	private int birthYear;
	@Lob
	@Column(columnDefinition = "longblob")
	private byte[] photo;
	@ElementCollection(targetClass = Department.class)
	private Set<Department> departments;
	@Enumerated(EnumType.STRING)
	private JobStyle jobStyle;
}
