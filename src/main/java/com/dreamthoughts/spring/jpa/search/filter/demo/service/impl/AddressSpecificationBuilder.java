package com.dreamthoughts.spring.jpa.search.filter.demo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.dreamthoughts.spring.jpa.search.filter.demo.entity.Address;
import com.dreamthoughts.spring.jpa.search.filter.demo.entity.SearchCriteria;
import com.dreamthoughts.spring.jpa.search.filter.demo.entity.SearchOperation;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddressSpecificationBuilder {

	private final List<SearchCriteria> params;

	public AddressSpecificationBuilder() {
		this.params = new ArrayList<>();
	}

	public final AddressSpecificationBuilder with(String key, String operation, Object value) {
		params.add(new SearchCriteria(key, operation, value));
		return this;
	}

	public final AddressSpecificationBuilder with(SearchCriteria searchCriteria) {
		params.add(searchCriteria);
		return this;
	}

	public Specification<Address> build() {
		if (params.size() == 0) {
			return null;
		}

		Specification<Address> result = new AddressSpecification(params.get(0));
		for (int idx = 1; idx < params.size(); idx++) {
			SearchCriteria criteria = params.get(idx);
			result = SearchOperation.getDataOption(criteria.getDataOption()) == SearchOperation.ALL
					? Specification.where(result).and(new AddressSpecification(criteria))
					: Specification.where(result).or(new AddressSpecification(criteria));
		}
		return result;
	}
}
