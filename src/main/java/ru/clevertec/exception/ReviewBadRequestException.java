package ru.clevertec.exception;

import ru.clevertec.util.Constants;

public class ReviewBadRequestException extends RuntimeException {
    private ReviewBadRequestException(String message) {
        super(message);
    }

    public static ReviewBadRequestException byReviewRequest() {
        return new ReviewBadRequestException(Constants.REVIEW_BAD_REQUEST);
    }

}
