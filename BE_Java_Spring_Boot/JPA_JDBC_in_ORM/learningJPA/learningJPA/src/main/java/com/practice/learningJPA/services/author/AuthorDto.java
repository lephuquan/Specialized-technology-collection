package com.practice.learningJPA.services.author;

import com.practice.learningJPA.entities.Address;
import com.practice.learningJPA.entities.Book;
import com.practice.learningJPA.services.book.BookDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorDto {

    private Long id;

    private String name;

    private Address address;

    private List<BookDto> bookDtoList;
}
