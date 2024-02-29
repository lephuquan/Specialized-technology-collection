package com.practice.learningJPA.services.orderDetail;

import com.practice.learningJPA.entities.OrderDetail;
import com.practice.learningJPA.exceptions.ResourceNotFoundException;
import com.practice.learningJPA.payloads.responses.HttpResponse;
import com.practice.learningJPA.repositories.OrderDetailRepository;
import com.practice.learningJPA.services.book.BookDto;
import com.practice.learningJPA.services.order.OrderDto;
import com.practice.learningJPA.services.orderDetail.dtoInterface.IGetBookDetail;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class OrderDetailImpl implements IOrderDetailService{

    private  final OrderDetailRepository orderDetailRepository;

    private final MessageSource messageSource;


    @Override
    public ResponseEntity<OrderDetailDto> getOrderDetailByBookNameAndOrderId(String bookName, Long orderId) {// Cơ bản, chậm

        ModelMapper modelMapper = new ModelMapper();

        // Chú ý: Nếu trả về api bằng object entity thì sẽ bị đệ quy cho dù dùng fetch = FetchType.EAGER giữa các entity hay không!
        OrderDetail orderDetail = orderDetailRepository.findByBookNameAndOrderId(bookName, orderId); // Luôn đặt exception khi lấy dữ liệu

        if (orderDetail == null) {// gọi class, gán lỗi cho response: ResourceNotFoundException
            throw new ResourceNotFoundException(messageSource.getMessage("api.resource.not-found",
                    null,
                    LocaleContextHolder.getLocale()));
        }


        //Dto map
        BookDto bookDto = modelMapper.map(orderDetail.getBook(), BookDto.class);
        OrderDto orderDto = modelMapper.map(orderDetail.getOrder(), OrderDto.class);

        OrderDetailDto orderDetailDto;// response dto
        orderDetailDto = modelMapper.map(orderDetail, OrderDetailDto.class);
        orderDetailDto.setBookDto(bookDto);
        orderDetailDto.setOrderDto(orderDto);

        return ResponseEntity.ok(orderDetailDto) ;
    }

    @Override
    public ResponseEntity<BookDto> getOrderDetailByBookNameAndOrderIdCase2(String bookName, Long orderId) {// Tìm nạp phép chiếu Proxy interface

        ModelMapper modelMapper = new ModelMapper();

        IGetBookDetail orderDetail = orderDetailRepository.findByBookNameAndOrderIdCase2(bookName, orderId); // // Tìm nạp phép chiếu Proxy interface

        if (orderDetail == null) {// gọi class, gán lỗi cho response: ResourceNotFoundException
            throw new ResourceNotFoundException(messageSource.getMessage("api.resource.not-found",
                    null,
                    LocaleContextHolder.getLocale()));
        }

        //Dto map
        BookDto bookDto = modelMapper.map(orderDetail.getBook(), BookDto.class);
        OrderDto orderDto = modelMapper.map(orderDetail.getOrder(), OrderDto.class);

        OrderDetailDto orderDetailDto;// response dto
        orderDetailDto = modelMapper.map(orderDetail, OrderDetailDto.class);
        orderDetailDto.setBookDto(bookDto);
        orderDetailDto.setOrderDto(orderDto);

        return ResponseEntity.ok(bookDto) ;
    }

//    @Override
//    public ResponseEntity<GetOrderDetailRecord> getOrderDetailByBookNameAndOrderIdCase3(String bookName, Long orderId) {
//        ModelMapper modelMapper = new ModelMapper();
//
//        GetOrderDetailRecord orderDetailDto = orderDetailRepository.findByBookNameAndOrderIdCase3(bookName, orderId);
//
//        if (orderDetailDto == null) {// gọi class, gán lỗi cho response: ResourceNotFoundException
//            throw new ResourceNotFoundException(messageSource.getMessage("api.resource.not-found",
//                    null,
//                    LocaleContextHolder.getLocale()));
//        }
//
//
////        Dto map
////        BookDto bookDto = modelMapper.map(orderDetail.getBook(), BookDto.class);
////        OrderDto orderDto = modelMapper.map(orderDetail.getOrder(), OrderDto.class);
////
////        OrderDetailDto orderDetailDto;// response dto
////        orderDetailDto = modelMapper.map(orderDetail, OrderDetailDto.class);
////        orderDetailDto.setBookDto(bookDto);
////        orderDetailDto.setOrderDto(orderDto);
//
//
//        return  ResponseEntity.ok(orderDetailDto);
//    }
}
