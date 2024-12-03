package ru.clevertec.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CarShowroomResponse {

    private Long id;
    private String name;
    private String address;

}
