package ru.clevertec.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReviewRequest {

    private String text;
    private Integer rating;
    private Long carId;
    private Long clientId;

}
