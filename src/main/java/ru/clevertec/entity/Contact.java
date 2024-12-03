package ru.clevertec.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ru.clevertec.enums.ContactType;
import ru.clevertec.enums.ContactTypeConverter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Embeddable
public class Contact {

    @Convert(converter = ContactTypeConverter.class)
    @Column(name = "contact_type", nullable = false, length = 30)
    private ContactType contactType;

    @Column(name = "contact_info", nullable = false, length = 50)
    private String contactInfo;

}
