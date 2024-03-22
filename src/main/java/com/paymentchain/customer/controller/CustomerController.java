package com.paymentchain.customer.controller;

import com.paymentchain.customer.domain.persistence.CustomerRepository;
import com.paymentchain.customer.domain.service.CustomerService;
import com.paymentchain.customer.mapping.CustomerMapper;
import com.paymentchain.customer.resource.CustomerResource;
import com.paymentchain.customer.resource.CustomerSaveResource;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/customer")
public class CustomerController {
    @Autowired
    private final CustomerService customerService;

    @Autowired
    private final CustomerMapper customerMapper;

    @GetMapping
    public List<CustomerResource> getAll(){
        return customerMapper.toResource(customerService.getAll());
    }

    @GetMapping(value = "/{id}")
    public CustomerResource getById(@PathVariable Long id){
        return customerMapper.toResource(customerService.getById(id).get());
    }

    @PostMapping
    public CustomerResource create(@RequestBody CustomerSaveResource customerSaveResource){
        return customerMapper.toResource(customerService.create(customerMapper.toModelSaveResource(customerSaveResource)).get());
    }

    @PutMapping(value = "/{id}")
    public CustomerResource update(@PathVariable Long id, @RequestBody CustomerSaveResource customerSaveResource){
        return customerMapper.toResource(customerService.update(id, customerMapper.toModelSaveResource(customerSaveResource)).get());
    }

    @DeleteMapping(value = "/{id}")
    public CustomerResource delete(@PathVariable Long id){
        return customerMapper.toResource(customerService.delete(id).get());
    }
}
