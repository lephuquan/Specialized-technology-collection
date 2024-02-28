package com.practice.learningJPA.services.anonymous_shop_reviews;

import com.practice.learningJPA.entities.AnonymousShopReviews;
import com.practice.learningJPA.payloads.requests.AddAnonymousShopReviewRequest;
import com.practice.learningJPA.payloads.responses.HttpResponse;
import com.practice.learningJPA.repositories.AnonymousShopReviewsRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AnonymousShopReviewsImpl implements IAnonymousShopReviewsService {

    private final AnonymousShopReviewsRepository anonymousShopReviewsRepository;

    @Override
    public ResponseEntity<HttpResponse> addAnonymousShopReviews(AddAnonymousShopReviewRequest anonymousShopReviewRequest) {

        ModelMapper modelMapper = new ModelMapper();
        AnonymousShopReviews anonymousShopReviews = modelMapper.map(anonymousShopReviewRequest, AnonymousShopReviews.class);
        anonymousShopReviewsRepository.save(anonymousShopReviews);

        return ResponseEntity.created(URI.create("")).body(
                HttpResponse.builder()
                        .timeStamp(LocalDateTime.now().toString())
                        .data(Map.of("AnonymousShopReviews",anonymousShopReviews))
                        .message("Anonymous Shop Reviews Created")
                        .status(HttpStatus.CREATED)
                        .statusCode(HttpStatus.CREATED.value())
                        .build()
        );

    }
}
