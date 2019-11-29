package com.tutorialspoint.demo.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewController {
    @RequestMapping("/view-products")
    public String viewProducts() {
        // Note: this points to resources/templates/view-products.html;
        return "view-products";

    }

    @RequestMapping("/add-products")
    public String addProducts() {
        // Note: this points to resources/templates/add-products.html;
        return "add-products";
    }

    @RequestMapping("/locale")
    public String locale() {
        return "locale";
    }

}

