package com.practice.learningJPA.controller;

import com.practice.learningJPA.payloads.requests.AddAnonymousShopReviewRequest;
import com.practice.learningJPA.payloads.responses.HttpResponse;
import com.practice.learningJPA.services.anonymous_shop_reviews.IAnonymousShopReviewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/AnonymousShopReviews")
@RequiredArgsConstructor
public class AnonymousShopReviewsController {

    private final IAnonymousShopReviewsService iAnonymousShopReviewsService;

    @PostMapping("/add")// Dùng form data
        public ResponseEntity<HttpResponse> addAnonymousShopReviews(@ModelAttribute AddAnonymousShopReviewRequest anonymousShopReviewRequest){
        return iAnonymousShopReviewsService.addAnonymousShopReviews(anonymousShopReviewRequest);
    }
}
