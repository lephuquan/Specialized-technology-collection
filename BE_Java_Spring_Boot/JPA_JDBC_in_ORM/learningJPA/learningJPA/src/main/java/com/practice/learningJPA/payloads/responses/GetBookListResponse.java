package com.practice.learningJPA.payloads.responses;

import com.practice.learningJPA.services.book.BookDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder // Dùng Builder  để cho phép trả về đối tượng trong response
public class GetBookListResponse {

    private List<BookDto> content;

    private PaginationSpec paginationSpec;
}
