package com.practice.learningJPA.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Anonymous_Shop_Reviews")
public class AnonymousShopReviews {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "subject")
    private String subject;

    @Column(name = "review_Date")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate reviewDate;

    @Column(name = "content")
    private String content;
}
