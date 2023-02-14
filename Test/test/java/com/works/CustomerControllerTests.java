package com.works;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.works.entities.Customer;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CustomerControllerTests {

    @Autowired private ObjectMapper objectMapper;

    RestTemplate restTemplate;
    @BeforeAll
    public void beforeAll() {
        restTemplate = new RestTemplate();
    }

    @Test
    public void restAdd() throws Exception {
        String url = "http://localhost:8090/customer/add";

        String sendSt = "{ email: 'erkan@mail.com', name: 'Erkan Bilsin' }";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> request = new HttpEntity( sendSt , headers);
        ResponseEntity<Customer> response = restTemplate.postForEntity(url, sendSt, Customer.class );
        System.out.println( response.getBody() );
        Assertions.assertEquals(response.getStatusCodeValue(), 200);
        Assertions.assertNotNull(response.getBody());

    }

}
