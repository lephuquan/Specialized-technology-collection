package com.lpq.springsecurity.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api")
@Validated
@RequiredArgsConstructor
public class CheckAccessBaseRoleController {

    @GetMapping("/user")
    public String userAccess(){
        return "Only user can access this api";
    }

    @GetMapping("/admin")
    public String adminAccess(){
        return "Only admin can access this api";
    }

    @GetMapping("/admin-user")// not guest
    public String adminAndUserAccess(){
        return "Admin and User can access this api, guest can not";
    }
}
