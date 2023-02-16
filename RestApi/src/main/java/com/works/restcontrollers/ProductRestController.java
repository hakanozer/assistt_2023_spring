package com.works.restcontrollers;

import com.works.entities.Note;
import com.works.entities.Product;
import com.works.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductRestController {

    final ProductService productService;

    @PostMapping("/save")
    public ResponseEntity save(@Valid @RequestBody Product product) {
        return productService.save(product);
    }

    @GetMapping("/list")
    public ResponseEntity list( @RequestParam(defaultValue = "0") int pageCount ) {
        return productService.list(pageCount);
    }

}
