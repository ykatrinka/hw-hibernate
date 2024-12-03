package ru.clevertec.exception;

import ru.clevertec.util.Constants;

public class CategoryBadRequestException extends RuntimeException {
    private CategoryBadRequestException(String message) {
        super(message);
    }

    public static CategoryBadRequestException byCategoryRequest() {
        return new CategoryBadRequestException(Constants.CATEGORY_BAD_REQUEST);
    }

}
