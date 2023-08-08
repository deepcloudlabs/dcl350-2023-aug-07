package com.example.hr.application.business;

import com.example.ddd.BusinessException;
import com.example.hr.domain.TcKimlikNo;

@SuppressWarnings("serial")
@BusinessException
public class ExistingEmployeeException extends Exception {
	private final TcKimlikNo identity;

	public ExistingEmployeeException(String message, TcKimlikNo identity) {
		super(message);
		this.identity = identity;
	}

	public TcKimlikNo identity() {
		return identity;
	}
	
}
