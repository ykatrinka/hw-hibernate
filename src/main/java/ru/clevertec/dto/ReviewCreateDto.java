package ru.clevertec.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.hibernate.validator.constraints.Length;

public record ReviewCreateDto(
        @NotBlank(message = "Text is null or empty")
        @Length(max = 1000, message = "Text is more than 1000 characters")
        String text,
        @Positive
        Integer rating,
        @NotNull
        Long carId,
        @NotNull
        Long clientId
) {
}
