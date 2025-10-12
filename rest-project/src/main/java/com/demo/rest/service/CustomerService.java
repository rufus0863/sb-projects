package com.demo.rest.service;

import com.demo.rest.dto.CustomerRequest;
import com.demo.rest.dto.CustomerResponse;

import java.util.List;

public interface CustomerService {
    List<CustomerResponse> findAll();
    CustomerResponse findById(Long id);
    CustomerResponse create(CustomerRequest request);
    CustomerResponse update(Long id, CustomerRequest request);
    void delete(Long id);
}
