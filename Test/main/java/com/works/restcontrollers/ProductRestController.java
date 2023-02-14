package com.works.restcontrollers;

import com.works.entities.Product;
import com.works.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductRestController {

    final ProductService pService;

    @PostMapping("/add/{catID}")
    public ResponseEntity<Product> add(
            @RequestBody Product product,
            @PathVariable String catID,
            @RequestParam(defaultValue = "") String model,
            @RequestParam(defaultValue = "") String color
    ) {
        System.out.println("catID :" + catID);
        System.out.println(model);
        System.out.println(color);
        System.out.println( product );
        return new ResponseEntity<>(pService.save(product), HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<List<Product>> list() {
        return new ResponseEntity<>(pService.list(), HttpStatus.OK);
    }

}
