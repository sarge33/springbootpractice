package com.tutorialspoint.demo.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebController {
    @RequestMapping(value = "/index")
    public String index() {
        // Note: this points to resources/templates/index.html;
        return "index";
    }
}
