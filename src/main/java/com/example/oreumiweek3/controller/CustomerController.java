package com.example.oreumiweek3.controller;

import com.example.oreumiweek3.domain.CustomerDto;
import com.example.oreumiweek3.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {
    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public List<CustomerDto> getAll() {
        return customerService.get();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<CustomerDto> getById(@PathVariable(name = "id") Long id) {
        Optional<CustomerDto> dto = customerService.get(id);
        return ResponseEntity.of(dto);
    }

    @PostMapping
    public ResponseEntity<CustomerDto> create(@RequestBody CustomerDto dto) {
        return new ResponseEntity<>(customerService.create(dto), HttpStatus.CREATED);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<CustomerDto> update(@PathVariable("id") Long id, @RequestBody CustomerDto dto) {
        return ResponseEntity.of(customerService.update(id, dto));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        return customerService.delete(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
