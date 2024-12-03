package ru.clevertec.exception;

import ru.clevertec.util.Constants;

public class CarBadRequestException extends RuntimeException {
    private CarBadRequestException(String message) {
        super(message);
    }

    public static CarBadRequestException byCarRequest() {
        return new CarBadRequestException(Constants.CAR_BAD_REQUEST);
    }

}
