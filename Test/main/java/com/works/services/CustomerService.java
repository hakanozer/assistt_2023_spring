package com.works.services;

import com.works.entities.Customer;
import com.works.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {

    final CustomerRepository cRepo;

    public ResponseEntity<Customer> add( Customer customer ) {
        cRepo.save(customer);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

}
