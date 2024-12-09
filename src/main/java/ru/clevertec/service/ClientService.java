package ru.clevertec.service;

import ru.clevertec.dto.ClientCreateDto;
import ru.clevertec.dto.ClientResponse;
import ru.clevertec.dto.ClientUpdateDto;

import java.util.List;

public interface ClientService {
    void saveClient(ClientCreateDto clientCreateDto);

    ClientResponse getClientById(Long clientId);

    void updateClient(Long clientId, ClientUpdateDto clientUpdateDto);

    void deleteClientById(Long clientId);

    List<ClientResponse> getAllClients();

    void buyCar(Long clientId, Long carId);
}
