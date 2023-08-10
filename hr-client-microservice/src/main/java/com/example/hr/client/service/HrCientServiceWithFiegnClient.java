package com.example.hr.client.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class HrCientServiceWithFiegnClient {
	private static final String tcKimlikNo = "70777100098";

	private final HrService hrService;
	
	public HrCientServiceWithFiegnClient(HrService hrService) {
		this.hrService = hrService;
	}

	@Scheduled(fixedRate=3_000)
	public void callHrService() {
		System.out.println(hrService.getEmployeeByIdentity(tcKimlikNo));

	}
}
