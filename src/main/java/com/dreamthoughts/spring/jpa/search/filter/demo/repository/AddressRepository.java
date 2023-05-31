package com.dreamthoughts.spring.jpa.search.filter.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.dreamthoughts.spring.jpa.search.filter.demo.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Long>, JpaSpecificationExecutor<Address> {

	Address findAllByAddressId(Long addressId);

	List<Address> findAllByProvinceAndCountry(String province, String country);
}
