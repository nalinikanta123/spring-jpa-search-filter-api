package com.dreamthoughts.spring.jpa.search.filter.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dreamthoughts.spring.jpa.search.filter.demo.entity.Address;
import com.dreamthoughts.spring.jpa.search.filter.demo.entity.AddressSearchRequest;
import com.dreamthoughts.spring.jpa.search.filter.demo.entity.SearchCriteria;
import com.dreamthoughts.spring.jpa.search.filter.demo.repository.AddressRepository;
import com.dreamthoughts.spring.jpa.search.filter.demo.service.impl.AddressService;
import com.dreamthoughts.spring.jpa.search.filter.demo.service.impl.AddressSpecificationBuilder;

@RestController
@RequestMapping("searchapi/v1")
public class AddressController {

	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private AddressService addressService;

	@GetMapping("/addressId/{id}")
	private Address getAddressById(@PathVariable Long id) {
		return addressRepository.findAllByAddressId(id);
	}

	@GetMapping("/addresses")
	private List<Address> getAllAddress() {
		return addressRepository.findAll();
	}

	@PostMapping("/search")
	public Page<Address> searchAddress(@RequestParam(name = "pageNum", defaultValue = "0") int pageNum,
			@RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
			@RequestBody AddressSearchRequest addressSearchRequest) {
		AddressSpecificationBuilder builder = new AddressSpecificationBuilder();
		List<SearchCriteria> criteriaList = addressSearchRequest.getSearchCriteriaList();
		if (criteriaList != null) {
			criteriaList.forEach(x -> {
				x.setDataOption(addressSearchRequest.getDataOption());
				builder.with(x);
			});
		}

		Pageable page = PageRequest.of(pageNum, pageSize, Sort.by("addressLine1").ascending()
				.and(Sort.by("addressLine2")).ascending().and(Sort.by("country")).ascending());

		Page<Address> addressPage = addressService.findBySearchCriteria(builder.build(), page);

		return addressPage;
	}
}
