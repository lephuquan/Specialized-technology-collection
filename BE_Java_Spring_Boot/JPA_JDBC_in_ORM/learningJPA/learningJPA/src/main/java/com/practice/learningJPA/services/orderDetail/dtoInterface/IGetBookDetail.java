package com.practice.learningJPA.services.orderDetail.dtoInterface;

import com.practice.learningJPA.entities.Book;
import com.practice.learningJPA.entities.Order;

public interface IGetBookDetail { // Tìm nạp phép chiếu Proxy interface


     Long getId();


     Order getOrder();


     Book getBook();


}
