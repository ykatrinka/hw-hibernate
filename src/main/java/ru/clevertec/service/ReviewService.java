package ru.clevertec.service;

import ru.clevertec.dto.ReviewRequest;
import ru.clevertec.dto.ReviewResponse;

import java.util.List;

public interface ReviewService {
    void saveReview(ReviewRequest reviewRequest);

    ReviewResponse getReviewById(Long reviewId);

    void updateReview(ReviewRequest reviewRequest, Long reviewId);

    void deleteReviewById(Long reviewId);

    List<ReviewResponse> getAllReviews();

    List<ReviewResponse> getAllReviewsFullTextSearch(String searchText);
}
