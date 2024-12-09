package ru.clevertec.dto;

import java.math.BigDecimal;


public record CarResponse(
        Long id,
        String model,
        String brand,
        int year,
        BigDecimal price,
        CategoryResponse category,
        CarShowroomResponse carShowroom
) {
}
