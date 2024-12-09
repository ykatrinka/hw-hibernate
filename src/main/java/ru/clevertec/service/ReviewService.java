package ru.clevertec.service;

import ru.clevertec.dto.ReviewCreateDto;
import ru.clevertec.dto.ReviewResponse;
import ru.clevertec.dto.ReviewUpdateDto;

import java.util.List;

public interface ReviewService {
    void saveReview(ReviewCreateDto reviewCreateDto);

    ReviewResponse getReviewById(Long reviewId);

    void updateReview(Long reviewId, ReviewUpdateDto reviewUpdateDto);

    void deleteReviewById(Long reviewId);

    List<ReviewResponse> getAllReviews();

}
