package ru.clevertec.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CarShowroomRequest {

    private String name;
    private String address;

}
