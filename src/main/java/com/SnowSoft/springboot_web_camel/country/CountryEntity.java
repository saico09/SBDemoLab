package com.SnowSoft.springboot_web_camel.country;

import org.hibernate.validator.constraints.UniqueElements;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="country")
public class CountryEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "country_seq")//se le puede dar cualquier nombre
    @SequenceGenerator(name = "country_seq", sequenceName = "country_ctry_id_seq", allocationSize = 1)
	private Integer ctryId;
	

	@NotBlank(message = "Country name cannot be null or empty")
    @Size(min = 2, max = 255, message = "Country name must be between 2 and 255 characters")
	@Column(name = "ctry_name")
	private String ctryName;
	
	public Integer getCtryId() {
		return ctryId;
	}
	public void setCtryId(Integer ctryId) {
		this.ctryId = ctryId;
	}
	public String getCtryName() {
		return ctryName;
	}
	public void setCtryName(String ctryName) {
		this.ctryName = ctryName;
	}
	
}
