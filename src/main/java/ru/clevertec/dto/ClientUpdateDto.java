package ru.clevertec.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;
import java.util.Map;

public record ClientUpdateDto(
        @NotBlank(message = "Name is null or empty")
        @Length(max = 100, message = "Name is more than 100 characters")
        String name,
        @PastOrPresent @JsonFormat(pattern = "dd-MM-yyyy")
        LocalDate dateOfRegister,
        Map<@NotBlank String, @NotBlank String> contacts
) {
}
