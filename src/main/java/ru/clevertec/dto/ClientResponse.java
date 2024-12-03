package ru.clevertec.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class ClientResponse {

    private Long id;
    private String name;
    private LocalDate dateOfRegister;
    private List<ContactResponse> contacts;

}
