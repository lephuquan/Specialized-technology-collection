package com.practice.learningJPA.controller;

import com.practice.learningJPA.payloads.requests.AddOrderDetailRequest;
import com.practice.learningJPA.payloads.requests.UpdateOrderDetailRequest;
import com.practice.learningJPA.payloads.responses.HttpResponse;
import com.practice.learningJPA.services.order.OrderServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orderDetail")
@RequiredArgsConstructor
public class OrderController {

    private  final OrderServiceImpl orderService;

//    @PostMapping("/add")// dùng json-@RequetsBody
//    public ResponseEntity<HttpResponse> addOrderDetailUse(@RequestBody AddOrderDetailRequest addOrderRequest){// POINT: dùng request body
//        return orderService.addOrder(addOrderRequest);// không truyền dữ liệu vào controller
//    }
//
//    @PutMapping("/update")// Dùng @Transaction thay thì save
//    public ResponseEntity<HttpResponse> updateOrderDetailUse(@RequestBody UpdateOrderDetailRequest updateOrderRequest){// POINT: dùng request body
//        return orderService.updateOrder(updateOrderRequest);
//    }

}
