package ru.clevertec.exception;

import ru.clevertec.util.Constants;

public class CarShowroomNotFoundException extends RuntimeException {
    private CarShowroomNotFoundException(String message) {
        super(message);
    }

    public static CarShowroomNotFoundException byCarShowroomId(Long carShowroomId) {
        return new CarShowroomNotFoundException(
                String.format(Constants.CAR_SHOWROOM_NOT_FOUND, carShowroomId)
        );
    }

}
