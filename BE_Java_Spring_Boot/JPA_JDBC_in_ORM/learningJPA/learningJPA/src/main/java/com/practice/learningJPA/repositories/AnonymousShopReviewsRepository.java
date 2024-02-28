package com.practice.learningJPA.repositories;

import com.practice.learningJPA.entities.AnonymousShopReviews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnonymousShopReviewsRepository extends JpaRepository<AnonymousShopReviews, Long> {
}
