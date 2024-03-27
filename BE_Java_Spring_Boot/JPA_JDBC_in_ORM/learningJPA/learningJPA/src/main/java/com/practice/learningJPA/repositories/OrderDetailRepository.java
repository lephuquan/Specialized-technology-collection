package com.practice.learningJPA.repositories;

import com.practice.learningJPA.entities.OrderDetail;
import com.practice.learningJPA.services.orderDetail.GetOrderDetailRecord;
import com.practice.learningJPA.services.orderDetail.dtoInterface.IGetBookDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {

    //@NamedQuery
    OrderDetail findByBookNameAndOrderId(@Param("bookName") String bookName,@Param("orderId") Long orderId);// Query duoc goi tu @NamedQuery cua  OrderDetail entity, nên dùng @Param để chỉ định tham số - ko có vẫn được

    @Query(value = "SELECT o.id as id, o.book as book, o.order as order FROM OrderDetail o where o.book.title = :bookName and o.order.id = :orderId")// Tìm nạp phép chiếu Proxy interface
    IGetBookDetail findByBookNameAndOrderIdCase2(@Param("bookName") String bookName,@Param("orderId") Long orderId);

//    @Query(value = """
//           SELECT new GetOrderDetailRecord(
//           o.id as id,
//           o.book as book,
//           o.order  order
//           )
//           FROM OrderDetail o
//           where o.book.title = :bookName
//           and o.order.id = :authorId
//           """)// Tìm nạp phép chiếu POJO DTO bằng Spring Data JPA / java 17
//    GetOrderDetailRecord findByBookNameAndOrderIdCase3(@Param("bookName") String bookName, @Param("authorId") Long authorId);

}
