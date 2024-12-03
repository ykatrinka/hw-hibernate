package ru.clevertec.exception;

import ru.clevertec.util.Constants;

public class ClientBadRequestException extends RuntimeException {
    private ClientBadRequestException(String message) {
        super(message);
    }

    public static ClientBadRequestException byClientRequest() {
        return new ClientBadRequestException(Constants.CLIENT_BAD_REQUEST);
    }

}
