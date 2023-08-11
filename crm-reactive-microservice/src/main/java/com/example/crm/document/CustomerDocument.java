package com.example.crm.document;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "identity")
@Document(collection = "customers")
public class CustomerDocument {
	@Id
	private String identity;
	private String fullName;
	@Indexed(unique = true)
	private String email;
	private List<Address> addresses;
	private List<Phone> phones;
	private int birthYear;
}
