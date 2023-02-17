package com.works.ifeings;

import com.works.props.DummyProduct;
import com.works.props.JWTUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "IDummyJson", url = "https://dummyjson.com/")
public interface IDummyJson {

    @GetMapping("products")
    DummyProduct dumyProduct();

    @PostMapping("auth/login")
    Object login(@RequestBody JWTUser jwtUser);

}
