package com.practice.learningJPA.services.orderDetail;

import com.practice.learningJPA.entities.Book;
import com.practice.learningJPA.entities.Order;

public record GetOrderDetailRecord(Long id,
                                   Book book,
                                   Order order) {}
