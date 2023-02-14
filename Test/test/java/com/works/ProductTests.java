package com.works;

import com.works.entities.Product;
import com.works.repositories.ProductRepository;
import com.works.services.ProductService;
import com.works.utils.Action;
import com.works.utils.DB;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DataJpaTest
@ExtendWith(SpringExtension.class)
//@RunWith(SpringRunner.class)
public class ProductTests {

    @Autowired
    private ProductRepository pRepo;

    // her şeyden önce bir kez çalış
    Action action;
    @BeforeAll
    public void beforeAll() {
        System.out.println("beforeAll - 1");
        action = new Action();
    }

    // Her @Test notasyonu için başta bir kez çalışkan
    int i = 0;
    @BeforeEach
    public void beforeEach() {
        i++;
        System.out.println("beforeEach - " + i);
    }

    @DisplayName("DB Control")
    @Test
    @Order(0)
    public void dbControl() {
        DB db = new DB();
        Assertions.assertEquals(db.connect(), true);
    }

    @DisplayName("Sayaç Testi")
    @Test
    @Order(1)
    public void countTest() {
        int count = action.count("Spring Test");
        Assertions.assertEquals(count, 11 );
    }

    @Test
    @Order(2)
    public void writeTest() {
        System.out.println("writeTest Call");
    }

    // data save test
    @Test
    @Order(3)
    public void saveTest() {
        Product product = new Product();
        product.setTitle("TV");
        product.setPrice(15000);
        pRepo.save(product);
        Assertions.assertEquals(product.getPid(), 1);
    }

    // product rest test
    @Test
    @Order(4)
    public void listTest() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8090/product/list";
        List<Product> list = restTemplate.getForObject(url, List.class);
        //Product[] listArr = restTemplate.getForObject(url, Product[].class);
        Assertions.assertAll(
                () -> Assertions.assertNotNull(list),
                () -> Assertions.assertEquals(list.size(), 2)
        );
    }


    // Her @Test notasyonu için bittikten sonra bir kez çalışkan
    @AfterEach
    public void afterEach() {
        System.out.println("afterEach Success "+ i);
    }

    // @Test notasyonu almış tüm üyeler bittikten sonra çalışır.
    @AfterAll
    public void afterAll() {
        System.out.println("afterAll Success");
    }



}
