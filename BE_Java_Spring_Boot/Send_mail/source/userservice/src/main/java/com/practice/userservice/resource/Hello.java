package com.practice.userservice.resource;


import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hello")
public class Hello {

    @GetMapping()
    public String hello() {
        return  "Hello";
    }

}