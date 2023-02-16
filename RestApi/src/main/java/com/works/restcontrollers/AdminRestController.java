package com.works.restcontrollers;

import com.works.entities.Admin;
import com.works.services.AdminDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminRestController {

    final AdminDetailService adminDetailService;

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody Admin admin) {
        return adminDetailService.register(admin);
    }

}
