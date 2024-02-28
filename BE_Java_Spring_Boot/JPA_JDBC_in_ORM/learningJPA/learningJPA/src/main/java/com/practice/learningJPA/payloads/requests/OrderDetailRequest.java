package com.practice.learningJPA.payloads.requests;

import com.practice.learningJPA.entities.Book;
import com.practice.learningJPA.entities.Order;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailRequest {
    private Long id;

    private Order order;

    private Book book;
}
