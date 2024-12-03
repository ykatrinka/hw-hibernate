package ru.clevertec.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CarRequest {

    private String model;
    private String brand;
    private int year;
    private Double price;
    private CategoryRequest category;

}
