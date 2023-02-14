package com.works;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.works.entities.Customer;
import com.works.restcontrollers.CustomerRestController;
import com.works.services.CustomerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = {CustomerRestController.class})
public class CustomerRestControllerTests {

    @MockBean
    private CustomerService customerService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    public void addCustomerTest() throws Exception {

        Customer customer = new Customer();
        customer.setName("Zehra Bilsin");
        customer.setEmail("zehra@mail.com");

        String customerString = objectMapper.writeValueAsString(customer);
        String url = "/customer/add";
        mockMvc.perform(
                MockMvcRequestBuilders.post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(customerString)
        ).andExpect(MockMvcResultMatchers.status().isOk());

    }

}
