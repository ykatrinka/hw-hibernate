package ru.clevertec.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.clevertec.dto.ClientCreateDto;
import ru.clevertec.dto.ClientResponse;
import ru.clevertec.dto.ClientUpdateDto;
import ru.clevertec.entity.Car;
import ru.clevertec.entity.Client;
import ru.clevertec.exception.CarNotFoundException;
import ru.clevertec.exception.ClientNotFoundException;
import ru.clevertec.logging.Logging;
import ru.clevertec.mapper.ClientMapper;
import ru.clevertec.repository.CarRepository;
import ru.clevertec.repository.ClientRepository;
import ru.clevertec.service.ClientService;

import java.util.List;
import java.util.Optional;

@Logging
@Service
@RequiredArgsConstructor
@Transactional
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final CarRepository carRepository;
    private final ClientMapper clientMapper;

    @Override
    public void saveClient(ClientCreateDto clientCreateDto) {
        Client client = clientMapper.createDtoToEntity(clientCreateDto);
        clientRepository.save(client);
    }

    @Transactional(readOnly = true)
    @Override
    public ClientResponse getClientById(Long clientId) {
        return clientRepository.findById(clientId)
                .map(clientMapper::entityToResponse)
                .orElseThrow(() -> ClientNotFoundException.byClientId(clientId));
    }

    @Override
    public void updateClient(Long clientId, ClientUpdateDto clientUpdateDto) {
        List<Car> cars = getClient(clientId).getCars();
        Client client = clientMapper.updateDtoToEntity(clientUpdateDto, clientId);
        client.setCars(cars);

        clientRepository.save(client);
    }


    @Override
    public void deleteClientById(Long clientId) {
        Optional.ofNullable(clientId)
                .map(clientRepository::findById)
                .ifPresent(client -> clientRepository.deleteById(clientId));
    }

    @Transactional(readOnly = true)
    @Override
    public List<ClientResponse> getAllClients() {
        return clientRepository.findAll()
                .stream()
                .map(clientMapper::entityToResponse)
                .toList();
    }

    @Override
    public void buyCar(Long clientId, Long carId) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> ClientNotFoundException.byClientId(clientId));
        Car car = carRepository.findById(carId)
                .orElseThrow(() -> CarNotFoundException.byCarId(carId));

        boolean isCarNotExists = client.getCars().stream()
                .noneMatch(car1 -> car1.getId().equals(carId));

        if (isCarNotExists) {
            client.getCars().add(car);
            clientRepository.save(client);
        }
    }


    private Client getClient(Long clientId) {
        return clientRepository.findById(clientId)
                .orElseThrow(() -> ClientNotFoundException.byClientId(clientId));
    }
}
