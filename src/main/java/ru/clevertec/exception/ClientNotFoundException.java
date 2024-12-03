package ru.clevertec.exception;

import ru.clevertec.util.Constants;

public class ClientNotFoundException extends RuntimeException {
    private ClientNotFoundException(String message) {
        super(message);
    }

    public static ClientNotFoundException byClientId(Long clientId) {
        return new ClientNotFoundException(
                String.format(Constants.CLIENT_NOT_FOUND, clientId)
        );
    }

}
