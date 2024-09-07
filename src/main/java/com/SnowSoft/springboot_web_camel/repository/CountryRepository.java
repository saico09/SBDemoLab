package com.SnowSoft.springboot_web_camel.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.SnowSoft.springboot_web_camel.country.CountryEntity;

public interface CountryRepository extends JpaRepository<CountryEntity, Integer>{
	Optional<CountryEntity> findByCtryNameIgnoreCase(String ctryName);
}
