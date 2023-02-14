package com.works.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class SettingsController {

    @GetMapping("/settings")
    public String settings() {
        return "settings";
    }

}
