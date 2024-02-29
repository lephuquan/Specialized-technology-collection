package com.practice.learningJPA.controller;

import com.practice.learningJPA.payloads.responses.HttpResponse;
import com.practice.learningJPA.services.orderDetail.IOrderDetailService;
import com.practice.learningJPA.services.orderDetail.OrderDetailDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/api/orderDetail")
@RequiredArgsConstructor
public class OrderDetailController {

    private final IOrderDetailService orderDetailService;// Lứu ý final

    @GetMapping(path = "/search")// Nhận tham số dưới dạng: ?bookName=keyword1&orderId=keyword2
    public ResponseEntity<?> getOrderDetailByBookNameAndOrderId(@RequestParam("bookName") String bookName, @RequestParam("orderId") Long orderId){
//        return orderDetailService.getOrderDetailByBookNameAndOrderId(bookName, orderId);
        return orderDetailService.getOrderDetailByBookNameAndOrderIdCase2(bookName, orderId); // case1
//        return orderDetailService.getOrderDetailByBookNameAndOrderIdCase3(bookName, orderId);//  case2
    }


}
