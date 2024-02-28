package com.practice.learningJPA.services.category;

import com.practice.learningJPA.payloads.responses.HttpResponse;
import org.springframework.http.ResponseEntity;

public interface ICategoryService {

    ResponseEntity<HttpResponse> addCategory(CategoryDto categoryDto);
}
