package com.practice.learningJPA.services.category;

import com.practice.learningJPA.entities.Book;
import com.practice.learningJPA.entities.Category;
import com.practice.learningJPA.payloads.responses.HttpResponse;
import com.practice.learningJPA.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements ICategoryService{

    private final CategoryRepository categoryRepository;
    @Override
    public ResponseEntity<HttpResponse> addCategory(CategoryDto categoryDto) {
        // Hàm này chủ yếu sử dụng stream chuyển dto sang entity thay for nên bỏ qua logic việc: thêm book kèm với category chứ không phải thêm category kèm list book

        ModelMapper modelMapper = new ModelMapper();
        Category category = new Category();

        category.setName(categoryDto.getName());

        List<Book> bookList = categoryDto.getBookDtoList().stream()// Dùng stream xử lý thay for
                .map(dto -> modelMapper.map(dto, Book.class))
                .collect(Collectors.toList());

        category.setBookList(bookList);

        categoryRepository.save(category);

        return ResponseEntity.created(URI.create("")).body(
                HttpResponse.builder()
                        .timeStamp(LocalDateTime.now().toString())
                        .data(Map.of("Category", category))
                        .message("Category Created")
                        .status(HttpStatus.CREATED)
                        .statusCode(HttpStatus.CREATED.value())
                        .build()
        );
    }
}
