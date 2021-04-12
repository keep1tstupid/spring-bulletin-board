package com.example.bb.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {
    // view for testing
    @GetMapping("/test")
    public String test() {
        return "testView";
    }
}
