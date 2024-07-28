package com.example.oreumiweek3.service;

import com.example.oreumiweek3.domain.CustomerDto;
import com.example.oreumiweek3.entity.Customer;
import com.example.oreumiweek3.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Transactional
    public CustomerDto create(CustomerDto dto) {
        Customer customer = dto.toEntity();
        Customer saved = customerRepository.save(customer);
        return CustomerDto.fromEntity(saved);
    }

    @Transactional(readOnly = true)
    public List<CustomerDto> get() {
        return customerRepository.findAll().stream().map(CustomerDto::fromEntity).toList();
    }

    @Transactional(readOnly = true)
    public Optional<CustomerDto> get(Long id) {
        return customerRepository.findById(id).map(CustomerDto::fromEntity);
    }

    @Transactional
    public Optional<CustomerDto> update(Long id, CustomerDto dto) {
        Optional<Customer> customer = customerRepository.findById(id);
        return customer.map(c -> {
            dto.updateEntity(c);
            return CustomerDto.fromEntity(customerRepository.save(c));
        });
    }

    @Transactional
    public boolean delete(Long id) {
        return customerRepository.findById(id).map(c -> {
            customerRepository.delete(c);
            return true;
        }).orElse(false);
    }
}
