package com.practice.learningJPA.repositories;

import com.practice.learningJPA.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Query(value = "SELECT b FROM Book b where b.title = :bookName and b.category.id = :categoryId", nativeQuery = false) // JPQL
    Book getBookByBookIdAndCategoryId(String bookName, Long categoryId);

}
