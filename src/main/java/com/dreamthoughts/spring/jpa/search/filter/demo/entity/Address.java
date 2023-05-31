package com.dreamthoughts.spring.jpa.search.filter.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
//refer https://medium.com/@cmmapada/advanced-search-and-filtering-using-spring-data-jpa-specification-and-criteria-api-b6e8f891f2bf
@Entity
@Table(name = "address")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {
	
	@Id
	private Long addressId;
	private String addressLine1;
	private String addressLine2;
	private String city;
	private String province;
	private String country;
	private String zipcode;

}
