package com.practice.learningJPA.services.anonymous_shop_reviews;

import com.practice.learningJPA.payloads.requests.AddAnonymousShopReviewRequest;
import com.practice.learningJPA.payloads.responses.HttpResponse;
import org.springframework.http.ResponseEntity;

public interface IAnonymousShopReviewsService {

    ResponseEntity<HttpResponse> addAnonymousShopReviews(AddAnonymousShopReviewRequest anonymousShopReviewRequest);
}
