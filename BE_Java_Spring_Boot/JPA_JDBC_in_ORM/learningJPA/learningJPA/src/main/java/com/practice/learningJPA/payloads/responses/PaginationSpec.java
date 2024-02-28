package com.practice.learningJPA.payloads.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaginationSpec {

    private long totalElements;

    private int totalPages;

    private int page;

    private int size;

}
