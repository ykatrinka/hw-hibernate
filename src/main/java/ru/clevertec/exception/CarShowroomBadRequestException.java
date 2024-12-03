package ru.clevertec.exception;

import ru.clevertec.util.Constants;

public class CarShowroomBadRequestException extends RuntimeException {
    private CarShowroomBadRequestException(String message) {
        super(message);
    }

    public static CarShowroomBadRequestException byCarShowroomRequest() {
        return new CarShowroomBadRequestException(Constants.CAR_SHOWROOM_BAD_REQUEST);
    }

}
