package com.demo.service;

import com.demo.model.Customer;
import com.demo.repository.CustomerRepository;
import com.demo.dto.CustomerRequest;
import com.demo.dto.CustomerResponse;
import com.demo.exception.ConflictException;
import com.demo.exception.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository repository;

    public CustomerServiceImpl(CustomerRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<CustomerResponse> findAll() {
        return repository.findAll().stream().map(this::toResponse).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public CustomerResponse findById(Long id) {
        Customer c = repository.findById(id).orElseThrow(() -> new NotFoundException("Customer " + id + " not found"));
        return toResponse(c);
    }

    @Override
    public CustomerResponse create(CustomerRequest request) {
        if (repository.existsByEmail(request.getEmail())) {
            throw new ConflictException("Email already in use");
        }
        Customer c = new Customer(null, request.getName(), request.getEmail(), request.getBirthDate());
        repository.save(c);
        return toResponse(c);
    }

    @Override
    public CustomerResponse update(Long id, CustomerRequest request) {
        Customer c = repository.findById(id).orElseThrow(() -> new NotFoundException("Customer " + id + " not found"));
        if (repository.existsByEmailAndIdNot(request.getEmail(), id)) {
            throw new ConflictException("Email already in use by another customer");
        }
        c.setName(request.getName());
        c.setEmail(request.getEmail());
        c.setBirthDate(request.getBirthDate());
        return toResponse(c);
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new NotFoundException("Customer " + id + " not found");
        }
        repository.deleteById(id);
    }

    private CustomerResponse toResponse(Customer c) {
        return new CustomerResponse(c.getId(), c.getName(), c.getEmail(), c.getBirthDate());
    }
}
