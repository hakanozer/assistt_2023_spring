package com.works;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.works.entities.Product;
import com.works.repositories.ProductRepository;
import com.works.restcontrollers.ProductOtherRestController;
import com.works.restcontrollers.ProductRestController;
import com.works.services.ProductService;
import com.works.services.ProductServicex;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = {ProductRestController.class, ProductOtherRestController.class})
public class RestTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ProductService pService;


    @Test
    public void addTest() throws Exception {

        Product product = new Product();
        product.setPrice(1000);
        product.setTitle("Spor Ayakkabı");

        mockMvc.perform(
        MockMvcRequestBuilders.post("/product/add/10")
        .contentType(MediaType.APPLICATION_JSON)
        .param("model", "nike")
        .param("color", "black")
        //.content(objectMapper.writeValueAsString(product))
        .content("{ \"title\": \"Tabletx\", \"price\": 16000 }")
        )
        .andExpect(MockMvcResultMatchers.status().isOk()
        );

    }

    @Test
    public void deleteTest() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.delete("/productOther/delete/100")
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }


}
