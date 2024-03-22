package com.paymentchain.customer.domain.persistence;

import com.paymentchain.customer.domain.entities.Customer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Qualifier("customerRepository")
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findById(Long id);

    @Query("SELECT c FROM Customer c")
    List<Customer> getAll();
}
