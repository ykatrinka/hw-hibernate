package ru.clevertec.exception;

import ru.clevertec.util.Constants;

public class CarAnotherClientException extends RuntimeException {
    private CarAnotherClientException(String message) {
        super(message);
    }

    public static CarAnotherClientException byCarIdAndClientId(Long carId, Long clientId) {
        return new CarAnotherClientException(
                String.format(Constants.CAR_NOT_EXISTS, carId, clientId)
        );
    }

}
