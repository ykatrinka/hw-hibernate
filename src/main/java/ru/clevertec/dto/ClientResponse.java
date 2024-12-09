package ru.clevertec.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import ru.clevertec.entity.Car;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public record ClientResponse(
        Long id,
        String name,
        @JsonFormat(pattern = "dd-MM-yyyy")
        LocalDate dateOfRegister,
        List<Car> cars,
        Map<String, String> contacts
) {
}
