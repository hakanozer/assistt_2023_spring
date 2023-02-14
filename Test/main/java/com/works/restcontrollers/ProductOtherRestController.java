package com.works.restcontrollers;

import com.works.entities.Product;
import com.works.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/productOther")
public class ProductOtherRestController {

    final ProductService pService;

    @DeleteMapping("/delete/{pid}")
    public ResponseEntity<Long> delete(@PathVariable Long pid) {
        Long deletePid = pService.delete(pid);
        return new ResponseEntity(deletePid, HttpStatus.OK);
    }

}
