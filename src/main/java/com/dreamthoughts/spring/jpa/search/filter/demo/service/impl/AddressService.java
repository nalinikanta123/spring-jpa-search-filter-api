package com.dreamthoughts.spring.jpa.search.filter.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.dreamthoughts.spring.jpa.search.filter.demo.entity.Address;
import com.dreamthoughts.spring.jpa.search.filter.demo.entity.AddressSearchRequest;
import com.dreamthoughts.spring.jpa.search.filter.demo.repository.AddressRepository;

@Service
public class AddressService {
	@Autowired
	private AddressRepository addressRepository;

	public AddressSearchRequest addAddress(AddressSearchRequest addressSearchRequest) {
		return null;
	}

	public List<Address> findAllEmployee() {
		return addressRepository.findAll();
	}

	public Page<Address> findBySearchCriteria(Specification<Address> spec, Pageable page) {
		Page<Address> searchResult = addressRepository.findAll(spec, page);
		return searchResult;
	}
}
