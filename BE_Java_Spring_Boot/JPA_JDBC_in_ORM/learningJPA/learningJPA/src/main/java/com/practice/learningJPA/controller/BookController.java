package com.practice.learningJPA.controller;

import com.practice.learningJPA.payloads.requests.GetBookListRequest;
import com.practice.learningJPA.payloads.requests.UpdateBookRequest;
import com.practice.learningJPA.payloads.responses.GetBookListResponse;
import com.practice.learningJPA.payloads.responses.HttpResponse;
import com.practice.learningJPA.services.book.BookDto;
import com.practice.learningJPA.services.book.IBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.EntityResponse;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/api/book")
@RequiredArgsConstructor
public class BookController {

    private final IBookService bookService;

    @GetMapping("getAll")
    public ResponseEntity<HttpResponse> getAllBook(GetBookListRequest getBookListRequest){// Không cần dùng @Requestbody

        GetBookListResponse getBookListResponse = bookService.getBookList(getBookListRequest);

        return ResponseEntity.ok().body(
                HttpResponse.builder()
                        .timeStamp(LocalDateTime.now().toString())
                        .data(Map.of("getBookListResponse", getBookListResponse))// có thể bỏ Map để trả về data là getBookListResponse
                        .message("Get book success")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }

    @GetMapping("detail/bookName/{bookName}/categoryId/{categoryId}")// Sử dụng pathVariable
    public ResponseEntity<HttpResponse> getBookDetail(@PathVariable String bookName, @PathVariable Long categoryId ){// @PathVariable

        BookDto bookDto = bookService.getBookDetail(bookName, categoryId);

        return ResponseEntity.ok().body(// Response trả về 1 đối tượng, có thể chỉ cần dùng ResponseEntity(bookDto)
                HttpResponse.builder()
                        .timeStamp(LocalDateTime.now().toString())
                        .data(Map.of("bookDto", bookDto))
                        .message("Get book success")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }

    @PutMapping("update")
    public ResponseEntity<HttpResponse> UpdateBook(@RequestBody UpdateBookRequest book){

        bookService.updateBook(book);

        return ResponseEntity.ok().body(
                HttpResponse.builder()
                        .timeStamp(LocalDateTime.now().toString())
                        .data(Map.of("Update", book))
                        .message("Update book success")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }
}

