package com.works.restcontrollers;

import com.works.entities.Product;
import com.works.entities.UserSession;
import com.works.services.ProductService;
import com.works.services.RedisService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductRestController {

    final ProductService productService;
    final RedisService redisService;

    @PostMapping("/save")
    public ResponseEntity save(@RequestBody Product product) {
        return productService.save(product);
    }

    @GetMapping("/allProduct")
    public ResponseEntity allProduct() {
        return productService.allProdut();
    }

    @GetMapping("/single/{pid}")
    public  ResponseEntity single( @PathVariable Long pid ) {
        return productService.singleProduct(pid);
    }

    @GetMapping("/list")
    public  ResponseEntity list( @RequestParam(defaultValue = "1") Long cid, @RequestParam(defaultValue = "0") int pageCount ) {
        return productService.list( cid, pageCount );
    }

    @GetMapping("/sessions")
    public Iterable<UserSession> sessions(  ) {
        return redisService.list();
    }



}
