package com.practice.learningJPA.payloads.responses;

import com.practice.learningJPA.entities.Book;
import com.practice.learningJPA.entities.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailResponse {
    private Long id;
    private Order order;
    private Book book;
}
