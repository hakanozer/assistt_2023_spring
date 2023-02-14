package com.works;

import com.works.entities.Product;
import com.works.props.Currency;
import com.works.services.ProductService;
import com.works.utils.Action;
import com.works.utils.UseXml;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Random;

//@RunWith(SpringRunner.class)
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestPropertySource("classpath:application.properties")
@Import(value = {com.works.utils.Action.class, com.works.utils.DB.class})
public class XmlTests {

    @Autowired private ProductService productService;
    @Autowired private Random random;
    @Autowired private Action action;

    UseXml useXml;
    @BeforeAll
    public void beforeAll() {
        useXml = new UseXml();
    }

    @DisplayName("XML Currency - Dollar")
    @Test
    public void xmlTest() {
        List<Currency> currencies = useXml.xml();
        Assertions.assertAll(
                () -> Assertions.assertNotNull(currencies),
                () -> Assertions.assertEquals(currencies.get(0).getCurrencyName(), "US DOLLAR"),
                () -> Assertions.assertNotEquals(currencies.get(0).getForexSelling(), "")
        );
        System.out.println(currencies.get(0).getForexSelling());
    }


    // testin belirtilen sayı kadar tekrarlanmasını sağlıyor
    int i = 0;
    @RepeatedTest(5)
    public void repeatedTest() {
        ++i;
        Assertions.assertNotEquals(i % 2, 0);
    }

    // Teste parametre göndermeye yarar
    @ParameterizedTest
    @ValueSource(strings = {"İstanbul", "Ankara", "İzmir", "Adana"})
    public void valueSource( String item ) {
        Assertions.assertEquals(item.length(), 5);
    }

    // Teste birden fazla parametre gönderme
    @DisplayName("Csv Source")
    @ParameterizedTest(name = " 1a={0}, 2b={1} }")
    @CsvSource(value = { "50,60", "5,6", "33,44" })
    public void csvSource( int a, int b ) {
        boolean status = a + b > 50 ? true : false;
        Assertions.assertEquals(status, true);
    }

    // application.properties Test
    @Value("${data.apikey}")
    private String apikey;

    @Test
    public void propertiesTest() {
        System.out.println( apikey );
        Assertions.assertNotNull(apikey);
    }

    @Test
    public void productSaveTest() {
        Product product = new Product();
        product.setTitle("Bilgisayar");
        product.setPrice(3000);
        productService.save(product);
        Assertions.assertNotNull(product);
    }

    @Test
    public void randomTest() {
        System.out.println(random.nextInt(100));
        Assertions.assertNotNull(random);
    }

    @Test
    public void actionTest() {
        System.out.println(action.count("Spring Test"));
        Assertions.assertNotNull(action);
    }

}
