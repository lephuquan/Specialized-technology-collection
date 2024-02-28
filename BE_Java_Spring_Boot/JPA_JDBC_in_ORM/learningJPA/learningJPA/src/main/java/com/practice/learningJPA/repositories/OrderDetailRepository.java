package com.practice.learningJPA.repositories;

import com.practice.learningJPA.entities.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {

//    @Query(value = "SELECT o FROM OrderDetail o where o.book.title = 'XinDig' and o.order.id = 1")// Rut gon  cau query để kiểm tra xem có phải query sai
    OrderDetail findByBookNameAndOrderId(String bookName, Long authorId);// Query duoc goi tu @NamedQuery cua  OrderDetail entity

}
