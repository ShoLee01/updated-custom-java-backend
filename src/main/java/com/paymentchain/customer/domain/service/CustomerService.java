package com.paymentchain.customer.domain.service;

import com.paymentchain.customer.domain.entities.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    List<Customer> getAll();
    Optional<Customer> getById(Long id);
    Optional<Customer> create(Customer customer);
    Optional<Customer> update(Long id, Customer customer);
    Optional<Customer> delete(Long id);
}
