package com.practice.learningJPA.services.book;

import com.practice.learningJPA.entities.Book;
import com.practice.learningJPA.payloads.requests.GetBookListRequest;
import com.practice.learningJPA.payloads.requests.UpdateBookRequest;
import com.practice.learningJPA.payloads.responses.GetBookListResponse;


public interface IBookService {

    GetBookListResponse getBookList(GetBookListRequest getBookListRequest);

    BookDto getBookDetail(String bookName, Long categoryId);

    Void updateBook(UpdateBookRequest book);
}
