package com.dreamthoughts.spring.jpa.search.filter.demo.entity;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressSearchRequest {
    private List<SearchCriteria> searchCriteriaList;
    private String dataOption;
}
