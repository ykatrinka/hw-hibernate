package ru.clevertec.exception;

import ru.clevertec.util.Constants;

public class CategoryNotFoundException extends RuntimeException {
    private CategoryNotFoundException(String message) {
        super(message);
    }

    public static CategoryNotFoundException byCategoryId(Long categoryId) {
        return new CategoryNotFoundException(
                String.format(Constants.CATEGORY_NOT_FOUND, categoryId)
        );
    }

}
