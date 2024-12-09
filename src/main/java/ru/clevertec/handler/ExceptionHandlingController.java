package ru.clevertec.handler;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.clevertec.exception.CarAnotherClientException;
import ru.clevertec.exception.CarNotFoundException;
import ru.clevertec.exception.CarShowroomNotFoundException;
import ru.clevertec.exception.CategoryNotFoundException;
import ru.clevertec.exception.ClientNotFoundException;
import ru.clevertec.exception.ReviewNotFoundException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExceptionHandlingController {

    @ExceptionHandler(value = {CategoryNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorMessage handleCategoryNotFoundExceptions(CategoryNotFoundException e) {
        return createErrorMessage(HttpStatus.NOT_FOUND, e.getMessage());
    }

    @ExceptionHandler(value = {CarAnotherClientException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorMessage handleCarAnotherClientExceptions(CarAnotherClientException e) {
        return createErrorMessage(HttpStatus.NOT_FOUND, e.getMessage());
    }

    @ExceptionHandler(value = {CarNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorMessage handleCarNotFoundExceptions(CarNotFoundException e) {
        return createErrorMessage(HttpStatus.NOT_FOUND, e.getMessage());
    }

    @ExceptionHandler(value = {CarShowroomNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorMessage handleCarShowroomNotFoundExceptions(CarShowroomNotFoundException e) {
        return createErrorMessage(HttpStatus.NOT_FOUND, e.getMessage());
    }

    @ExceptionHandler(value = {ClientNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorMessage handleClientNotFoundExceptions(ClientNotFoundException e) {
        return createErrorMessage(HttpStatus.NOT_FOUND, e.getMessage());
    }

    @ExceptionHandler(value = {ReviewNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorMessage handleReviewNotFoundExceptions(ReviewNotFoundException e) {
        return createErrorMessage(HttpStatus.NOT_FOUND, e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage handleValidationException(
            MethodArgumentNotValidException e) {

        Map<String, String> mapErrors = new HashMap<>();
        BindingResult bindingResult = e.getBindingResult();
        bindingResult.getAllErrors()
                .forEach(error -> {
                    String field = error instanceof FieldError ? ((FieldError) error).getField() : error.getObjectName();
                    String message = error.getDefaultMessage();
                    if (mapErrors.containsKey(field)) {
                        mapErrors.put(field, mapErrors.get(field) + " = " + message);
                    } else {
                        mapErrors.put(field, message);
                    }
                });

        return createErrorMessage(HttpStatus.BAD_REQUEST, mapErrors.toString());
    }

    @ExceptionHandler(value = {Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorMessage handleExceptions(Exception e) {
        return createErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }


    public ErrorMessage createErrorMessage(HttpStatus status, String message) {
        return new ErrorMessage(status.value(), status.name(), message);
    }

    public record ErrorMessage(
            int statusCode,
            String status,
            String message

    ) {
    }
}
