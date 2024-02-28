package com.practice.learningJPA.services.order;

import com.practice.learningJPA.entities.Book;
import com.practice.learningJPA.entities.Order;
import com.practice.learningJPA.entities.OrderDetail;
import com.practice.learningJPA.payloads.requests.AddOrderDetailRequest;
import com.practice.learningJPA.payloads.requests.UpdateOrderDetailRequest;
import com.practice.learningJPA.payloads.responses.HttpResponse;
import com.practice.learningJPA.repositories.OrderDetailRepository;
import com.practice.learningJPA.repositories.OrdersRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements IOrderService{

//    private  final OrdersRepository ordersRepository;
//
//    @Override
//    public ResponseEntity<HttpResponse> addOrder(@RequestBody OrderDto orderDto) {
//
//        ModelMapper modelMapper = new ModelMapper();
//
//        Order order = new Order();
//        order.setOrderDate(orderDto.getOrderDate());
//
//        List<OrderDetail> orderDetailList = orderDto.getOrderDetailDtoList().stream()// Dùng stream xử lý thay for
//                .map(dto -> modelMapper.map(dto, OrderDetail.class))
//                .collect(Collectors.toList());
//
//        order.setBookList(bookList);
//
//        ordersRepository.save(order);
//
//        return ResponseEntity.created(URI.create("")).body(
//                HttpResponse.builder()
//                        .timeStamp(LocalDateTime.now().toString())
//                        .data(Map.of("Order",order))
//                        .message("Order Created")
//                        .status(HttpStatus.CREATED)
//                        .statusCode(HttpStatus.CREATED.value())
//                        .build()
//        );
//    }

}
