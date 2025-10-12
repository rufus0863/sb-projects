package com.demo.service;

import com.demo.dto.CustomerRequest;
import com.demo.dto.CustomerResponse;

import java.util.List;

public interface CustomerService {
    List<CustomerResponse> findAll();
    CustomerResponse findById(Long id);
    CustomerResponse create(CustomerRequest request);
    CustomerResponse update(Long id, CustomerRequest request);
    void delete(Long id);
}
