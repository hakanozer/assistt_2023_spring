package com.works.restcontrollers;

import com.works.services.SagaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class SagaRestController {

    final SagaService sagaService;

    @GetMapping("/save")
    public Map save() {
        return sagaService.save();
    }

}
