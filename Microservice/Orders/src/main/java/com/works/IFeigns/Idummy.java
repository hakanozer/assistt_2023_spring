package com.works.IFeigns;

import com.works.props.JWTLogin;
import com.works.props.JWTUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "dummy", url = "https://dummyjson.com/")
public interface Idummy {

    @PostMapping("auth/login")
    JWTLogin login(@RequestBody JWTUser jwtUser);

}
