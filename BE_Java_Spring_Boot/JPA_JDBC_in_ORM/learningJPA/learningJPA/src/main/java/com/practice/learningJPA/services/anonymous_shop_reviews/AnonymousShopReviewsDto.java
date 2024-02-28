package com.practice.learningJPA.services.anonymous_shop_reviews;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnonymousShopReviewsDto {

    private Long id;

    private String subject;

    private LocalDate reviewDate;

    private String content;
}
