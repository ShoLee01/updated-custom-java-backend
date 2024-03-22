package com.paymentchain.customer.mapping;

import com.paymentchain.customer.domain.entities.Customer;
import com.paymentchain.customer.resource.CustomerResource;
import com.paymentchain.customer.resource.CustomerSaveResource;
import com.paymentchain.customer.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomerMapper implements Serializable {
    @Autowired
    EnhancedModelMapper modelMapper;

    // Object Mapping
    public CustomerResource toResource(Customer customer) {
        return modelMapper.map(customer, CustomerResource.class);
    }

    public Customer toModelSaveResource(CustomerSaveResource customerSaveResource) {
        return modelMapper.map(customerSaveResource, Customer.class);
    }

    public List<CustomerResource> toResource(List<Customer> models) {
        return models.stream().map(this::toResource).collect(Collectors.toList());
    }
}
