package com.works.repositories;

import com.works.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    boolean existsByEmailEqualsIgnoreCase(String email);
    Optional<Customer> findByEmailEqualsIgnoreCase(String email);

    // select * from customer where email = ?
    // Optional<Customer> findByEmailEquals( String email );

}