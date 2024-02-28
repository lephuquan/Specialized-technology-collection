package com.practice.learningJPA.services.orderDetail;

import com.practice.learningJPA.entities.Book;
import com.practice.learningJPA.entities.Order;
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



public interface IOrderDetailService {

    ResponseEntity<HttpResponse> getOrderDetailByBookNameAndOrderId(String bookName, Long orderId);
}
