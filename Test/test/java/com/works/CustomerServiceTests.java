package com.works;

import com.works.entities.Customer;
import com.works.services.CustomerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest
public class CustomerServiceTests {

    @Autowired
    private CustomerService service;

    @Test
    public void customerAdd() {
        Customer customer = new Customer();
        customer.setName("Serkan Bil");
        customer.setEmail("serkan@mail.com");
        ResponseEntity<Customer> responseEntity = service.add(customer);

        Assertions.assertAll(
                () -> Assertions.assertNotNull(responseEntity),
                () -> Assertions.assertNotEquals(customer.getCid(), 0),
                () -> Assertions.assertEquals(responseEntity.getStatusCode(), HttpStatus.OK)
        );
    }

}
