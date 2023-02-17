package com.works.repositories;

import com.works.entities.ProductCat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductCatRepository extends JpaRepository<ProductCat, Long> {

    @Query(value = "select PRODUCT.pid, price, title, C.NAME from PRODUCT\n" +
            "    inner join PRODUCT_CATEGORIES PC on PRODUCT.PID = PC.PRODUCT_PID\n" +
            "    inner join CATEGORY C on C.CID = PC.CATEGORIES_CID", nativeQuery = true)
    List<ProductCat> proCat();

}