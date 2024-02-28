package com.practice.learningJPA.payloads.requests;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Objects;

@Data
@AllArgsConstructor
public class GetBookListRequest {


    private Integer size;

    private Integer page;

    private String sortField;

    private String sortDir;

    public GetBookListRequest() {//  Request cơ bản sẽ có page, size hoặc thêm trường cần sort và kiểu sort
        this.page = Objects.isNull(page) ? 0 : page;
        this.size = Objects.isNull(size) ? 10 : size;
        this.sortField = "title";
        this.sortDir = "DESC";
    }
}
