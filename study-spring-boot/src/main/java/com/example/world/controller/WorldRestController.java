package com.example.world.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.world.dao.CountryDao;
import com.example.world.entity.Country;

@RestController
@RequestMapping("/countries")
//@Scope("request")
//@RequestScope
@Validated
@CrossOrigin
public class WorldRestController {
	private final CountryDao countryDao;
	
	public WorldRestController(CountryDao countryDao) {
		this.countryDao = countryDao;
	}

	// GET http://localhost:9100/world/api/v1/countries/TUR
	@GetMapping("/{code}")
	public Country getCountryByCode(@PathVariable String code){
		return countryDao.findCountryByCode(code);
	}
}
