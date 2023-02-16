package com.works.services;

import com.works.entities.Note;
import com.works.entities.Product;
import com.works.repositories.ProductRepository;
import com.works.utils.REnum;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.apache.log4j.Logger;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ProductService {

    final ProductRepository productRepository;
    private static Logger logger = Logger.getLogger(ProductService.class);

    public ResponseEntity save(Product product) {
        Map<REnum, Object> hm = new LinkedHashMap<>();
        try {
            productRepository.save(product);
            hm.put(REnum.status, true);
            hm.put(REnum.result, product);
            logger.trace( "Arg-1");
            logger.debug("debug message");
            logger.error("error message");
            logger.trace("trace message");
            logger.info("info message");
            logger.warn("warn message");
            return new ResponseEntity(hm, HttpStatus.OK);
        }catch (Exception ex) {
            hm.put(REnum.status, false);
            hm.put(REnum.errors, ex.getMessage());
            return new ResponseEntity(hm, HttpStatus.BAD_REQUEST);
        }
    }


    public ResponseEntity list( int pageCount ) {
        Pageable pageable = PageRequest.of(pageCount, 5);
        Page<Product> productPage = productRepository.findAll(pageable);
        return new ResponseEntity(productPage, HttpStatus.OK);
    }

}
