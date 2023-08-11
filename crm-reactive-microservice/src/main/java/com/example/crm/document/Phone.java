package com.example.crm.document;

import lombok.Data;

@Data
public class Phone {
	private String countryCode;
	private String number;
	private String extension;
	private PhoneType phoneType;
}
