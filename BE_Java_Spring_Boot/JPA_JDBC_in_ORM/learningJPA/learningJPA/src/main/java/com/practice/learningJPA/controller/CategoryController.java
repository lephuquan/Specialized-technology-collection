package com.practice.learningJPA.controller;

import com.practice.learningJPA.payloads.responses.HttpResponse;
import com.practice.learningJPA.services.category.CategoryDto;
import com.practice.learningJPA.services.category.ICategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.function.EntityResponse;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {

    private final ICategoryService categoryService;

    @PostMapping("/add")
    public ResponseEntity<HttpResponse> addRepository(@RequestBody CategoryDto categoryDto){
        return categoryService.addCategory(categoryDto);
    }
}
