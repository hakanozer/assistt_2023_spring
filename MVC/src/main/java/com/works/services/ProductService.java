package com.works.services;

import com.works.entities.Product;
import com.works.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    final ProductRepository productRepository;

    public boolean save(Product product) {
        try {
            productRepository.save(product);
            return true;
        }catch (Exception ex) {
            return false;
        }
    }

    public List<Product> list() {
        return productRepository.findAll();
    }

    public boolean delete( Long pid ) {
        try {
            productRepository.deleteById(pid);
            return true;
        }catch (Exception ex) {
            return false;
        }
    }

}
