package com.javagda25.spring_students2.spring_students2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/")
public class IndexController {

    @GetMapping(path = "/hello")
    public String helloWeb(){
        return "index";
    }
}
