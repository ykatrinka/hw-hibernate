package ru.clevertec.dto;

public record ReviewResponse(
        Long id,
        String text,
        Integer rating,
        CarResponse car,
        ClientResponse client
) {
}
