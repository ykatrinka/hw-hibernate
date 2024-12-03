package ru.clevertec.enums;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Arrays;

@Converter(autoApply = true)
public class ContactTypeConverter implements AttributeConverter<ContactType, String> {

    @Override
    public String convertToDatabaseColumn(ContactType contactType) {
        return contactType != null ? contactType.name() : null;
    }

    @Override
    public ContactType convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }

        return Arrays.stream(ContactType.values())
                .filter(contactType -> contactType.name().equals(dbData))
                .findFirst()
                .orElse(null);
    }
}
