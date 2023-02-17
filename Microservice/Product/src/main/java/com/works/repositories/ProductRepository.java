package com.works.repositories;

import com.works.entities.Product;
import com.works.projections.IProJoinCatProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query( value = "select PID.pid, PID.price, PID.title, C.NAME from PRODUCT as PID \n" +
            "    inner join PRODUCT_CATEGORIES PC on PID.PID = PC.PRODUCT_PID\n" +
            "    inner join CATEGORY C on C.CID = PC.CATEGORIES_CID where c.CID = ?1", nativeQuery = true)
    Page<IProJoinCatProjection> proCatJoin(Long cid, Pageable pageable);

}