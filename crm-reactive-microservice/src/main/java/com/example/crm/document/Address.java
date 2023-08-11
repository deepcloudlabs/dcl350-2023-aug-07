package com.example.crm.document;

import lombok.Data;

@Data
public class Address {
	private String street;
	private String city;
	private String country;
	private AddressType addressType;
}
