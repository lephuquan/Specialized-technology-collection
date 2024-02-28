package com.practice.learningJPA.services.book;

import com.practice.learningJPA.entities.Book;
import com.practice.learningJPA.payloads.requests.GetBookListRequest;
import com.practice.learningJPA.payloads.responses.GetBookListResponse;
import com.practice.learningJPA.payloads.responses.HttpResponse;
import com.practice.learningJPA.payloads.responses.PaginationSpec;
import com.practice.learningJPA.repositories.BookRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements IBookService{

    private  final BookRepository bookRepository;


    @Override
    public GetBookListResponse getBookList(GetBookListRequest getBookListRequest) {

        ModelMapper modelMapper  = new ModelMapper();

        Sort.Direction sortDir = Objects.equals(getBookListRequest.getSortDir(), "ASC") ?
                Sort.Direction.ASC :
                Sort.Direction.DESC;

        Pageable pageable = PageRequest.of(
                getBookListRequest.getPage(),
                getBookListRequest.getSize(),
                sortDir,
                getBookListRequest.getSortField()
        );

        Page<Book> books = bookRepository.findAll(pageable);// gửi page 0, size 10 nhưng nhận page và size 10

        Type bookDto = new TypeToken<List<BookDto>>() {}.getType();

        List<BookDto> bookDtoList = modelMapper.map(books.getContent(), bookDto);

        PaginationSpec paginationSpec = PaginationSpec.builder()
                .totalPages(books.getTotalPages())
                .totalElements(books.getTotalElements())
                .size(books.getPageable().getPageSize())
                .page(books.getPageable().getPageNumber())
                .build();

        return GetBookListResponse.builder() // Lấy 1 đối tượng, lấy không dùng page thì sao
                .content(bookDtoList)
                .paginationSpec(paginationSpec)
                .build();
    }

    @Override
    public BookDto getBookDetail(String bookName, Long categoryId) {

        ModelMapper modelMapper = new ModelMapper();

        Book book = bookRepository.getBookByBookIdAndCategoryId(bookName, categoryId);

        BookDto bookDto = modelMapper.map(book, BookDto.class);

        return bookDto; // Tả về 1 đối tượng sẽ không xử lý page
    }
}
