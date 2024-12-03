package ru.clevertec.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CarResponse {

    private Long id;
    private String model;
    private String brand;
    private int year;
    private Double price;
    private CategoryResponse category;
    private CarShowroomResponse carShowroom;

}
