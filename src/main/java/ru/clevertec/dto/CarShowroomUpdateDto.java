package ru.clevertec.dto;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record CarShowroomUpdateDto(
        @NotBlank(message = "Category is null or empty")
        @Length(max = 50, message = "Category is more than 50 characters")
        String name,
        @NotBlank(message = "Category is null or empty")
        @Length(max = 100, message = "Category is more than 100 characters")
        String address
) {
}
