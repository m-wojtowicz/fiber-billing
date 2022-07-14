package com.comarch.fiberBilling.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/")
    @CrossOrigin("http://localhost:4200/")
    public String index(){
        System.out.println("Success");
        return "Success";
    }
}
