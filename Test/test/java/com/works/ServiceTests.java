package com.works;

import com.works.services.ProductServicex;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ServiceTests {

    @Autowired
    private ProductServicex productServicex;

    @Test
    public void realDeleteTest() {
        Long pid = productServicex.delete(1l);
        //Assertions.assertEquals(pid, 1);
    }

}
