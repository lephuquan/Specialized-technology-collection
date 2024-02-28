package com.practice.learningJPA.services.orderDetail;

import com.practice.learningJPA.entities.OrderDetail;
import com.practice.learningJPA.exceptions.ResourceNotFoundException;
import com.practice.learningJPA.payloads.responses.HttpResponse;
import com.practice.learningJPA.repositories.OrderDetailRepository;
import com.practice.learningJPA.services.book.BookDto;
import com.practice.learningJPA.services.order.OrderDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;


@Service
@RequiredArgsConstructor
public class OrderDetailImpl implements IOrderDetailService{

    private  final OrderDetailRepository orderDetailRepository;

    private final MessageSource messageSource;


    @Override
    public ResponseEntity<HttpResponse> getOrderDetailByBookNameAndOrderId(String bookName, Long orderId) {

        ModelMapper modelMapper = new ModelMapper();

        OrderDetail orderDetail = orderDetailRepository.findByBookNameAndOrderId(bookName, orderId); // Luôn đặt exception khi lấy dữ liệu
        // Chú ý: Nếu trả về api bằng object entity thì sẽ bị đệ quy cho dù dùng fetch = FetchType.EAGER giữa các entity hay không!

        if (orderDetail == null) {// gọi class, gán lỗi cho response: ResourceNotFoundException
            throw new ResourceNotFoundException(messageSource.getMessage("api.resource.not-found",
                    null,
                    LocaleContextHolder.getLocale()));
        }

        BookDto bookDto = modelMapper.map(orderDetail.getBook(), BookDto.class);
        OrderDto orderDto = modelMapper.map(orderDetail.getOrder(), OrderDto.class);

        OrderDetailDto orderDetailDto;
        orderDetailDto = modelMapper.map(orderDetail, OrderDetailDto.class);
//        orderDetailDto.setBookDto(bookDto);
//        orderDetailDto.setOrderDto(orderDto);


        return ResponseEntity.ok().body(
                HttpResponse.builder()
                        .timeStamp(LocalDateTime.now().toString())
                        .data(Map.of("GetOrderDetail", orderDetailDto))// có thể bỏ Map để trả về data là getBookListResponse
                        .message("Get order detail success")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );

    }
}
