package com.SnowSoft.springboot_web_camel.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException.BadRequest;

import com.SnowSoft.springboot_web_camel.country.CountryEntity;
import com.SnowSoft.springboot_web_camel.service.CountrysService;
import com.SnowSoft.springboot_web_camel.util.ResponseDTO;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class UbicationController {
	private final CountrysService countryService;
	Logger logger = LoggerFactory.getLogger(UbicationController.class);
	
	public UbicationController (CountrysService countryService) {
		this.countryService=countryService;
	}
	
	@GetMapping("/countrys")
	public List<CountryEntity> getCountrys() {
		return countryService.requestCountrys();
	}
	
	@PostMapping("/countrys")
	public ResponseEntity<?> postCountry(@Valid @RequestBody CountryEntity newContry){
		return countryService.createCountry(newContry);
	}
	
	@PostMapping("/countrys/v2")
	public ResponseEntity<?> postCountryV2(@Valid @RequestBody CountryEntity newCountry){
		return countryService.createCountryV2(newCountry);
	}
	
	@PutMapping("/countrys/{ctryid}")
	public ResponseEntity<?> putCountry(@Valid @PathVariable Integer ctryid,@RequestBody CountryEntity newCountry){
		return countryService.updateCountry(ctryid, newCountry);
	}
	
	@DeleteMapping("/countrys/{ctryid}")
	public ResponseEntity<?> deleteCountryV2(@PathVariable Integer ctryid){
		return countryService.deleteCountry(ctryid);
	}
}
