package ru.clevertec.service;

import ru.clevertec.dto.ClientRequest;
import ru.clevertec.dto.ClientResponse;

import java.util.List;

public interface ClientService {
    void saveClient(ClientRequest clientRequest);

    ClientResponse getClientById(Long clientId);

    void updateClient(ClientRequest clientRequest, Long clientId);

    void deleteClientById(Long clientId);

    List<ClientResponse> getAllClients();

    void buyCar(Long clientId, Long carId);
}
