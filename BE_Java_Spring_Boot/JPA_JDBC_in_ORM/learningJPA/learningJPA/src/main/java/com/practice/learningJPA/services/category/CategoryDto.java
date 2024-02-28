package com.practice.learningJPA.services.category;


import com.practice.learningJPA.entities.Book;
import com.practice.learningJPA.services.book.BookDto;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {

    private Long id;

    private String name;

    private List<BookDto> bookDtoList;
}
