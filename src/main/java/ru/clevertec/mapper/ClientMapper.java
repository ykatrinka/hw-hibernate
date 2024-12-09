package ru.clevertec.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.clevertec.dto.ClientCreateDto;
import ru.clevertec.dto.ClientResponse;
import ru.clevertec.dto.ClientUpdateDto;
import ru.clevertec.entity.Client;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    @Mapping(target = "reviews", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "cars", ignore = true)
    Client createDtoToEntity(ClientCreateDto clientCreateDto);

    @Mapping(target = "reviews", ignore = true)
    @Mapping(target = "id", source = "clientId")
    @Mapping(target = "cars", ignore = true)
    Client updateDtoToEntity(ClientUpdateDto clientUpdateDto, Long clientId);

    ClientResponse entityToResponse(Client client);

    default Client fromId(Long clientId) {
        return clientId != null ? Client.builder().id(clientId).build() : null;
    }
}
