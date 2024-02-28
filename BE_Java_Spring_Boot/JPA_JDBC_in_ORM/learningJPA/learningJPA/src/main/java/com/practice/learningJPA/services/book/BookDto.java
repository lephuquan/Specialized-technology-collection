package com.practice.learningJPA.services.book;


import com.practice.learningJPA.entities.Order;
import com.practice.learningJPA.services.author.AuthorDto;
import com.practice.learningJPA.services.category.CategoryDto;
import com.practice.learningJPA.services.order.OrderDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data // Lỗi: khi thieu setter/getter thi gia tri cua các thuoc tinh gui ve se bị null
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {

    private Long id;

    private String title;

    private OrderDto orderDto;

    private CategoryDto categoryDto;

    private List<AuthorDto> authorDtoList;
}
