package ru.clevertec.exception;

import ru.clevertec.util.Constants;

public class ReviewNotFoundException extends RuntimeException {
    private ReviewNotFoundException(String message) {
        super(message);
    }

    public static ReviewNotFoundException byReviewId(Long reviewId) {
        return new ReviewNotFoundException(
                String.format(Constants.REVIEW_NOT_FOUND, reviewId)
        );
    }

}
