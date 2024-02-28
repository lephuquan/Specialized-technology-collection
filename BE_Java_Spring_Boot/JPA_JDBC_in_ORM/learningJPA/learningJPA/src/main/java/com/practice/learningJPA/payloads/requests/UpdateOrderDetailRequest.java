package com.practice.learningJPA.payloads.requests;

import com.practice.learningJPA.services.book.BookDto;
import com.practice.learningJPA.services.order.OrderDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateOrderDetailRequest {
    private Long id;

    private OrderDto orderDto;

    private BookDto bookDto;
}
