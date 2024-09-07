package com.SnowSoft.springboot_web_camel.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.SnowSoft.springboot_web_camel.country.CountryEntity;
import com.SnowSoft.springboot_web_camel.repository.CountryRepository;
import com.SnowSoft.springboot_web_camel.util.ResponseDTO;

@Service
public class CountrysService {

    @Autowired
    private CountryRepository countryRepository;

    public List<CountryEntity> requestCountrys() {
    	try {
    		return countryRepository.findAll();
		} catch (Exception e) {
			throw new RuntimeException("An unexpected error occurred while searching for countrys: " + e.getMessage());
		}
    }

    public Optional<CountryEntity> findCountryName(String ctryName) {
    	 try {
	        return countryRepository.findByCtryNameIgnoreCase(ctryName);
    	 } catch (Exception e) {
	        throw new RuntimeException("An unexpected error occurred while searching for country: " + e.getMessage());
    	 }
    }

    public ResponseEntity<?> createCountry(CountryEntity country) {
    	try {
	    	countryRepository.save(country);
	        return ResponseEntity.ok(new ResponseDTO("Correct", country));
	    }catch(Exception e) {
	    	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body(new ResponseDTO("Error", "An unexpected error occurred in createCountry: " + e.getMessage()));
	    }
    }

    public ResponseEntity<?> createCountryV2(CountryEntity country) {
    	try {
	    	return findCountryName(country.getCtryName()).map(getCtry -> {
	            return ResponseEntity.status(HttpStatus.CONFLICT)
	                    .body(new ResponseDTO("Error", "Country " + country.getCtryName() + " already exists"));
	        }).orElseGet(() -> {
	            countryRepository.save(country);
	            return ResponseEntity.ok(new ResponseDTO("Correct", country));
	        });
	    }catch(Exception e) {
	    	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body(new ResponseDTO("Error", "An unexpected error occurred in createCountryV2: " + e.getMessage()));
	    }
    }

    public ResponseEntity<?> updateCountry(Integer ctryId, CountryEntity country) {
        try {
        	return getCountryById(ctryId).map(getCtry -> {
                getCtry.setCtryName(country.getCtryName());
                createCountry(getCtry);
                return ResponseEntity.ok(new ResponseDTO("Correct", getCtry));
            }).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseDTO("Error", "Country id not found")));
        }catch(Exception e) {
        	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO("Error", "An unexpected error occurred in updateCountry: " + e.getMessage()));
        }
    }

    public Optional<CountryEntity> getCountryById(Integer ctryId) {
    	try {
    		return countryRepository.findById(ctryId);
	    } catch (Exception e) {
			throw new RuntimeException("An unexpected error occurred while searching for countrys: " + e.getMessage());
		}
    }

    public ResponseEntity<?> deleteCountry(Integer ctryId) {
    	try {
	    	return getCountryById(ctryId).map(getCtry -> {
	            countryRepository.deleteById(ctryId);
	            return ResponseEntity.ok(new ResponseDTO("Correct", "Deleted country: " + getCtry.getCtryName()));
	        }).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
	                .body(new ResponseDTO("Error", "Country id not found")));
    	}catch(Exception e) {
    			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
    					.body(new ResponseDTO("Error", "An unexpected error occurred in deleteCountry: " + e.getMessage()));
    	}
    }
}
