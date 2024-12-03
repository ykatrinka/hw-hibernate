package ru.clevertec.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReviewResponse {

    private Long id;
    private String text;
    private Integer rating;
    private CarResponse car;
    private ClientResponse client;

}
