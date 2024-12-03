package ru.clevertec.exception;

import ru.clevertec.util.Constants;

public class CarNotFoundException extends RuntimeException {
    private CarNotFoundException(String message) {
        super(message);
    }

    public static CarNotFoundException byCarId(Long carId) {
        return new CarNotFoundException(
                String.format(Constants.CAR_NOT_FOUND, carId)
        );
    }

}
