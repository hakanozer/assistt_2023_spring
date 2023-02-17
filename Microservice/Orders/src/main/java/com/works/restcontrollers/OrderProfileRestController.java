package com.works.restcontrollers;

import com.works.props.News;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders/profile")
public class OrderProfileRestController {

    @GetMapping("/user")
    public News news() {
        String apiKey = "38a9e086f10b445faabb4461c4aa71f8";
        return null;
    }

}
