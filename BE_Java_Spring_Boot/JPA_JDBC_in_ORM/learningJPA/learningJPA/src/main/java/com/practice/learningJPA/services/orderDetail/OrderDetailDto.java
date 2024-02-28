package com.practice.learningJPA.services.orderDetail;

import com.practice.learningJPA.services.book.BookDto;
import com.practice.learningJPA.services.order.OrderDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailDto {

    private Long id;

    private OrderDto orderDto;

    private BookDto bookDto;
}
