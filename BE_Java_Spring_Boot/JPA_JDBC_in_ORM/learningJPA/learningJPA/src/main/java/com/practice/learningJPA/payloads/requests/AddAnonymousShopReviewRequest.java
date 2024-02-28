package com.practice.learningJPA.payloads.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddAnonymousShopReviewRequest {
    private Long id;

    private String subject;

    private LocalDate reviewDate;

    private String content;
}
