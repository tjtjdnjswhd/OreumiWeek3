package com.example.oreumiweek3.domain;

import com.example.oreumiweek3.entity.Customer;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

/**
 * DTO for {@link Customer}
 */
@AllArgsConstructor
@Getter
public class CustomerDto implements Serializable {
    private final Long id;
    private final String name;
    private final String phoneNumber;
    private final String address;

    public static CustomerDto fromEntity(Customer customer) {
        return new CustomerDto(customer.getId(), customer.getName(), customer.getPhoneNumber(), customer.getAddress());
    }

    public Customer toEntity() {
        return new Customer(name, phoneNumber, address);
    }

    public void updateEntity(Customer customer) {
        customer.setName(name);
        customer.setAddress(address);
        customer.setPhoneNumber(phoneNumber);
    }
}