package com.works.IFeigns;

import com.works.props.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "product")
public interface IProduct {

    @GetMapping("/product/single/{pid}")
    Product single(@RequestHeader("token") String token, @PathVariable Long pid );

}
