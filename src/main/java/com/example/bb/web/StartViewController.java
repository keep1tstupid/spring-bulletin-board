package com.example.bb.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StartViewController {
    @GetMapping("/")
    public String start() { return "testView"; }
}
