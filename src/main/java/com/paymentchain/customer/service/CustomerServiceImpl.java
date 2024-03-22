package com.paymentchain.customer.service;

import com.paymentchain.customer.domain.entities.Customer;
import com.paymentchain.customer.domain.persistence.CustomerRepository;
import com.paymentchain.customer.domain.service.CustomerService;
import com.paymentchain.customer.resource.CustomerSaveResource;
import com.paymentchain.customer.shared.exception.ResourceNotFoundException;
import com.paymentchain.customer.shared.exception.ResourceValidationException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private final CustomerRepository customerRepository;

    @Autowired
    private final Validator validator;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<Customer> getAll() {
        return customerRepository.getAll();
    }

    @Override
    public Optional<Customer> getById(Long id) {
        return Optional.ofNullable(customerRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", id)));
    }

    @Override
    public Optional<Customer> create(Customer customer) {
        Set<ConstraintViolation<Customer>> violations = validator.validate(customer);
        if(!violations.isEmpty())
            throw new ResourceValidationException("Customer", violations);
        return Optional.of(customerRepository.save(customer));
    }

    @Override
    public Optional<Customer> update(Long id, Customer customer) {
        Set<ConstraintViolation<Customer>> violations = validator.validate(customer);
        if (!violations.isEmpty()) {
            throw new ResourceValidationException("Customer", violations);
        }

        var customerUpdated = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", id));

        modelMapper.typeMap(Customer.class, Customer.class).addMappings(mapper -> mapper.skip(Customer::setId));
        modelMapper.map(customer, customerUpdated);
        return Optional.of(customerRepository.save(customerUpdated));
    }

    @Override
    public Optional<Customer> delete(Long id) {
        var customerUpdated = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", id));
        customerRepository.delete(customerUpdated);
        return Optional.of(customerUpdated);
    }
}
