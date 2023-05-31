package com.dreamthoughts.spring.jpa.search.filter.demo.service.impl;

import java.util.Objects;

import org.springframework.data.jpa.domain.Specification;

import com.dreamthoughts.spring.jpa.search.filter.demo.entity.Address;
import com.dreamthoughts.spring.jpa.search.filter.demo.entity.SearchCriteria;
import com.dreamthoughts.spring.jpa.search.filter.demo.entity.SearchOperation;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

/*
 * Payload example
 * {
    "dataOption":"all",
    "searchCriteriaList":[
       {
          "filterKey":"deptName",
          "operation":"eq",
          "value":"MARKETING"
       },
       {
          "filterKey":"salary",
          "operation":"lt",
          "value":2957
       },
       {
          "filterKey":"emplastNm",
          "operation":"ne",
          "value":"Stella"
       },
       {
          "filterKey":"commission",
          "operation":"gt",
          "value":0
       },
       {
          "filterKey":"empId",
          "operation":"eq",
          "value":64989
       }
    ]
}
 */
public class AddressSpecification implements Specification<Address> {

	private final SearchCriteria searchCriteria;

	public AddressSpecification(final SearchCriteria searchCriteria) {
		super();
		this.searchCriteria = searchCriteria;
	}

	@Override
	public Predicate toPredicate(Root<Address> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		String strToSearch = searchCriteria.getValue().toString().toLowerCase();

		switch (Objects.requireNonNull(SearchOperation.getSimpleOperation(searchCriteria.getOperation()))) {
		case CONTAINS:
			if (searchCriteria.getFilterKey().equals("addressLine1")) {
				return cb.like(cb.lower(root.get(searchCriteria.getFilterKey())), "%" + strToSearch + "%");
			} else if (searchCriteria.getFilterKey().equals("addressLine2")) {
				return cb.like(cb.lower(root.get(searchCriteria.getFilterKey())), "%" + strToSearch + "%");
			}
			return cb.like(cb.lower(root.get(searchCriteria.getFilterKey())), "%" + strToSearch + "%");

		case DOES_NOT_CONTAIN:
			return cb.notLike(cb.lower(root.get(searchCriteria.getFilterKey())), "%" + strToSearch + "%");

		case BEGINS_WITH:
			return cb.like(cb.lower(root.get(searchCriteria.getFilterKey())), strToSearch + "%");

		case DOES_NOT_BEGIN_WITH:
			return cb.notLike(cb.lower(root.get(searchCriteria.getFilterKey())), strToSearch + "%");

		case ENDS_WITH:
			return cb.like(cb.lower(root.get(searchCriteria.getFilterKey())), "%" + strToSearch);

		case DOES_NOT_END_WITH:
			return cb.notLike(cb.lower(root.get(searchCriteria.getFilterKey())), "%" + strToSearch);

		case EQUAL:
			if (searchCriteria.getFilterKey().equals("addressLine1")) {
				return cb.like(cb.lower(root.get(searchCriteria.getFilterKey())), "%" + strToSearch + "%");
			} else if (searchCriteria.getFilterKey().equals("addressLine2")) {
				return cb.like(cb.lower(root.get(searchCriteria.getFilterKey())), "%" + strToSearch + "%");
			}
			return cb.equal(root.get(searchCriteria.getFilterKey()), searchCriteria.getValue());

		case NOT_EQUAL:
			return cb.notEqual(root.get(searchCriteria.getFilterKey()), searchCriteria.getValue());

		case NUL:
			return cb.isNull(root.get(searchCriteria.getFilterKey()));

		case NOT_NULL:
			return cb.isNotNull(root.get(searchCriteria.getFilterKey()));

		case GREATER_THAN:
			return cb.greaterThan(root.<String>get(searchCriteria.getFilterKey()),
					searchCriteria.getValue().toString());

		case GREATER_THAN_EQUAL:
			return cb.greaterThanOrEqualTo(root.<String>get(searchCriteria.getFilterKey()),
					searchCriteria.getValue().toString());

		case LESS_THAN:
			return cb.lessThan(root.<String>get(searchCriteria.getFilterKey()), searchCriteria.getValue().toString());

		case LESS_THAN_EQUAL:
			return cb.lessThanOrEqualTo(root.<String>get(searchCriteria.getFilterKey()),
					searchCriteria.getValue().toString());
		}

		return null;
	}

}
