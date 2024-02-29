package com.practice.learningJPA.payloads.requests;


import com.practice.learningJPA.services.category.CategoryDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateBookRequest {

    private Long id;
    private String title;
    private CategoryDto categoryDto;
}
