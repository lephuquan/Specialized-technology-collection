package com.practice.learningJPA.controller;

import com.practice.learningJPA.services.order.OrderServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orderDetail")
@RequiredArgsConstructor
public class OrderController {

    private  final OrderServiceImpl orderService;

}
