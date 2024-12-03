package ru.clevertec.service.impl;

import lombok.RequiredArgsConstructor;
import ru.clevertec.dto.ClientRequest;
import ru.clevertec.dto.ClientResponse;
import ru.clevertec.entity.Client;
import ru.clevertec.exception.CarNotFoundException;
import ru.clevertec.exception.ClientBadRequestException;
import ru.clevertec.exception.ClientNotFoundException;
import ru.clevertec.mapper.ClientMapper;
import ru.clevertec.repository.CarRepository;
import ru.clevertec.repository.ClientRepository;
import ru.clevertec.service.ClientService;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final CarRepository carRepository;
    private final ClientMapper clientMapper;

    @Override
    public void saveClient(ClientRequest clientRequest) {
        checkClientRequest(clientRequest);

        Client car = clientMapper.requestToEntity(clientRequest);
        clientRepository.save(car);
    }

    @Override
    public ClientResponse getClientById(Long clientId) {
        checkClientId(clientId);

        Client client = clientRepository.getById(clientId);
        return clientMapper.entityToResponse(client);
    }

    @Override
    public void updateClient(ClientRequest clientRequest, Long clientId) {
        checkClientRequest(clientRequest);
        Client clientDB = checkClientId(clientId);

        Client client = clientMapper.requestToEntity(clientRequest, clientId);
        client.setCars(clientDB.getCars());
        clientRepository.update(client);
    }

    @Override
    public void deleteClientById(Long clientId) {
        checkClientId(clientId);

        clientRepository.deleteById(clientId);
    }

    @Override
    public List<ClientResponse> getAllClients() {
        return clientRepository.getAll()
                .stream()
                .map(clientMapper::entityToResponse)
                .toList();
    }

    @Override
    public void buyCar(Long clientId, Long carId) {
        checkClientId(clientId);
        checkCarId(carId);

        clientRepository.buyCar(clientId, carId);
    }

    private void checkClientRequest(ClientRequest clientRequest) {
        if (clientRequest == null) {
            throw ClientBadRequestException.byClientRequest();
        }
    }

    private Client checkClientId(Long clientId) {
        return Optional.ofNullable(clientId)
                .map(clientRepository::getById)
                .orElseThrow(() -> ClientNotFoundException.byClientId(clientId));
    }

    private void checkCarId(Long carId) {
        Optional.ofNullable(carId)
                .map(carRepository::getById)
                .orElseThrow(() -> CarNotFoundException.byCarId(carId));
    }

}
