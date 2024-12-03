package ru.clevertec.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CarRequest implements Serializable {

    private String model;
    private String brand;
    private int year;
    private Double price;
    private Long categoryId;

}
