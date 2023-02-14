package com.works.services;

import com.works.entities.Product;
import com.works.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    final ProductRepository pRepo;

    public Product save( Product product ) {
        return pRepo.save(product);
    }

    public List<Product> list() {
        return  pRepo.findAll();
    }

    public Long delete(Long pid) {
        System.out.println("pid : " + pid);
        pRepo.deleteById(pid);
        return pid;
    }

}
