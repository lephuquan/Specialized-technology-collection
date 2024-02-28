package com.practice.learningJPA.payloads.requests;

import com.practice.learningJPA.entities.Book;
import com.practice.learningJPA.entities.Order;
import com.practice.learningJPA.services.book.BookDto;
import com.practice.learningJPA.services.order.OrderDto;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddOrderDetailRequest {
    private Long id;

    private OrderDto orderDto;

    private BookDto bookDto;
}
