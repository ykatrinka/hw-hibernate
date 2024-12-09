package ru.clevertec.dto;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record CarShowroomCreateDto(
        @NotBlank(message = "Name is null or empty")
        @Length(max = 50, message = "Name is more than 50 characters")
        String name,
        @NotBlank(message = "Address is null or empty")
        @Length(max = 100, message = "Address is more than 100 characters")
        String address
) {
}
