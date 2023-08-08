package com.example.hr.domain;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import com.example.ddd.Entity;

@Entity(identity = "tcKimlikNo", aggregate = true)
public class Employee {
	private TcKimlikNo tcKimlikNo;
	private FullName fullName;
	private Money salary;
	private Iban iban;
	private BirthYear birthYear;
	private Photo photo;
	private Set<Department> departments;
	private JobStyle jobStyle;

	private Employee(Builder builder) {
		this.tcKimlikNo = builder.tcKimlikNo;
		this.fullName = builder.fullName;
		this.salary = builder.salary;
		this.iban = builder.iban;
		this.birthYear = builder.birthYear;
		this.photo = builder.photo;
		this.departments = builder.departments;
		this.jobStyle = builder.jobStyle;
	}

	public static class Builder {
		private TcKimlikNo tcKimlikNo;
		private FullName fullName;
		private Money salary;
		private Iban iban;
		private BirthYear birthYear;
		private Photo photo;
		private Set<Department> departments;
		private JobStyle jobStyle;

		public Builder(TcKimlikNo tcKimlikNo) {
			this.tcKimlikNo = tcKimlikNo;
		}

		public Builder fullName(String firstName, String lastName) {
			this.fullName = FullName.of(firstName, lastName);
			return this;
		}

		public Builder salary(FiatCurrency currency, double value) {
			this.salary = Money.of(currency, value);
			return this;
		}

		public Builder salary(double value) {
			return salary(FiatCurrency.TL, value);
		}

		public Builder iban(String value) {
			this.iban = Iban.of(value);
			return this;
		}

		public Builder birthYear(int value) {
			this.birthYear = new BirthYear(value);
			return this;
		}

		public Builder photo(byte[] values) {
			this.photo = Photo.valueOf(values);
			return this;
		}

		public Builder photo(String base64Values) {
			this.photo = Photo.valueOf(base64Values);
			return this;
		}

		public Builder jobStyle(JobStyle value) {
			this.jobStyle = value;
			return this;
		}
		
		public Builder departments(Department... values) {
			this.departments = new HashSet<>(Arrays.asList(values));
			return this;
		}
		
		public Employee build() {
			// Consistent Aggregate
			// Validation, Policy, Business Rule, Invariants, Constraint
			
			return new Employee(this);
		}

	}

	
	public TcKimlikNo getTcKimlikNo() {
		return tcKimlikNo;
	}

	public FullName getFullName() {
		return fullName;
	}

	public Money getSalary() {
		return salary;
	}

	public Iban getIban() {
		return iban;
	}

	public BirthYear getBirthYear() {
		return birthYear;
	}

	public Photo getPhoto() {
		return photo;
	}

	public Set<Department> getDepartments() {
		// return Collections.unmodifiableSet(departments);
		return Set.of(departments.toArray(new Department[0]));
	}
	

	public JobStyle getJobStyle() {
		return jobStyle;
	}

    public void increaseSalary(Rate rate) {
		// Consistent Aggregate
		// Validation, Policy, Business Rule, Invariants, Constraint
    	this.salary = this.salary.multiply(1.0 + rate.value()/100.0);
    }
    
    public void promote(Department fromDepartment,Department toDepartment) {
    	this.jobStyle = JobStyle.FULL_TIME;
    	this.departments.remove(fromDepartment);
    	this.departments.add(toDepartment);
    }
}
