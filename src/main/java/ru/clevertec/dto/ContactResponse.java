package ru.clevertec.dto;

import lombok.Builder;
import lombok.Data;
import ru.clevertec.enums.ContactType;

@Data
@Builder
public class ContactResponse {

    private ContactType contactType;
    private String contactInfo;

}
