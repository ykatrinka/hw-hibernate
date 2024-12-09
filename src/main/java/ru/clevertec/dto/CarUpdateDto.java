package ru.clevertec.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;

public record CarUpdateDto(
        @NotBlank(message = "Model is null or empty")
        @Length(max = 50, message = "Model is more than 50 characters")
        String model,
        @NotBlank(message = "Brand is null or empty")
        @Length(max = 50, message = "Brand is more than 50 characters")
        String brand,
        @Min(value = 1970, message = "Year less than 1970")
        @Max(value = 2024, message = "Year more than 2024")
        int year,
        @Positive(message = "Price is negative")
        BigDecimal price,
        @NotNull(message = "Category is null")
        Long categoryId
) {
}
