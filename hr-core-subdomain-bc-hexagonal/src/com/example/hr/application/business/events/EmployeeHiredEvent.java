package com.example.hr.application.business.events;

import com.example.hr.domain.TcKimlikNo;

public class EmployeeHiredEvent extends HrEvent {

	public EmployeeHiredEvent(TcKimlikNo identity) {
		super(identity);
	}

}
