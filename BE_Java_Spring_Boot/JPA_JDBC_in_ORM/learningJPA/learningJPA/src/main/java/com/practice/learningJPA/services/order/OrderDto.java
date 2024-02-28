package com.practice.learningJPA.services.order;

import com.practice.learningJPA.entities.OrderDetail;
import com.practice.learningJPA.services.book.BookDto;
import com.practice.learningJPA.services.orderDetail.OrderDetailDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {

    private Long id;

    private LocalDate orderDate;

    private List<OrderDetailDto> orderDetailDtoList;

}
