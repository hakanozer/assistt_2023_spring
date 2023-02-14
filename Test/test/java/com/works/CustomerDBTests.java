package com.works;

import com.works.entities.Customer;
import com.works.repositories.CustomerRepository;
import com.works.services.CustomerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

@DataJpaTest
public class CustomerDBTests {

    @Autowired
    CustomerRepository cRepo;

    @Test
    @DisplayName("Customer Add Tests")
    public void addTest() {
        Customer customer = new Customer();
        customer.setName("Ahmet Bilmem");
        customer.setEmail("ahmet@mail.com");
        cRepo.save(customer);
        Assertions.assertNotNull(cRepo);
        Assertions.assertEquals(customer.getCid(), 1);
    }

    @Test
    public void emailTest() {
        Optional<Customer> optionalCustomer = cRepo.findByEmailEquals("ali@mail.com");
        Assertions.assertNotNull(optionalCustomer);
        //Assertions.assertNotNull(optionalCustomer.get());
        if ( optionalCustomer.isPresent() ) {
            System.out.println( optionalCustomer.get() );
        }else {
            System.out.println("Customer Email Null");
        }
    }
}
